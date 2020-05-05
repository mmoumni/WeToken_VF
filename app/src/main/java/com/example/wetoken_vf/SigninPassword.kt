package com.example.wetoken_vf

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SigninPassword.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SigninPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class SigninPassword : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var btnSend: Button

    lateinit var edittxtTel: EditText

    lateinit var retrofit: Retrofit
    lateinit var usersServices: UsersServices


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
        val view = inflater.inflate(R.layout.fragment_signin_password, container, false)


        (activity as AppCompatActivity).supportActionBar!!.hide()




        val appcmpatAct = activity as AppCompatActivity
        val viewToolbar = appcmpatAct.supportActionBar?.customView
        //val txtTitle = viewToolbar?.findViewById<TextView>(R.id.txtTitle)
        val btnBack = viewToolbar?.findViewById<ImageButton>(R.id.btnBack)
        btnBack?.visibility = View.VISIBLE




        btnSend = view.findViewById(R.id.btnnextA)


        edittxtTel = view.findViewById(R.id.texttt)


        retrofit = Retrofit.Builder().baseUrl(ApiUrl.url)
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(ScalarsConverterFactory.create())
            .build()
        usersServices = retrofit.create(UsersServices::class.java)





        edittxtTel.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                btnSend.visibility = View.VISIBLE

            }

        })



        btnSend.setOnClickListener {
            val email = arguments?.getString("email").toString()
                val password = edittxtTel.text.toString()
                 login(email,password)


            val acceuilFragment = aceuil_signin()

            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, acceuilFragment)
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

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SigninPassword().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    public fun login(mail:String,password: String){
        val call = usersServices.login(mail,password)
        call.enqueue(object : Callback<Reponse> {
            override fun onFailure(call: Call<Reponse>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<Reponse>, response: Response<Reponse>) {
                println(response)
                println("hi from the other side")
                println(response.body()?.token)
            }

        })
    }
}
