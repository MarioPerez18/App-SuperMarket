package com.example.supermarket

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.supermarket.Productos

//Dao correspondiente para realizar operaciones en la tabla de productos
@Dao
interface ProductoDao {
    @Insert
    fun insertarProducto(producto:Productos)

    //Consulta para traer todos los campos de la tabla Prodcutos
    @Query("SELECT * FROM Productos")
    fun getAllProductos():LiveData<List<Productos>>

    //Consulta para eliminar un producto de la tabla
    @Query("DELETE FROM Productos WHERE Producto= :name")
    fun borrarProducto(name:String)





}