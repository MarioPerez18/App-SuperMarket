package com.example.supermarket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Clase que representa la entidad de los productos
@Entity(tableName = "Productos")
class Productos {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProductoID")
    var id:Int = 0
    @ColumnInfo(name = "Producto")
    var nombreProducto:String?=null

    @ColumnInfo(name = "Precio")
    var precio:Int= 0


    constructor(){}

    constructor(nombreProducto:String, precio:Int){
        this.nombreProducto = nombreProducto
        this.precio = precio
    }
}