package com.example.wetoken_vf

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


internal lateinit var textcashout: TextView
internal lateinit var buttoncash: Button
internal lateinit var buttonbanktransfer: Button
internal lateinit var buttoncard: Button

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CashMenu.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CashMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class CashMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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
        return inflater.inflate(R.layout.fragment_cash_menu, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textcashout =  getView()!!.findViewById(R.id.textcashout)
        buttoncash =  getView()!!.findViewById(R.id.buttoncash)
        buttonbanktransfer =  getView()!!.findViewById(R.id.buttonbanktransfer)
        buttoncard =  getView()!!.findViewById(R.id.buttoncard)

        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")

        textcashout.typeface = tf3

        buttoncard.typeface = tf3
        buttonbanktransfer.typeface = tf3
        buttoncash.typeface = tf3
        buttoncash.setOnClickListener {


            val firstcache = FirstCash()
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, firstcache)
            transac?.addToBackStack(null)
            transac?.commit()







        }
           buttonbanktransfer.setOnClickListener{
                val frag = First_Bank_Transfer()
                val ft = fragmentManager!!.beginTransaction()
                ft.replace(R.id.fragmnt, frag)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.addToBackStack(null)
                ft.commit()

            }
         /*   buttoncard.setOnClickListener{
                val frag = CardFirstActivity()
                val ft = fragmentManager!!.beginTransaction()
                ft.replace(R.id.content_frame, frag)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.addToBackStack(null)
                ft.commit()

            }
    */

    }












    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }



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
         * @return A new instance of fragment CashMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CashMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
