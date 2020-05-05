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
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_password.*
import android.R.attr.button
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUp1Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUp1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnnextA: Button
    lateinit var textt: EditText





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
        val view = inflater.inflate(R.layout.fragment_sign_up1, container, false)


        textt = view.findViewById(R.id.email)
        btnnextA = view.findViewById(R.id.btnnextA)





        textt.setOnFocusChangeListener(object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                textt.post(Runnable {
                    if(activity?.getSystemService(Context.INPUT_METHOD_SERVICE)!=null) {
                        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(textt, InputMethodManager.SHOW_IMPLICIT)
                    }
                })
            }
        })


        textt.requestFocus()

















        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()


        val imgClear = resources.getDrawable(R.drawable.erreur1,null)
        val imgcheck = resources.getDrawable(R.drawable.check,null)

        textt.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (textt.text.toString().trim().matches(emailPattern)) {


                    textt.setCompoundDrawablesWithIntrinsicBounds(null,null,imgcheck,null)
                    btnnextA.setBackgroundColor(Color.parseColor("#00BFFF"))

                    btnnextA.isEnabled = true
                } else {


                    textt.setCompoundDrawablesWithIntrinsicBounds(null,null,imgClear,null)
                    btnnextA.setBackgroundColor(Color.parseColor("#3100BFFF"))
                    btnnextA.isEnabled = false

                }


            }

        })





        btnnextA.setOnClickListener {

            val connectVeriflFragment = PasswordFragment()
            val bundle = Bundle();
            bundle.putString("tel", arguments?.getString("tel"))
            bundle.putString("lastname", arguments?.getString("lastname"))
            bundle.putString("firstname", arguments?.getString("firstname"))
            bundle.putString("email", textt.text.toString())
            connectVeriflFragment.arguments = bundle
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()

        }

        return view
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


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
         * @return A new instance of fragment SignUp1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}
