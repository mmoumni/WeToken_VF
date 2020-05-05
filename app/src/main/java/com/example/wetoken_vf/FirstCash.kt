package com.example.wetoken_vf

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.google.zxing.EncodeHintType
import java.util.HashMap
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FirstCash.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FirstCash.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstCash : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null




    internal lateinit var text_euro: EditText
    internal lateinit var btncashout: Button
    internal lateinit var textviewmaxamount: TextView
    internal lateinit var textcashout: TextView
    internal lateinit var imageviewcodebarre: ImageView

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
        return inflater.inflate(R.layout.fragment_first_cash, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        text_euro =  getView()!!.findViewById(R.id.text_euro)
        btncashout =  getView()!!.findViewById(R.id.btncashout)
        textcashout =  getView()!!.findViewById(R.id.textcashout)
        imageviewcodebarre =  getView()!!.findViewById(R.id.imageviewcodebarre)

        textviewmaxamount =  getView()!!.findViewById(R.id.textviewmaxamount)

        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")

        text_euro.typeface = tf4
        btncashout.typeface = tf3
        textviewmaxamount.typeface = tf1
        text_euro.requestFocus()
        textcashout.typeface = tf3
        text_euro.requestFocus()



        text_euro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (text_euro.text.toString().trim { it <= ' ' } != "") {
                    val multiFormatWriter = MultiFormatWriter()
                    try {
                        val hintMap = HashMap<EncodeHintType, Any>()
                        hintMap[EncodeHintType.MARGIN] = 1
                        val barcodeEncoder = BarcodeEncoder()
                        val bitMatrix = multiFormatWriter.encode(
                            text_euro.text.toString(),
                            com.google.zxing.BarcodeFormat.QR_CODE,
                            800,
                            800,
                            hintMap
                        )

                        val bitmap = barcodeEncoder.createBitmap(bitMatrix)
                        imageviewcodebarre.setImageBitmap(bitmap)


                    } catch (e: WriterException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        }
        )


       btncashout.setOnClickListener {
            if (Integer.parseInt(text_euro.text.toString()) <= 300 && text_euro.text.toString() != null) {

                val connectVeriflFragment = Second_card()
                val bundle = Bundle();
                bundle.putString("text_euros", text_euro.text.toString())
                connectVeriflFragment.arguments = bundle
                val fragManager = activity?.supportFragmentManager
                val transac = fragManager?.beginTransaction()
                transac?.replace(R.id.fragmnt, connectVeriflFragment)
                transac?.addToBackStack(null)
                transac?.commit()
            } else {
                Toast.makeText(
                    activity, "Le montant ne doit dépasser les 300 euros",
                    Toast.LENGTH_LONG
                ).show()
            }
















        }
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
         * @return A new instance of fragment FirstCash.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstCash().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
