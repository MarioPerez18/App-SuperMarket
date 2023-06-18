package com.example.supermarket.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarket.ProductoDao
import com.example.supermarket.R
import com.example.supermarket.Productos


class ListaProductos(private val itemLista: Int):
    RecyclerView.Adapter<ListaProductos.ViewHolder>() {
    private var listaProduct:List<Productos>?=null

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        var item: TextView = item.findViewById(R.id.fila_producto)
        var itemDos: TextView = item.findViewById(R.id.fila_precio)


    }

    //funcion para crear una nueva instancia del elemento vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(itemLista, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SuspiciousIdentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = holder.item
        listaProduct.let {
            item.text = it!![position].nombreProducto
        }

        val itemDos = holder.itemDos
        listaProduct.let {
            itemDos.text = it!![position].precio.toString()
        }



        //val currentUserInput = Listaproductos?.get(position)

        //Establecer
        //holder.textProducto.text = currentUserInput?.nombreProducto
        //holder.textProducto.text = currentUserInput?.precio.toString()


    }

    override fun getItemCount(): Int {
        return if (listaProduct == null) 0 else listaProduct!!.size

    }

    fun setListaProductos(productos: List<Productos>){
        listaProduct = productos
        notifyDataSetChanged()
    }




}




