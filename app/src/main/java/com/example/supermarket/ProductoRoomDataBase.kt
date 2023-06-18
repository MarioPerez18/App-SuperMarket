package com.example.supermarket


import android.content.Context
import android.os.Bundle
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.provider.CalendarContract.Instances
//Aqui creo una instancia de la base de datos
@Database(entities = [(Productos::class)], version = 1)
abstract class ProductoRoomDataBase:RoomDatabase() {

    //funcion para que regrese un ProductoDao
    abstract fun productoDao():ProductoDao

    companion object{
        //obtener la base de datos
        private var INSTANCE:ProductoRoomDataBase?=null

        internal fun getDataBase(context: Context):ProductoRoomDataBase?{
            if(INSTANCE == null){
                synchronized((ProductoRoomDataBase::class.java)){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProductoRoomDataBase::class.java, "producto_database").build()
                    }
                }
            }
            return INSTANCE
        }



    }
}