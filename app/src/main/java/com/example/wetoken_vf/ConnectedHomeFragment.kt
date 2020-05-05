package com.example.wetoken_vf

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ConnectedHomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ConnectedHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectedHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    lateinit var recyclerTokens:RecyclerView
    var listTokens:ArrayList<TokenJSON> = arrayListOf()


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
        val view = inflater.inflate(R.layout.fragment_connected_home, container, false)


        val appcmpatAct = activity as AppCompatActivity
        val viewToolbar = appcmpatAct.supportActionBar?.customView
        val txtTitle = viewToolbar?.findViewById<TextView>(R.id.txtTitle)
        val btnBack = viewToolbar?.findViewById<ImageButton>(R.id.btnBack)
        val btnMenu = viewToolbar?.findViewById<ImageButton>(R.id.btnMenu)
        btnBack?.visibility = View.VISIBLE
        btnMenu?.visibility = View.VISIBLE
//viewToolbar?.setBackgroundColor(Color.parseColor("#4656D5"))
        recyclerTokens = view.findViewById(R.id.recyclerTokens)
        var t1 = TokenJSON()
        t1.currency = "Token"
        t1.nom = "WeToken"
        t1.value = "3000"
        t1.type = "Supply"
        listTokens.add(t1)
        listTokens.add(t1 )






        var t2 = TokenJSON()
        t2.currency = "ETH"
        t2.nom = "Token"
        t2.value = "108"
        t2.type = "Supply"
        listTokens.add(t2)
        listTokens.add(t2 )




        var t3 = TokenJSON()
        t3.currency = "BTC"
        t3.nom = "Token"
        t3.value = "181"
        t3.type = "Supply"
        listTokens.add(t3)
        listTokens.add(t3 )





        var t4 = TokenJSON()
        t4.currency = "Token"
        t4.nom = "WeToken"
        t4.value = "3000"
        t4.type = "Supply"
        listTokens.add(t4)
        listTokens.add(t4 )






        var t5 = TokenJSON()
        t5.currency = "ETH"
        t5.nom = "Token"
        t5.value = "108"
        t5.type = "Supply"
        listTokens.add(t5)
        listTokens.add(t5 )




        var t6 = TokenJSON()
        t6.currency = "BTC"
        t6.nom = "Token"
        t6.value = "181"
        t6.type = "Supply"
        listTokens.add(t6)
        listTokens.add(t6 )

       showListTokens()

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectedHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnectedHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    public fun showListTokens() {

            var adapter = TokenListAdapter(listTokens,context)
            if (context != null) {
                recyclerTokens.setLayoutManager(LinearLayoutManager(context));
                recyclerTokens.adapter = adapter
            }
        }
    }

