package com.example.wetoken_vf

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PasswordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var txtUp: TextView
    lateinit var btnnextA: Button
    lateinit var editTxtPass:TextInputEditText
    var textDown = ""

    lateinit var edittxt1: EditText
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
        val view =  inflater.inflate(R.layout.fragment_password, container, false)


        edittxt1 = view.findViewById(R.id.texttt)
        btnnextA = view.findViewById(R.id.btnnextA)


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





        btnnextA.setOnClickListener {

            val connectVeriflFragment = ConfirmpassFragment()
            val bundle = Bundle();
            bundle.putString("tel", arguments?.getString("tel"))
            bundle.putString("lastname", arguments?.getString("lastname"))
            bundle.putString("firstname", arguments?.getString("firstname"))
            bundle.putString("email", arguments?.getString("email"))
            bundle.putString("password", editTxtPass.text.toString())
            connectVeriflFragment.arguments = bundle
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()

        }



        edittxt1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                btnnextA.visibility = View.VISIBLE

            }

        })



        val img1 = view.findViewById(R.id.img1) as ImageView
        val img2 = view.findViewById(R.id.img2) as ImageView
        val img3 = view.findViewById(R.id.img3) as ImageView
        val img4 = view.findViewById(R.id.img4) as ImageView
        val img5 = view.findViewById(R.id.img5) as ImageView
        txtUp = view.findViewById(R.id.txtUp)
        val txtDown = view.findViewById(R.id.txtDown) as TextView
        val mustContain = changeColor("Must contain : ", "#00000")
        val upperTxt = changeColor("1 capital letter", "#EE0A0A")
        val lowerTxt = changeColor(",1 lower case letter", "#EE0A0A")
        val numberTxt = changeColor(",1 number", "#EE0A0A")
        val specialTxt = changeColor(",1 special character", "#EE0A0A")
        txtUp.setText(Html.fromHtml(upperTxt+lowerTxt+numberTxt+specialTxt));



        editTxtPass = view.findViewById(R.id.texttt)
        editTxtPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getStrength(s.toString())
                if (s.toString().length == 0) {
                    txtDown.setText("")


                    img1.setImageResource(R.drawable.rectangle2)
                    img2.setImageResource(R.drawable.rectangle2)
                    img3.setImageResource(R.drawable.rectangle2)
                    img4.setImageResource(R.drawable.rectangle2)
                    img5.setImageResource(R.drawable.rectangle2)
                } else
                    if (getStrength(s.toString()) > 1 && getStrength(s.toString()) < 5) {
                        txtDown.setText("Very weak")
                        txtUp.setText("")
                        txtDown.setTextColor(Color.RED)
                        img1.setImageResource(R.drawable.rectangle_red)
                        img2.setImageResource(R.drawable.rectangle2)
                        img3.setImageResource(R.drawable.rectangle2)
                        img4.setImageResource(R.drawable.rectangle2)
                        img5.setImageResource(R.drawable.rectangle2)
                        btnnextA.isEnabled = false
                    } else if (getStrength(s.toString()) > 5 && getStrength(s.toString()) < 100) {
                        txtDown.setText("Weak")
                        txtDown.setTextColor(Color.parseColor("#F0B80E"))
                        println(getStrength(s.toString()))
                        img1.setImageResource(R.drawable.rectangle_orange)
                        img2.setImageResource(R.drawable.rectangle_orange)
                        img3.setImageResource(R.drawable.rectangle2)
                        img4.setImageResource(R.drawable.rectangle2)
                        img5.setImageResource(R.drawable.rectangle2)
                        btnnextA.isEnabled = false
                    } else if (getStrength(s.toString()) > 101 && getStrength(s.toString()) < 111 && s.toString().length>2) {
                        txtDown.setText("Should be better")
                        txtDown.setTextColor(Color.parseColor("#F0B80E"))
                        img1.setImageResource(R.drawable.rectangle_orange)
                        img2.setImageResource(R.drawable.rectangle_orange)
                        img3.setImageResource(R.drawable.rectangle_orange)
                        img4.setImageResource(R.drawable.rectangle2)
                        img5.setImageResource(R.drawable.rectangle2)
                        btnnextA.isEnabled = false
                    } else
                        if (getStrength(s.toString()) > 110 && getStrength(s.toString()) < 1110 && s.toString().length>3) {
                            txtDown.setText("Secure")
                            txtDown.setTextColor(Color.parseColor("#035C07"))
                            img1.setImageResource(R.drawable.rectangle_green)
                            img2.setImageResource(R.drawable.rectangle_green)
                            img3.setImageResource(R.drawable.rectangle_green)
                            img4.setImageResource(R.drawable.rectangle_green)
                            img5.setImageResource(R.drawable.rectangle2)
                            btnnextA.isEnabled = true
                        } else
                            if (getStrength(s.toString()) > 1110 && s.toString().length>5) {
                                txtDown.setText("Super secure")
                                txtDown.setTextColor(Color.parseColor("#035C07"))
                                img1.setImageResource(R.drawable.rectangle_green)
                                img2.setImageResource(R.drawable.rectangle_green)
                                img3.setImageResource(R.drawable.rectangle_green)
                                img4.setImageResource(R.drawable.rectangle_green)
                                img5.setImageResource(R.drawable.rectangle_green)
                                btnnextA.isEnabled = true
                            } else {
                            }
            }
        })


        return view

    }




    fun getStrength(pass: String): Int {
        var score = 0
        var i = 0

        var mustContain = changeColor("Must contain : ", "#00000")
        var upperTxt = changeColor("1 capital letter", "#EE0A0A")
        var lowerTxt = changeColor(",1 lower case letter", "#EE0A0A")
        var numberTxt = changeColor(",1 number", "#EE0A0A")
        var specialTxt = changeColor(",1 special character", "#EE0A0A")


        for (c in pass) {
            // If password contains lowercase letters
            if (c >= 'a' && c <= 'z') {
                lowerTxt = changeColor(",1 lower case letter", "#00000")
                score = score + 1;
            }
            // If password contains uppercase letters
            else if (c >= 'A' && c <= 'Z') {
                upperTxt = changeColor("1 capital letter", "#00000")
                score = score + 10;
            }
            // If password contains numbers
            else if (c >= '0' && c <= '9') {
                numberTxt = changeColor(",1 number", "#00000")
                score = score + 100;
            }
            // If password contains special chars
            else {
                specialTxt = changeColor(",1 special character", "#00000")
                score = score + 1000;
            }
            i++
        }




        txtUp.setText(Html.fromHtml(upperTxt+lowerTxt+numberTxt+specialTxt));
        return score
    }

    private fun changeColor(text: String, color: String): String {
        return "<quicksand color=$color>$text</quicksand>"
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
            PasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
