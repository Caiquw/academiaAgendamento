package com.example.agendamentoacademia

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendamentoacademia.data.TreinosMock
import com.example.agendamentoacademia.databinding.ActivityListaTreinosBinding
import com.example.agendamentoacademia.model.Treino
import com.example.agendamentoacademia.util.getSerializableExtraCompat

class ListaTreinosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaTreinosBinding
    private val treinos = TreinosMock.lista.toMutableList()
    private lateinit var adapter: TreinoAdapter

    private val detalheLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        if (resultado.resultCode == RESULT_OK) {
            val atualizado = resultado.data?.getSerializableExtraCompat<Treino>(EXTRA_TREINO)
            atualizado?.let { atualizarTreinoNaLista(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaTreinosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TreinoAdapter(treinos) { treino -> abrirDetalhe(treino) }
        binding.rvTreinos.layoutManager = LinearLayoutManager(this)
        binding.rvTreinos.adapter = adapter
    }

    private fun abrirDetalhe(treino: Treino) {

        val intent = Intent(this, DetalheTreinoActivity::class.java)
        intent.putExtra(EXTRA_TREINO, treino)
        detalheLauncher.launch(intent)
    }

    private fun atualizarTreinoNaLista(atualizado: Treino) {
        val indice = treinos.indexOfFirst { it.id == atualizado.id }
        if (indice != -1) {
            treinos[indice] = atualizado
            adapter.notifyItemChanged(indice)
        }
    }

    companion object {
        const val EXTRA_TREINO = "extra_treino"
    }
}
