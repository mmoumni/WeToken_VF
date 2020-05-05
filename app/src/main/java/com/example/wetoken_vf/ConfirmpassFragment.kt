package com.example.wetoken_vf

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_password.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*






// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ConfirmpassFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ConfirmpassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfirmpassFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnnextA: Button

    lateinit var edittxt1: EditText

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
        val view =  inflater.inflate(R.layout.fragment_confirmpass, container, false)


        edittxt1 = view.findViewById(R.id.passw)
        btnnextA = view.findViewById(R.id.btnnext3)


        retrofit = Retrofit.Builder().baseUrl(ApiUrl.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        usersServices = retrofit.create(UsersServices::class.java)


        val bundle = Bundle();
        println("tel"+arguments?.getString("tel"))
        println("lastname"+arguments?.getString("lastname"))
        println("firstname"+arguments?.getString("firstname"))
        println("email"+arguments?.getString("email"))
        println("password"+arguments?.getString("password"))

        val STRING_CHARACTERS = "abcdefghijklmnopqrstuvwxyz"

        val eos_name = (1..12).map { STRING_CHARACTERS.random() }.joinToString("")

        edittxt1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                btnnextA.visibility = View.VISIBLE

            }

        })


        btnnextA.setOnClickListener {
            if(arguments?.getString("password").equals(edittxt1.text.toString())) {
          val email = arguments?.getString("email").toString()
                val password = arguments?.getString("password").toString()
                 val nom = arguments?.getString("lastname") + " " + arguments?.getString("firstname")
                 val tel = arguments?.getString("tel").toString()

                register(email,password,tel,nom,eos_name)



                val connectVeriflFragment = welcomwenext()

                val fragManager = activity?.supportFragmentManager
                val transac = fragManager?.beginTransaction()
                transac?.replace(R.id.fragmnt, connectVeriflFragment)
                transac?.addToBackStack(null)
                transac?.commit()




            } else {
                Toast.makeText(context,"Not the same password",Toast.LENGTH_SHORT).show()
            }


            /*
            val connectVeriflFragment = YouAreReadyFragment()

            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()
*/
        }


        return view

    }



    public fun register(mail:String,password: String,telephone:String,nom:String,eos_name:String){
        println(mail+"/"+password+"/"+telephone+"/"+nom+"/"+eos_name)
        val call = usersServices.register(mail,password,telephone,nom,true,"teleconseiller","aaaa",eos_name,"aaaaa")
        call.enqueue(object : Callback<Reponse>{
            override fun onFailure(call: Call<Reponse>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<Reponse>, response: Response<Reponse>) {
                println(response)

              if(context!=null)
               Toast.makeText(context,response.body()?.token,Toast.LENGTH_SHORT).show()


                val signin = Signin()
                val fragManager = activity?.supportFragmentManager
                val transac = fragManager?.beginTransaction()
                transac?.replace(R.id.fragmnt, signin)
                transac?.addToBackStack(null)
                transac?.commit()
            }

        })
    }

    }

