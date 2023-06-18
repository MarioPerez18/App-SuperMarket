package com.example.supermarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarket.databinding.FragmentMainBinding
import com.example.supermarket.databinding.FragmentPantallaDosBinding
import com.example.supermarket.databinding.FragmentPantallaTresBinding
import com.example.supermarket.ui.main.ListaProductos
import com.example.supermarket.ui.main.MainViewModel
import java.util.*
import com.example.supermarket.Productos
import androidx.lifecycle.Observer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PantallaTresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PantallaTresFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: MainViewModel by viewModels()
    private var adapter: ListaProductos?=null
    private var _binding: FragmentPantallaTresBinding?=null
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
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pantalla_tres, container, false)
        _binding= FragmentPantallaTresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegresaInicio.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaTresFragment_to_pantallaDosFragment)
        }

        binding.btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaTresFragment_to_mainFragment)
        }


        recycler()
        observer()



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PantallaTresFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PantallaTresFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    private fun recycler(){
        adapter = ListaProductos(R.layout.items_productos)
        binding.productRecycler.layoutManager = LinearLayoutManager(context)
        binding.productRecycler.adapter = adapter

    }

    private fun observer(){
        val datos: LiveData<List<Productos>> = viewModel.getAllProducts()!!
        var cont = 0

        viewModel.getAllProducts()?.observe(viewLifecycleOwner){
                productos->productos?.let {
            adapter?.setListaProductos(it)
            }
        }


        datos.observe(viewLifecycleOwner, Observer { listaProductos ->
            listaProductos?.let {
                // Recorrer los precios
                for (precio in it) {
                    //sumar los precios
                    cont += precio.precio
                }
                //asignar el total a mi textview
                binding.precioSuma.text = cont.toString() + " Pesos"
            }
        })
    }
}