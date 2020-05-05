package com.example.wetoken_vf

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent.getIntent
import android.graphics.Color
import android.os.AsyncTask
import android.text.Editable
import android.text.TextWatcher
import android.text.method.KeyListener
import android.view.KeyEvent
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.nirigo.mobile.view.passcode.PasscodeView
import com.thekhaeng.pushdownanim.PushDownAnim
import java.util.concurrent.TimeUnit
import com.example.wetoken_vf.IOSPasscodeAdapter;
import com.nirigo.mobile.view.passcode.PasscodeIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ConnectVerifiFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ConnectVerifiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectVerifiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
lateinit var edittxtCode:EditText
    lateinit var btnVerif:Button
    lateinit var txtTel:TextView
    var codeSMS = ""
    lateinit var passcodeView:PasscodeView
    lateinit var passcodeIndicator:PasscodeIndicator

    private var yourCurrentPasscode = StringBuilder()

    private val SMS_CONSENT_REQUEST = 2  // Set to an unused request code



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
        val view = inflater.inflate(R.layout.fragment_connect_verifi, container, false)


        val sharedPrf = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val tel = sharedPrf?.getString("tel", "0")!!

        val code = sharedPrf?.getString("code", "0")!!
/*code
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btn5 = view.findViewById(R.id.btn5)
        btn6 = view.findViewById(R.id.btn6)

   */

        val iosPasscodeAdapter = IOSPasscodeAdapter(activity)

        passcodeIndicator = view.findViewById(R.id.passcode_indicator);
          passcodeView =  view.findViewById(R.id.passcode_view);
        passcodeView.setAdapter(iosPasscodeAdapter);

         passcodeView.setOnItemClickListener(object : PasscodeView.OnItemClickListener {
             override fun onItemClick(p0: PasscodeView?, p1: Int, p2: View?, p3: Any?) {
                 println(p1.toString() + " "  + p3.toString())
                 if(!passcodeIndicator.isAnimationInProgress()) {

                     yourCurrentPasscode.append(p3.toString());
                     passcodeIndicator.setIndicatorLevel(yourCurrentPasscode.length);
                     println(yourCurrentPasscode.toString())

                     if (yourCurrentPasscode.length == passcodeIndicator.getIndicatorLength()) {
                         println("here" + yourCurrentPasscode.toString())
                         //    if(yourCurrentPasscode.toString().equals("111111")){
                               if(yourCurrentPasscode.toString().equals(code))
                               {
                             Toast.makeText(context,"correct",Toast.LENGTH_SHORT).show()

                           //confirmerCompte(tel)



                              val connectVeriflFragment = SignUPlastnameFragment()
                               val bundle = Bundle();
                              bundle.putString("tel", arguments?.getString("tel"))
                                   // bundle.putString("lastname", edittxt1.text.toString())
                               connectVeriflFragment.arguments = bundle
                               val fragManager = activity?.supportFragmentManager
                               val transac = fragManager?.beginTransaction()
                               transac?.replace(R.id.fragmnt, connectVeriflFragment)
                               transac?.addToBackStack(null)
                               transac?.commit()


                       } else {

                             yourCurrentPasscode = StringBuilder();
                             passcodeIndicator.wrongPasscode()
                         }



                     }

                 }
             }


        });

        context?.let { SmsRetriever.getClient(it).startSmsUserConsent(tel) }

         val smsVerificationReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                    val extras = intent.extras
                    val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
                    println("status"+smsRetrieverStatus)
                    when (smsRetrieverStatus.statusCode) {
                        CommonStatusCodes.SUCCESS -> {
                            // Get consent intent
                            val consentIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                            try {
                                // Start activity to show consent dialog to user, activity must be started in
                                // 5 minutes, otherwise you'll receive another TIMEOUT intent
                                startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                            } catch (e: ActivityNotFoundException) {
                                // Handle the exception ...
                            }
                        }
                        CommonStatusCodes.TIMEOUT -> {
                            // Time out occurred, handle the error.
                        }
                    }
                }
            }
        }


      //  val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
     //   activity?.registerReceiver(smsVerificationReceiver, intentFilter)

/*code

        btn1.setOnFocusChangeListener(object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                btn1.post(Runnable {
                    if(activity?.getSystemService(Context.INPUT_METHOD_SERVICE)!=null) {
                        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(btn1, InputMethodManager.SHOW_IMPLICIT)
                    }
                })
            }

        })
        btn1.requestFocus()


        btn1.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){
                    btn1.setText("")
                    btn1.setBackgroundResource(R.drawable.circle_edittext)
                    btn1.setTextColor(Color.parseColor("#000000"))
                } else {
                    btn1.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn1.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn1.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn1.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn1.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn1.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn1.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn1.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn1.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn1.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn1.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn1.setText("9") }
                    btn2.requestFocus()
                }

                return true
            }

        })





        btn2.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){

                    btn2.setText("")
                    btn2.setBackgroundResource(R.drawable.circle_edittext)
                    btn2.setTextColor(Color.parseColor("#000000"))
                    btn1.requestFocus()
                } else {

                    btn2.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn2.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn2.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn2.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn2.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn2.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn2.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn2.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn2.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn2.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn2.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn2.setText("9") }
                    btn3.requestFocus()
                }
                return true
            }

        })

        btn3.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){

                    btn3.setText("")
                    btn3.setBackgroundResource(R.drawable.circle_edittext)
                    btn3.setTextColor(Color.parseColor("#000000"))
                    btn2.requestFocus()
                } else {

                    btn3.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn3.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn3.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn3.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn3.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn3.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn3.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn3.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn3.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn3.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn3.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn3.setText("9") }
                    btn4.requestFocus()

                }
                return true
            }

        })

        btn4.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){

                    btn4.setText("")
                    btn4.setBackgroundResource(R.drawable.circle_edittext)
                    btn4.setTextColor(Color.parseColor("#000000"))
                    btn3.requestFocus()
                } else {

                    btn4.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn4.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn4.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn4.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn4.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn4.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn4.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn4.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn4.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn4.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn4.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn4.setText("9") }
                    btn5.requestFocus()
                }
                return true
            }

        })

        btn5.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){

                    btn5.setText("")
                    btn5.setBackgroundResource(R.drawable.circle_edittext)
                    btn5.setTextColor(Color.parseColor("#000000"))
                    btn4.requestFocus()
                } else {

                    btn5.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn5.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn5.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn5.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn5.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn5.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn5.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn5.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn5.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn5.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn5.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn5.setText("9") }
                    btn6.requestFocus()
                }
                return true
            }

        })

        btn6.setOnKeyListener(object : KeyListener, View.OnKeyListener {
            override fun onKeyDown(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun onKeyUp(view: View?, text: Editable?, keyCode: Int, event: KeyEvent?): Boolean {
                return true }
            override fun getInputType(): Int { return 1 }
            override fun onKeyOther(view: View?, text: Editable?, event: KeyEvent?): Boolean {
                return true }
            override fun clearMetaKeyState(view: View?, content: Editable?, states: Int) {}

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_DEL){


                    btn6.setText("")
                    btn6.setBackgroundResource(R.drawable.circle_edittext)
                    btn6.setTextColor(Color.parseColor("#000000"))
                    btn5.requestFocus()
                } else {
                    btn6.setBackgroundResource(R.drawable.circle_edittext_full)
                    btn6.setTextColor(Color.parseColor("#FFFFFF"))
                    if (keyCode == KeyEvent.KEYCODE_0) { btn6.setText("0") }
                    if (keyCode == KeyEvent.KEYCODE_1) { btn6.setText("1") }
                    if (keyCode == KeyEvent.KEYCODE_2) { btn6.setText("2") }
                    if (keyCode == KeyEvent.KEYCODE_3) { btn6.setText("3") }
                    if (keyCode == KeyEvent.KEYCODE_4) { btn6.setText("4") }
                    if (keyCode == KeyEvent.KEYCODE_5) { btn6.setText("5") }
                    if (keyCode == KeyEvent.KEYCODE_6) { btn6.setText("6") }
                    if (keyCode == KeyEvent.KEYCODE_7) { btn6.setText("7") }
                    if (keyCode == KeyEvent.KEYCODE_8) { btn6.setText("8") }
                    if (keyCode == KeyEvent.KEYCODE_9) { btn6.setText("9") }

                    val code = btn1.text.toString() + btn2.text.toString()+btn3.text.toString() + btn4.text.toString()+btn5.text.toString()+btn6.text.toString()
                    println(code)
                    //codeSMS = "12345"
                    codeSMS = "222222"
                    if(code.equals(codeSMS)){
                        //Toast.makeText(context,"Code correcte",Toast.LENGTH_LONG).show()

                        val signUPAFragment = SignUPAFragment()
                        val bundle = Bundle();
                        bundle.putString("tel", arguments?.getString("tel"))
                        signUPAFragment.arguments = bundle

                        val fragManager = activity?.supportFragmentManager
                        val transac = fragManager?.beginTransaction()
                        transac?.replace(R.id.fragmnt, signUPAFragment)
                        transac?.addToBackStack(null)
                        transac?.commit()
                        //confirmerCompte(tel)
                    } else {
                        Toast.makeText(context,"Code incorrecte",Toast.LENGTH_LONG).show()
                    }

                }
                return true
            }

        })
code*/
        //val phoneNumber = "+21620862299"
        // val phoneNumber = "+21622477877"

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                println("onVerificationCompleted:$p0")
                Toast.makeText(context,"Message envoyé",Toast.LENGTH_SHORT).show()
                codeSMS = p0.smsCode.toString()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                println("error: "+p0)
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
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            tel, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            MainActivity(), // Activity (for callback binding)
            callbacks
        )




/*


        btnVerif = view.findViewById(R.id.btnVerif)
        txtTel=view.findViewById(R.id.txtTel)
        txtTel.text = tel









        btnVerif.setOnClickListener {
  if(code.equals(btnCode.text.toString()))
 {
     val connectVeriflFragment = SignUPAFragment()

     val fragManager = activity?.supportFragmentManager
     val transac = fragManager?.beginTransaction()
     transac?.replace(R.id.fragmnt, connectVeriflFragment)
     transac?.addToBackStack(null)
     transac?.commit()

 } else{
     //Toast.makeText(context,"code incorecte",Toast.LENGTH_SHORT).show()



      val connectVeriflFragment = SignUPAFragment()
      val fragManager = activity?.supportFragmentManager
      val transac = fragManager?.beginTransaction()
      transac?.replace(R.id.fragmnt, connectVeriflFragment)
      transac?.addToBackStack(null)
      transac?.commit()
 }

}
*/











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
* @return A new instance of fragment ConnectVerifiFragment.
*/
// TODO: Rename and change types and number of parameters
@JvmStatic
fun newInstance(param1: String, param2: String) =
 ConnectVerifiFragment().apply {
     arguments = Bundle().apply {
         putString(ARG_PARAM1, param1)
         putString(ARG_PARAM2, param2)
     }
 }
}

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // ...
            SMS_CONSENT_REQUEST ->
                // Obtain the phone number from the result
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Get SMS message content
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    // Extract one-time code from the message and complete verification
                    // `message` contains the entire text of the SMS message, so you will need
                    // to parse the string.
                    println(message)

                    // send one time code to the server
                } else {
                    // Consent denied. User can type OTC manually.
                }
        }
    }

}
