package com.example.wetoken_vf

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airweb.contributionwenext.*
import com.daimajia.swipe.util.Attributes
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import kotlinx.android.synthetic.main.fragment_contribution_onoff_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Contribution_onoff_history.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Contribution_onoff_history.newInstance] factory method to
 * create an instance of this fragment.
 */
class Contribution_onoff_history : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    internal lateinit var recyclerView: RecyclerView
    internal var list: List<Contributions>? = ArrayList()
    internal lateinit var adapter: ContributionAdapterHistory

    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var mDataSet: ArrayList<String>


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
        return inflater.inflate(R.layout.fragment_contribution_onoff_history, container, false)
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = getView()!!.findViewById(R.id.recyclerHistory)
        recyclerView.setHasFixedSize(true)

        val recyclerView = getView()!!.findViewById(R.id.recyclerHistory) as RecyclerView

        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.itemAnimator = FadeInLeftAnimator()
        /*var mDataSet: ArrayList<String> = arrayListOf()
        mDataSet.add("paiement numero 1")
        mDataSet.add("paiement numero 2")

        mAdapter = RecyclerViewAdapter(context, mDataSet)
        (mAdapter as RecyclerViewAdapter).setMode(Attributes.Mode.Multiple)
        recyclerView.setAdapter(mAdapter)
        recyclerView.setOnScrollListener(onScrollListener)
*/

        /*val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                activity!!,
                R.drawable.divider
            )!!
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        val service = RetrofitInstance.retrofitInstance.create(RetrofitInterface::class.java)
        val call = service.getContributionsOneOff
        call.enqueue(object : Callback<Rows> {
            override fun onResponse(call: Call<Rows>, response: Response<Rows>) {
                list = response.body()!!.rows
                adapter = ContributionAdapterHistory(activity!!, list!!)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<Rows>, t: Throwable) {
                Toast.makeText(
                    activity!!,
                    "Attente de vérification de votre compte " + t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        */








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
         * @return A new instance of fragment Contribution_onoff_history.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Contribution_onoff_history().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    var onScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            println("onScrollStateChanged")
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // Could hide open views here if you wanted. //
        }
    }
}
