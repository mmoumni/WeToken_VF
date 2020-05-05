package com.example.wetoken_vf.mobile_connect

import `in`.mayanknagwanshi.countrypicker.CountrySelectActivity
import `in`.mayanknagwanshi.countrypicker.bean.CountryData
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.wetoken_vf.ConnectVerifiFragment
import com.example.wetoken_vf.MainActivity

import com.example.wetoken_vf.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import android.R.string.no
import android.R.attr.name
import android.graphics.Color
import android.R.string.no
import android.R.attr.name
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import com.hbb20.CountryCodePicker


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ConnectFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ConnectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    lateinit var progbarSms:ProgressBar

      lateinit var ccp:CountryCodePicker
  lateinit var editTextCarrierNumber:EditText

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
        val view =  inflater.inflate(R.layout.fragment_connect, container, false)



        progbarSms = view.findViewById(R.id.progbarSms)


        ccp = view.findViewById(R.id.ccp);
        editTextCarrierNumber = view.findViewById(R.id.editText_carrierNumber);

        ccp.registerCarrierNumberEditText(editTextCarrierNumber);

        val imgClear = resources.getDrawable(R.drawable.erreur1,null)
       // val imgchecka = resources.getDrawable(R.drawable.check,null)



        editTextCarrierNumber.setOnFocusChangeListener(object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                editTextCarrierNumber.post(Runnable {
                    if(activity?.getSystemService(Context.INPUT_METHOD_SERVICE)!=null) {
                        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(editTextCarrierNumber, InputMethodManager.SHOW_IMPLICIT)
                    }
                })
            }
        })

        editTextCarrierNumber.requestFocus()






val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
        println("onVerificationCompleted:$p0")
        Toast.makeText(context,"Message envoyé",Toast.LENGTH_SHORT).show()
        println(p0.smsCode.toString())
           progbarSms.visibility = View.GONE
           val connectVeriflFragment = ConnectVerifiFragment()
        val bundle = Bundle();
        bundle.putString("code", p0.smsCode.toString())
        bundle.putString("tel",ccp.fullNumberWithPlus)
        connectVeriflFragment.arguments = bundle
        val fragManager = activity?.supportFragmentManager
        val transac = fragManager?.beginTransaction()
        transac?.replace(R.id.fragmnt, connectVeriflFragment)
        transac?.addToBackStack(null)
        transac?.commit()


    }

    override fun onVerificationFailed(p0: FirebaseException) {
        println("error: "+p0)
        progbarSms.visibility = View.GONE
        if (p0 is FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(context,"Probleme de connexion",Toast.LENGTH_LONG).show()
            // Invalid request
            // ...
        } else if (p0 is FirebaseTooManyRequestsException) {
            if(context!=null) {
                Toast.makeText(context,"Veuillez réessayer plus tard",Toast.LENGTH_LONG).show()
            }
            // The SMS quota for the project has been exceeded
            // ...
        }
    }


    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        println("onCodeSent:$verificationId")

        // Save verification ID and resending token so we can use them later

        println("token :"+token)
        // ...
    }
}


//sms code
//  val tel = "+21692569606"


        ccp.setPhoneNumberValidityChangeListener( CountryCodePicker.PhoneNumberValidityChangeListener() {
            if(it==false){
                editTextCarrierNumber.setCompoundDrawablesWithIntrinsicBounds(null,null,imgClear,null)
                progbarSms.visibility = View.GONE
            } else {
                editTextCarrierNumber.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null)
                progbarSms.visibility = View.VISIBLE
                val tel = ccp.fullNumberWithPlus
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    tel, // Phone number to verify
                    60, // Timeout duration
                    TimeUnit.SECONDS, // Unit of timeout
                    MainActivity(), // Activity (for callback binding)
                    callbacks
                )
            }
        });


return view
}


interface OnFragmentInteractionListener {
// TODO: Update argument type and name
fun onFragmentInteraction(uri: Uri)
}

companion object {

@JvmStatic
fun newInstance(param1: String, param2: String) =
    ConnectFragment().apply {
        arguments = Bundle().apply {
            putString(ARG_PARAM1, param1)
            putString(ARG_PARAM2, param2)
        }
    }
}


}
