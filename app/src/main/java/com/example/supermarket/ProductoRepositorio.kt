package com.example.supermarket


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*
import com.example.supermarket.Productos

class ProductoRepositorio(application: Application) {
    //variable de tipo MutableLiveData que contiene una lista de objetos de tipo Productos
    var results = MutableLiveData<List<Productos>>()
    //variable de tipo ProductoDao
    private var productoDao: ProductoDao?
    //variable que se utiliza para ejecutar corutinas
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    //variable de tipo LiveData que contiene una lista de objetos de tipo Productos
    val allProductos: LiveData<List<Productos>>?

    init {
        //variable para la instancia de la clase ProductRoomDataBase, que representa la base de datos
        val db: ProductoRoomDataBase? = ProductoRoomDataBase.getDataBase(application)
        productoDao = db?.productoDao()
        allProductos = productoDao?.getAllProductos()
    }


    fun insertarProductos(nuevoProducto: Productos){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsertar(nuevoProducto)
        }
    }

    //funcion para insertar
    fun asyncInsertar(producto: Productos){
        productoDao?.insertarProducto(producto)
    }

    //funcion para borrar productos
    fun borrarProducto(name:String){
        coroutineScope.launch(Dispatchers.IO){
            asyncBorrar(name)
        }
    }
    private fun asyncBorrar(name: String){
        productoDao?.borrarProducto(name)
    }




}