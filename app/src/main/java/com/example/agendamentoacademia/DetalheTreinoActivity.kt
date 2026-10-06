package com.example.agendamentoacademia

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.agendamentoacademia.databinding.ActivityDetalheTreinoBinding
import com.example.agendamentoacademia.model.StatusPresenca
import com.example.agendamentoacademia.model.Treino
import com.example.agendamentoacademia.util.getSerializableExtraCompat

class DetalheTreinoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheTreinoBinding
    private lateinit var treinoAtual: Treino

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheTreinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recebido = intent.getSerializableExtraCompat<Treino>(ListaTreinosActivity.EXTRA_TREINO)
        if (recebido == null) {
            finish()
            return
        }
        treinoAtual = recebido

        binding.btnAcao.setOnClickListener { registrarAcao() }
        binding.btnVoltar.setOnClickListener {
            enviarResultado()
            finish()
        }

        atualizarInterface()
    }


    private fun registrarAcao() {
        treinoAtual = when (treinoAtual.statusPresenca) {
            StatusPresenca.CONFIRMADO -> treinoAtual.copy(
                statusPresenca = StatusPresenca.CANCELADO,
                vagasOcupadas = (treinoAtual.vagasOcupadas - 1).coerceAtLeast(0)
            )
            StatusPresenca.LISTA_ESPERA -> treinoAtual.copy(
                statusPresenca = StatusPresenca.NAO_MARCADO
            )
            StatusPresenca.NAO_MARCADO, StatusPresenca.CANCELADO -> {
                if (!treinoAtual.estaLotado) {
                    treinoAtual.copy(
                        statusPresenca = StatusPresenca.CONFIRMADO,
                        vagasOcupadas = treinoAtual.vagasOcupadas + 1
                    )
                } else {
                    treinoAtual.copy(statusPresenca = StatusPresenca.LISTA_ESPERA)
                }
            }
        }
        enviarResultado()
        atualizarInterface()
    }

    private fun enviarResultado() {
        val intent = Intent().putExtra(ListaTreinosActivity.EXTRA_TREINO, treinoAtual)
        setResult(RESULT_OK, intent)
    }


    private fun atualizarInterface() {
        binding.tvModalidadeDetalhe.text = treinoAtual.modalidade
        binding.tvInstrutorDetalhe.text = treinoAtual.instrutor
        binding.tvHorarioDetalhe.text = treinoAtual.horario
        binding.tvVagasDetalhe.text = getString(
            R.string.formato_vagas_ocupadas,
            treinoAtual.vagasOcupadas,
            treinoAtual.vagasTotais
        )


        val observacao = treinoAtual.observacao
        if (observacao.isNullOrBlank()) {
            binding.tvLabelObservacao.visibility = View.GONE
            binding.tvObservacaoDetalhe.visibility = View.GONE
        } else {
            binding.tvLabelObservacao.visibility = View.VISIBLE
            binding.tvObservacaoDetalhe.visibility = View.VISIBLE
            binding.tvObservacaoDetalhe.text = observacao
        }

        val (corRes, textoRes) = when (treinoAtual.statusPresenca) {
            StatusPresenca.CONFIRMADO -> R.color.verde_confirmado to R.string.status_confirmado
            StatusPresenca.LISTA_ESPERA -> R.color.amarelo_espera to R.string.status_lista_espera
            StatusPresenca.CANCELADO -> R.color.vermelho_cancelado to R.string.status_cancelado
            StatusPresenca.NAO_MARCADO -> R.color.roxo_academia to R.string.status_nao_marcado
        }
        binding.tvStatusDetalhe.text = getString(textoRes)
        val cor = ContextCompat.getColor(this, corRes)
        (binding.tvStatusDetalhe.background.mutate() as GradientDrawable).setColor(cor)
        (binding.ivIconeDetalhe.background.mutate() as GradientDrawable).setColor(cor)

        binding.btnAcao.text = when (treinoAtual.statusPresenca) {
            StatusPresenca.CONFIRMADO -> getString(R.string.btn_cancelar)
            StatusPresenca.LISTA_ESPERA -> getString(R.string.btn_sair_espera)
            StatusPresenca.NAO_MARCADO, StatusPresenca.CANCELADO ->
                if (!treinoAtual.estaLotado) getString(R.string.btn_confirmar)
                else getString(R.string.btn_lista_espera)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        enviarResultado()
        finish()
        return true
    }

    override fun onBackPressed() {
        enviarResultado()
        super.onBackPressed()
    }
}
