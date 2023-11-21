package es.vag.vmusic.main_menus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.vag.vmusic.R

class GenerosAdapter(private val generos: MutableList<Genero>, private val context: Context?, private  val mListener: (Genero) -> Unit):
    RecyclerView.Adapter<GenerosAdapter.GenerosViewHolder>(){

    override fun onBindViewHolder(holder: GenerosViewHolder, position: Int){
        val item = generos[position]
        holder.bindItem(item, context)
        holder.itemView.setOnClickListener{mListener(item)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenerosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_genero_layout,parent,false)
        return GenerosViewHolder(view)
    }


    override fun getItemCount(): Int{
        return generos.size
    }

    class GenerosViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvName: TextView = view.findViewById(R.id.nombre_genero)
        private val imagen: ImageView= view.findViewById(R.id.logo_genero)

        fun bindItem(genero: Genero, context: Context?){
            imagen.setImageResource(genero.imageid)
            tvName.text = genero.name

        }
    }
}