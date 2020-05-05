package com.example.wetoken_vf

import `in`.mayanknagwanshi.countrypicker.CountrySelectActivity
import `in`.mayanknagwanshi.countrypicker.bean.CountryData
import android.app.Activity
import android.content.Context
import android.content.Intent
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
import android.widget.*
import com.example.wetoken_vf.mobile_connect.ConnectFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.R.attr.subtitle
import androidx.appcompat.app.AppCompatActivity



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Signin.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Signin.newInstance] factory method to
 * create an instance of this fragment.
 */
class Signin : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    lateinit var btnSend: Button

    lateinit var linear1: LinearLayout
    lateinit var creercompte: TextView
    lateinit var textt: TextView


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
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()



        val imgCleara = resources.getDrawable(R.drawable.erreur1,null)
        val imgcheck = resources.getDrawable(R.drawable.check,null)
        btnSend = view.findViewById(R.id.btnSend)



        textt = view.findViewById(R.id.editemail)
        creercompte = view.findViewById(R.id.createcompte)


        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()






        textt.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (textt.text.toString().trim().matches(emailPattern)) {


                    textt.setCompoundDrawablesWithIntrinsicBounds(null,null,imgcheck,null)
                    btnSend.setBackgroundColor(Color.parseColor("#00BFFF"))

                } else {


                    textt.setCompoundDrawablesWithIntrinsicBounds(null,null,imgCleara,null)
                    btnSend.setBackgroundColor(Color.parseColor("#3100BFFF"))

                }


            }

        })






        btnSend.setOnClickListener {

            val signinPassword = SigninPassword()
            val bundle = Bundle();
            bundle.putString("email", textt.text.toString())
            signinPassword.arguments = bundle
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, signinPassword)
            transac?.addToBackStack(null)
            transac?.commit()

        }





        creercompte.setOnClickListener {

            val connectFragment = ConnectFragment()
            val bundle = Bundle();

            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectFragment)
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
         * @return A new instance of fragment Signin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Signin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            val countryData =
                data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData
            println(countryData.countryFlag)
            img.setImageResource(countryData.countryFlag)
            edittxtPays.setText(countryData.countryISD)
        }


    }*/
}
