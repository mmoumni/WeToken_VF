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
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Third_card.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Third_card.newInstance] factory method to
 * create an instance of this fragment.
 */
class Third_card : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null



    internal lateinit var textviewcountry: TextView
    internal lateinit var edittextcountry: EditText
    internal lateinit var textviewpostalcode: TextView
    internal lateinit var edittextpostalcode: EditText
    internal lateinit var textviewAddressLine: TextView
    internal lateinit var edittextaddresslineone: EditText
    internal lateinit var textviewAddressLine2: TextView
    internal lateinit var edittextaddresslinetwo: EditText
    internal lateinit var textviewcity: TextView
    internal lateinit var edittextcity: EditText
    internal lateinit var btncontinue: Button
    internal lateinit var textaddress: TextView

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
        return inflater.inflate(R.layout.fragment_third_card, container, false)
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textviewcountry = getView()!!.findViewById(R.id.textviewcountry)
        edittextcountry = getView()!!.findViewById(R.id.edittextcountry)
        textviewpostalcode = getView()!!.findViewById(R.id.textviewpostalcode)
        edittextpostalcode = getView()!!.findViewById(R.id.edittextpostalcode)
        textviewAddressLine = getView()!!.findViewById(R.id.textviewAddressLine)
        edittextaddresslineone = getView()!!.findViewById(R.id.edittextaddresslineone)
        textviewAddressLine2 = getView()!!.findViewById(R.id.textviewAddressLine2)
        edittextaddresslinetwo = getView()!!.findViewById(R.id.edittextaddresslinetwo)
        textviewcity = getView()!!.findViewById(R.id.textviewcity)
        edittextcity = getView()!!.findViewById(R.id.edittextcity)
        btncontinue = getView()!!.findViewById(R.id.btncontinue)
        textaddress = getView()!!.findViewById(R.id.textaddress)


        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")

        textviewcountry.typeface = tf2
        edittextcountry.typeface = tf2
        textviewpostalcode.typeface = tf2
        edittextpostalcode.typeface = tf2
        textviewAddressLine.typeface = tf2
        edittextaddresslineone.typeface = tf2
        edittextaddresslinetwo.typeface = tf2
        textviewAddressLine2.typeface = tf2
        textviewcity.typeface = tf2
        edittextcity.typeface = tf2
        btncontinue.typeface = tf3
        textaddress.typeface = tf3


        btncontinue.setOnClickListener {

            val frag = FifthCard()
            val bundle = Bundle()
            frag.arguments=bundle
            bundle.putString("edittextcountry",edittextcountry.text.toString())
            bundle.putString("edittextpostalcode",edittextpostalcode.text.toString())
            bundle.putString("edittextaddresslineone",edittextaddresslineone.text.toString())
            bundle.putString("edittextaddresslinetwo",edittextaddresslinetwo.text.toString())
            bundle.putString("edittextcity",edittextcity.text.toString())

            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.fragmnt, frag)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(null)
            ft.commit()










        }







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
         * @return A new instance of fragment Third_card.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Third_card().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
