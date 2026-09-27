package com.example.agendamentoacademia

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.agendamentoacademia.databinding.ItemTreinoBinding
import com.example.agendamentoacademia.model.StatusPresenca
import com.example.agendamentoacademia.model.Treino

/**
 * Adapter da RecyclerView que exibe a lista de treinos.
 * Usa ViewBinding (ItemTreinoBinding) para conectar as Views do item ao Kotlin.
 */
class TreinoAdapter(
    private val treinos: List<Treino>,
    private val aoClicar: (Treino) -> Unit
) : RecyclerView.Adapter<TreinoAdapter.TreinoViewHolder>() {

    inner class TreinoViewHolder(val binding: ItemTreinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
        val binding = ItemTreinoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TreinoViewHolder(binding)
    }

    override fun getItemCount(): Int = treinos.size

    override fun onBindViewHolder(holder: TreinoViewHolder, position: Int) {
        val treino = treinos[position]
        val context = holder.binding.root.context

        holder.binding.tvModalidade.text = treino.modalidade
        holder.binding.tvInstrutorHorario.text = context.getString(
            R.string.formato_instrutor_horario, treino.instrutor, treino.horario
        )

        holder.binding.tvVagas.text = if (treino.estaLotado) {
            context.getString(R.string.vagas_esgotadas)
        } else {
            context.getString(R.string.formato_vagas_disponiveis, treino.vagasDisponiveis)
        }

        val (corIcone, _) = corETextoStatus(context, treino.statusPresenca)
        (holder.binding.ivIcone.background.mutate() as GradientDrawable).setColor(corIcone)

        if (treino.statusPresenca == StatusPresenca.NAO_MARCADO) {
            holder.binding.tvStatus.visibility = View.GONE
        } else {
            val (cor, texto) = corETextoStatus(context, treino.statusPresenca)
            holder.binding.tvStatus.visibility = View.VISIBLE
            holder.binding.tvStatus.text = texto
            (holder.binding.tvStatus.background.mutate() as GradientDrawable).setColor(cor)
        }

        holder.binding.root.setOnClickListener { aoClicar(treino) }
    }

    private fun corETextoStatus(context: Context, status: StatusPresenca): Pair<Int, String> {
        return when (status) {
            StatusPresenca.CONFIRMADO ->
                ContextCompat.getColor(context, R.color.verde_confirmado) to
                    context.getString(R.string.status_confirmado)
            StatusPresenca.LISTA_ESPERA ->
                ContextCompat.getColor(context, R.color.amarelo_espera) to
                    context.getString(R.string.status_lista_espera)
            StatusPresenca.CANCELADO ->
                ContextCompat.getColor(context, R.color.vermelho_cancelado) to
                    context.getString(R.string.status_cancelado)
            StatusPresenca.NAO_MARCADO ->
                ContextCompat.getColor(context, R.color.roxo_academia) to
                    context.getString(R.string.status_nao_marcado)
        }
    }
}
