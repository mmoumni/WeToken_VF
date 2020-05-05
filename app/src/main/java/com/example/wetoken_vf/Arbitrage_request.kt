package com.example.wetoken_vf

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.airweb.contributionwenext.RetrofitInstance
import com.airweb.contributionwenext.RetrofitInterface
import com.airweb.contributionwenext.SharedPref
import com.airweb.contributionwenext.SharedPref1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Arbitrage_request.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Arbitrage_request.newInstance] factory method to
 * create an instance of this fragment.
 */
class Arbitrage_request : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    internal lateinit var textviewcreateregularcontribution: TextView
    internal lateinit var textviewtothisaccount: TextView
    internal lateinit var textviewCTRdate: TextView
    internal lateinit var btndone: Button

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




        val sharedPref1 = SharedPref1(activity!!)
        val sharedPref = SharedPref(activity!!)


        if (sharedPref1.getenglishmode() == true) {

            activity!!.setTheme(R.style.english)

        } else
            activity!!.setTheme(R.style.frensh)


        if (sharedPref.loadNightModeState() == true) {

            activity!!.setTheme(R.style.darktheme)

        } else
            activity!!.setTheme(R.style.AppTheme)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arbitrage_request, container, false)
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")
        val tf4 = Typeface.createFromAsset(activity!!.assets, "MiriamLibre-Bold.ttf")
        activity!!.title = "Management"
        textviewcreateregularcontribution =
            getView()!!.findViewById(R.id.textviewcreateregularcontribution)
        textviewtothisaccount = getView()!!.findViewById(R.id.textviewtothisaccount)
        textviewCTRdate = getView()!!.findViewById(R.id.textviewCTRdate)

        btndone = getView()!!.findViewById(R.id.btndone)

        textviewcreateregularcontribution.setTypeface(tf3)
        textviewtothisaccount.setTypeface(tf2)
        textviewCTRdate.setTypeface(tf2)

        btndone.setTypeface(tf3)






        btndone.setOnClickListener {
            val service = RetrofitInstance.retrofitInstance.create(RetrofitInterface::class.java)
            val Arbitrage = Arbitrage("1234","yassineamor4@wenext.io","WeToken","[{\"key\": \"ETH\", \"value\": 88 }, { \"key\": \"BTC\", \"value\": 12 } ]","2020"

            )
            val call = service.createarbitrage(Arbitrage)
            call.enqueue(object : Callback<Arbitrage> {
                override fun onResponse(
                    call: Call<Arbitrage>,
                    response: Response<Arbitrage>
                ) {
                    Toast.makeText(
                        activity,
                        "your Arbitrage is created with success",
                        Toast.LENGTH_SHORT
                    ).show()
                   /* val frag = HistoryFragment()


                    val ft = fragmentManager!!.beginTransaction()
                    ft.replace(R.id.content_frame, frag)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    ft.addToBackStack(null)
                    ft.commit()
                    */


                }

                override fun onFailure(call: Call<Arbitrage>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        "Attente de vérification de votre compte " + t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
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
         * @return A new instance of fragment Arbitrage_request.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Arbitrage_request().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
