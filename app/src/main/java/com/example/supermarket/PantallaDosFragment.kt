package com.example.supermarket

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarket.databinding.FragmentPantallaDosBinding
import com.example.supermarket.databinding.FragmentPantallaTresBinding
import com.example.supermarket.ui.main.ListaProductos
import com.example.supermarket.ui.main.MainViewModel
import java.util.*
import com.example.supermarket.Productos


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PantallaDosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PantallaDosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var adapter:ListaProductos?=null

    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentPantallaDosBinding?=null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pantalla_dos, container, false)
        _binding= FragmentPantallaDosBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PantallaUnoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PantallaDosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Boton que tiene como acción la navehación de la pantalla dos a la pantalla tres
        binding.btnFinalizar.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaDosFragment_to_pantallaTresFragment)
        }
        listener()
        observer()
        recycler()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    //En esta funcion llamamos a nuestro boton mediante binding posteriormente le damos una accion
    //para que cuando el usuario ya haya ingresado los datos y dec clic se agreguen a la tabla
    private fun listener(){
        binding.btnAgregar.setOnClickListener {
            val nombre = binding.Nombreproducto.text.toString()
            val precio = binding.PrecioProducto.text.toString()
            if (nombre != "" && precio != ""){
                val producto = Productos(nombre, Integer.parseInt(precio))
                viewModel.insertarProductos(producto)
                Toast.makeText(context, "Se ha insertado el producto", Toast.LENGTH_LONG).show()
                clearFields()
            }else{
                Toast.makeText(context, "Falta contenido", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnEliminar.setOnClickListener {
            val nombre = binding.Nombreproducto.text.toString()
            if(nombre == ""){
                Toast.makeText(context, "Ingrese el nombre del producto a eliminar", Toast.LENGTH_LONG).show()
            }else{
                viewModel.borrarProducto(binding.Nombreproducto.text.toString())
            }
            clearFields()
        }
    }


    //Esta funcion hace que cambie cuando un objeto cambia su estado, notifica y se
    //actualiza automaticamente el objeto
    private fun observer(){
        viewModel.getAllProducts()?.observe(viewLifecycleOwner){
            productos->productos?.let {
                adapter?.setListaProductos(it)
            }
        }

        viewModel.getResults().observe(viewLifecycleOwner){
            productos -> productos?.let {
                if(it.isNotEmpty()){
                    binding.Nombreproducto.setText(it[0].nombreProducto)
                    binding.PrecioProducto.setText(String.format(Locale.US, "%d", it[0].precio))
                }else{

                }
            }
        }
    }

    private fun recycler(){
        adapter = ListaProductos(R.layout.items_productos)
        binding.productRecycler.layoutManager = LinearLayoutManager(context)
        binding.productRecycler.adapter = adapter
    }

    private fun clearFields(){
        binding.Nombreproducto.setText("")
        binding.PrecioProducto.setText("")
    }



















}