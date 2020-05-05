package com.example.wetoken_vf

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SixthCard.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SixthCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class SixthCard : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null



    internal lateinit var textviewonlinepayement: TextView
    internal lateinit var textviewatm: TextView
    internal lateinit var textviewfreeze: TextView
    internal lateinit var textviewlimitations: TextView
    internal lateinit var textviewresetpin: TextView
    internal lateinit var textviewOnlinePayement: TextView
    internal lateinit var textviewFreeze: TextView
    internal lateinit var textviewLimitations: TextView
    internal lateinit var textviewAtm: TextView
    internal lateinit var textviewresetPin: TextView




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
        return inflater.inflate(R.layout.fragment_sixth_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textviewonlinepayement = getView()!!.findViewById(R.id.textviewonlinepayement)
        textviewatm = getView()!!.findViewById(R.id.textviewatm)
        textviewfreeze = getView()!!.findViewById(R.id.textviewfreeze)
        textviewlimitations = getView()!!.findViewById(R.id.textviewlimitations)
        textviewresetpin = getView()!!.findViewById(R.id.textviewresetpin)
        textviewOnlinePayement = getView()!!.findViewById(R.id.textviewOnlinePayement)
        textviewFreeze = getView()!!.findViewById(R.id.textviewFreeze)
        textviewLimitations = getView()!!.findViewById(R.id.textviewLimitations)
        textviewAtm = getView()!!.findViewById(R.id.textviewAtm)
        textviewresetPin = getView()!!.findViewById(R.id.textviewresetPin)


        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")

        textviewonlinepayement.typeface = tf3
        textviewatm.typeface = tf3
        textviewfreeze.typeface = tf3
        textviewlimitations.typeface = tf3
        textviewresetpin.typeface = tf3

        textviewOnlinePayement.typeface = tf2
        textviewFreeze.typeface = tf2
        textviewLimitations.typeface = tf2
        textviewAtm.typeface = tf2
        textviewresetPin.typeface = tf2


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
         * @return A new instance of fragment SixthCard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SixthCard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
