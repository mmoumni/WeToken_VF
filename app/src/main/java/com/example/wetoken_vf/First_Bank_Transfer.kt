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
 * [First_Bank_Transfer.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [First_Bank_Transfer.newInstance] factory method to
 * create an instance of this fragment.
 */
class First_Bank_Transfer : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    internal lateinit var text_euro: EditText
    internal lateinit var btnnext: Button
    internal lateinit var textcashout: TextView
    internal lateinit var textviewwetoken: TextView
    internal lateinit var textviewmaxamount: TextView

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
        return inflater.inflate(R.layout.fragment_first__bank__transfer, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")

        text_euro = getView()!!.findViewById(R.id.text_euro)
        btnnext = getView()!!.findViewById(R.id.btnnext)
        textcashout = getView()!!.findViewById(R.id.textcashout)
        textviewwetoken = getView()!!.findViewById(R.id.textviewwetoken)
        textviewmaxamount = getView()!!.findViewById(R.id.textviewmaxamount)


        textcashout.typeface = tf3
        text_euro.typeface = tf4
        textviewwetoken.typeface = tf1
        btnnext.typeface = tf3
        textviewmaxamount.typeface = tf1

        text_euro.requestFocus()

        /*   btnnext.setOnClickListener {


               val frag = SecondActivityBankTransfer()
               val bundle = Bundle()
               frag.arguments=bundle
               bundle.putString("text_euros",text_euro.text.toString())
               val ft = fragmentManager!!.beginTransaction()
               ft.replace(R.id.content_frame, frag)
               ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
               ft.addToBackStack(null)
               ft.commit()
               /*  val intent =
                     Intent(this@FirstActivityBankTransfer, SecondActivityBankTransfer::class.java)
                 intent.putExtra("text_euros", text_euro.text.toString())

                 startActivity(intent)*/
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
         * @return A new instance of fragment First_Bank_Transfer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            First_Bank_Transfer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
