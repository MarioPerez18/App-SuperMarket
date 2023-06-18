package com.example.supermarket.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.supermarket.ProductoRepositorio
import com.example.supermarket.Productos

class MainViewModel(application: Application): AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val repositorio: ProductoRepositorio = ProductoRepositorio(application)
    private val allProducts:LiveData<List<Productos>>?
    private val results:MutableLiveData<List<Productos>>


    init {
        allProducts = repositorio.allProductos
        results = repositorio.results
    }

    fun insertarProductos(productos:Productos){
        repositorio.insertarProductos(productos)
    }

    fun getResults():MutableLiveData<List<Productos>>{
        return results
    }

    fun getAllProducts(): LiveData<List<Productos>>?{
        return allProducts
    }

    fun borrarProducto(name:String){
        repositorio.borrarProducto(name)
    }





}