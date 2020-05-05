package com.example.wetoken_vf

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUPAFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUPAFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUPlastnameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btnSend: Button

    lateinit var edittxt1: EditText
lateinit var txtValid:TextView

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
        val view = inflater.inflate(R.layout.fragment_signup_lastname, container, false)

        edittxt1 = view.findViewById(R.id.what_s_yourl)



        val imgClear = resources.getDrawable(R.drawable.erreur1,null)
        val imgcheck = resources.getDrawable(R.drawable.check,null)

        btnSend = view.findViewById(R.id.btnnextL)



        btnSend.setOnClickListener {
            val connectVeriflFragment = SignUpFragment()
            val bundle = Bundle();
            bundle.putString("tel", arguments?.getString("tel"))
            bundle.putString("lastname", edittxt1.text.toString())
            connectVeriflFragment.arguments = bundle
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()



        }





        edittxt1.setOnFocusChangeListener(object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                edittxt1.post(Runnable {
                    if(activity?.getSystemService(Context.INPUT_METHOD_SERVICE)!=null) {
                        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(edittxt1, InputMethodManager.SHOW_IMPLICIT)
                    }
                })
            }
        })


        edittxt1.requestFocus()

























        edittxt1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(isOnlyAlphabet(edittxt1.text.toString())){


                edittxt1.setCompoundDrawablesWithIntrinsicBounds(null,null,imgcheck,null)
                btnSend.setBackgroundColor(Color.parseColor("#00BFFF"))

                btnSend.isEnabled = true










            }else {


                edittxt1.setCompoundDrawablesWithIntrinsicBounds(null,null,imgClear,null)
                btnSend.setBackgroundColor(Color.parseColor("#3100BFFF"))
                btnSend.isEnabled = false

}


            }

        })











        return view
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
         * @return A new instance of fragment SignUPAFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUPlastnameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun isOnlyAlphabet(str: String): Boolean {
        return (str != ""
                && str != null
                && str.length != null
                && str.length >= 2
                && str.matches("^[a-zA-Z]*$".toRegex())

                )
    }
}



