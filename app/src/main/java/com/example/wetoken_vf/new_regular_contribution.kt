package com.example.wetoken_vf

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.airweb.contributionwenext.RetrofitInstance
import com.airweb.contributionwenext.RetrofitInterface
import com.airweb.contributionwenext.SharedPref
import com.airweb.contributionwenext.SharedPref1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [new_regular_contribution.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [new_regular_contribution.newInstance] factory method to
 * create an instance of this fragment.
 */
class new_regular_contribution : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    //internal lateinit var textviewcreateregularcontribution: TextView
    internal lateinit var textviewtothisaccount: TextView
    internal lateinit var textviewCTRdate: TextView
    internal lateinit var textviewdelay: TextView
    internal lateinit var textviewpurpose: TextView
    internal lateinit var textviewamount: TextView
    internal lateinit var edittextCTRdate: EditText
    internal lateinit var edittextdelay: EditText
    internal lateinit var btndone: Button
    internal lateinit var spinner1: Spinner
    internal lateinit var edittextpurpose: EditText
    internal lateinit var edittextamount: EditText
    internal val myCalendar = Calendar.getInstance()

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
        val view = inflater.inflate(R.layout.fragment_new_regular_contribution, container, false)

        val sharedPref1 = SharedPref1(activity!!)
        val sharedPref = SharedPref(activity!!)

        edittextCTRdate = view.findViewById(R.id.edittextCTRdate)
        edittextdelay = view.findViewById(R.id.edittextdelay)
      btndone = view.findViewById(R.id.btndone)
        spinner1 = view.findViewById(R.id.spinner1)
       edittextpurpose = view.findViewById(R.id.edittextpurpose)
      edittextamount= view.findViewById(R.id.edittextamount)


        if (sharedPref1.getenglishmode() == true) {

            activity!!.setTheme(R.style.english)

        } else
            activity!!.setTheme(R.style.frensh)


        if (sharedPref.loadNightModeState() == true) {

            activity!!.setTheme(R.style.darktheme)

        } else
            activity!!.setTheme(R.style.AppTheme)
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments








        return view
    }











    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")
        //textviewcreateregularcontribution = getView()!!.findViewById(R.id.textviewcreateregularcontribution)
        textviewtothisaccount = getView()!!.findViewById(R.id.textviewtothisaccount)
        textviewCTRdate = getView()!!.findViewById(R.id.textviewCTRdate)
        textviewdelay = getView()!!.findViewById(R.id.textviewdelay)
        textviewpurpose = getView()!!.findViewById(R.id.textviewpurpose)
        textviewamount = getView()!!.findViewById(R.id.textviewamount)
        edittextCTRdate = getView()!!.findViewById(R.id.edittextCTRdate)
        edittextdelay = getView()!!.findViewById(R.id.edittextdelay)
        btndone = getView()!!.findViewById(R.id.btndone)
        edittextpurpose = getView()!!.findViewById(R.id.edittextpurpose)
        edittextamount = getView()!!.findViewById(R.id.edittextamount)
        spinner1 = getView()!!.findViewById(R.id.spinner1)
        //textviewcreateregularcontribution.setTypeface(tf3)
       /* textviewtothisaccount.setTypeface(tf2)
        textviewCTRdate.setTypeface(tf2)
        textviewdelay.setTypeface(tf2)
        textviewpurpose.setTypeface(tf2)
        textviewamount.setTypeface(tf2)
        edittextCTRdate.setTypeface(tf2)
        edittextdelay.setTypeface(tf2)
        edittextpurpose.setTypeface(tf2)
        btndone.setTypeface(tf3)
        edittextamount.setTypeface(tf2)*/


        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            edittextCTRdate.isFocusableInTouchMode = true
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        edittextCTRdate.setOnClickListener {
            // TODO Auto-generated method stub
            val datePickerDialog = DatePickerDialog(
                activity!!,R.style.DatePickerStyle, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
            edittextdelay.requestFocus()
        }




        btndone.setOnClickListener {
            println(spinner1.selectedItem.toString())
            val service = RetrofitInstance.retrofitInstance.create(RetrofitInterface::class.java)
            val contributions = Contributions(
                "20",
                edittextpurpose.text.toString(),
                "mmoumni@wenext.io",
                spinner1.selectedItem.toString(),
                edittextamount.text.toString(),
                edittextdelay.text.toString(),
                edittextCTRdate.text.toString()
            )
            val call = service.createContributions(contributions)
            call.enqueue(object : Callback<Contributions> {
                override fun onResponse(
                    call: Call<Contributions>,
                    response: Response<Contributions>
                ) {
                    Toast.makeText(
                        activity,
                        "your contribution is created with success",
                        Toast.LENGTH_SHORT
                    ).show()
/*

                    val ft = fragmentManager!!.beginTransaction()
                    ft.replace(R.id.content_frame, frag)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    ft.addToBackStack(null)
                    ft.commit()

                    */


                }

                override fun onFailure(call: Call<Contributions>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        "Attente de vérification de votre compte " + t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }


    }

    private fun updateLabel() {
        val myFormat = "dd-MM-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)

        edittextCTRdate.setText(sdf.format(myCalendar.time))
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
         * @return A new instance of fragment new_regular_contribution.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            new_regular_contribution().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
