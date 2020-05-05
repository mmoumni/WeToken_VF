package com.example.wetoken_vf

import android.content.Context
import android.graphics.Color

import android.graphics.drawable.AnimatedVectorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.Holder
import com.orhanobut.dialogplus.OnItemClickListener
import com.orhanobut.dialogplus.ViewHolder
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airweb.contributionwenext.ContributionAdapterHistory
import com.airweb.contributionwenext.RetrofitInstance
import com.airweb.contributionwenext.RetrofitInterface
import com.cjj.sva.JJSearchView
import com.cjj.sva.anim.controller.JJChangeArrowController
import com.cjj.sva.anim.controller.JJCircleToSimpleLineController

import com.daimajia.swipe.util.Attributes;
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import dmax.dialog.SpotsDialog
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import kotlinx.android.synthetic.main.fragment_contribution_managment.*
import kotlinx.android.synthetic.main.swipe_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import java.util.ArrayList;
import java.util.Arrays;





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContributionManagment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContributionManagment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ContributionManagment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var btnRegular: TabItem
    lateinit var btnOneOff: TabItem
    lateinit var dialog: AlertDialog
    lateinit var service: RetrofitInterface
    internal lateinit var recyclerView: RecyclerView
    internal var list: List<Contributions>? = ArrayList()
    internal lateinit var adapter: ContributionAdapterHistory
    lateinit var btnAddRegular:FloatingActionButton
    lateinit var btnAddOneOff:FloatingActionButton
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var mDataSet: ArrayList<String>
    lateinit var dialogLoading: android.app.AlertDialog

    lateinit var mJJSearchView:JJSearchView
var clicked = false

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
        val view = inflater.inflate(R.layout.fragment_contribution_managment, container, false)

        dialogLoading = SpotsDialog.Builder().setContext(context).build()


        btnAddRegular = view.findViewById(R.id.btnAddRegular)
        btnAddRegular.setOnClickListener { view ->
            val connectVeriflFragment = new_regular_contribution()
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()
        }

        btnAddOneOff = view.findViewById(R.id.btnAddOneOff)
        btnAddOneOff.hide()
        btnAddOneOff.setOnClickListener { view ->
            val connectVeriflFragment = new_regular_contribution()
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, connectVeriflFragment)
            transac?.addToBackStack(null)
            transac?.commit()
        }

        mJJSearchView = view.findViewById(R.id.jjsv) as JJSearchView
          mJJSearchView.setController(JJCircle());

        mJJSearchView.setOnClickListener {
            if(!clicked)
            {
                mJJSearchView.startAnim()
                clicked = true
            } else {
                mJJSearchView.resetAnim()
                clicked = false
            }
        }

        recyclerView = view.findViewById(R.id.contributionRecycler)
        recyclerView.setHasFixedSize(true)

        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.itemAnimator = FadeInLeftAnimator()
        var mDataSet: ArrayList<String> = arrayListOf()


        val adapterRegular = ContributionsRegularAdapter(context, false, 4)
        val adapterOneOff = ContributionsRegularAdapter(context, false, 3)
        val holder: Holder
        holder = ViewHolder(R.layout.content)


        service = RetrofitInstance.retrofitInstance.create(RetrofitInterface::class.java)

        getRegularContributions()

        val tabs = view.findViewById(R.id.tabs) as TabLayout
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                println(position)
                if (position == 0) {
                    btnAddRegular.show()
                    btnAddOneOff.hide()
                    dialogLoading.show()
                    recyclerView.visibility = View.GONE
                    getRegularContributions()
                }
                if (position == 1) {
                    btnAddRegular.hide()
                    btnAddOneOff.show()
                    mJJSearchView.resetAnim();
                    dialogLoading.show()
                    recyclerView.visibility = View.GONE
                    getOneOffContributions()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
*/
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
         * @return A new instance of fragment ContributionManagment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContributionManagment().apply {
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

    fun getRegularContributions() {
        val call = service.contributions
        call.enqueue(object : Callback<Rows> {
            override fun onResponse(call: Call<Rows>, response: Response<Rows>) {
                dialogLoading.dismiss()
                recyclerView.visibility = View.VISIBLE
                list = response.body()!!.rows
                mAdapter = RecyclerViewAdapter(context, list, RecyclerViewAdapter.OnClickInterface { contribution, id ->

                    val inflatr = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val popupview = inflatr.inflate(R.layout.popup_layout, null)
                    val popup = PopupWindow(
                        popupview,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    if (context != null) {
                        var dialogview: View = layoutInflater.inflate(R.layout.dialogcontribution, null)
                        var locationDialog = AlertDialog.Builder(activity!!, R.style.CustomAlertDialog);
                        locationDialog.setCancelable(true)
                        locationDialog.setView(dialogview);
                        dialog = locationDialog.create()
                        dialog.show()



                       var sender = dialogview.findViewById(R.id.sender) as TextView
                        sender.setText(contribution.ctR_FROM)


                        var receiver = dialogview.findViewById(R.id.receiver) as TextView
                        receiver.setText(contribution.ctR_TO)



                        var prime = dialogview.findViewById(R.id.prime) as TextView
                        prime.setText(contribution.ctR_MOTIF)


                        var amount = dialogview.findViewById(R.id.amount) as TextView
                        amount.setText(contribution.ctR_AMOUNT)


                        var intervale = dialogview.findViewById(R.id.intervale) as TextView
                        intervale.setText(contribution.ctR_PLAGE)


                        var datee = dialogview.findViewById(R.id.datee) as TextView
                        datee.setText(contribution.ctR_DATE)


                        //txtPopup.text = getString(R.string.txtautoriseraccesposition)
                       // var btnOk = dialogview.findViewById(R.id.btnPopup) as Button
                        var btnback = dialogview.findViewById(R.id.retour) as ImageView

                     //   var txtAmount = dialogview.findViewById(R.id.txtAmounnt) as TextView
                        // txtAmount.setText(contribution.ctR_AMOUNT)

                        btnback.setOnClickListener {
                           dialog.dismiss();
                        }

                        //btnOk.setOnClickListener {
                        //}
                    }
                })
                recyclerView.adapter = mAdapter
            }

            override fun onFailure(call: Call<Rows>, t: Throwable) {
                dialogLoading.dismiss()
                Toast.makeText(
                    activity!!,
                    "Attente de vérification de votre compte " + t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun getOneOffContributions() {
        val call = service.getContributionsOneOff
        call.enqueue(object : Callback<Rows> {
            override fun onResponse(call: Call<Rows>, response: Response<Rows>) {
                dialogLoading.dismiss()
                recyclerView.visibility = View.VISIBLE
                list = response.body()!!.rows
                mAdapter = RecyclerViewAdapter(context, list, RecyclerViewAdapter.OnClickInterface { contribution, id ->

                    val inflatr = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val popupview = inflatr.inflate(R.layout.popup_layout, null)
                    val popup = PopupWindow(
                        popupview,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    if (context != null) {
                        var dialogview: View = layoutInflater.inflate(R.layout.dialogcontribution, null)
                        var locationDialog = AlertDialog.Builder(activity!!, R.style.CustomAlertDialog);
                        locationDialog.setCancelable(true)
                        locationDialog.setView(dialogview);
                        dialog = locationDialog.create()
                        dialog.show()
                        // var txtPopup = dialogview.findViewById(R.id.txtPopup) as TextView
                        //txtPopup.text = getString(R.string.txtautoriseraccesposition)
                        var btnOk = dialogview.findViewById(R.id.btnPopup) as Button
                        var btnback = dialogview.findViewById(R.id.retour) as ImageView

                        var txtAmount = dialogview.findViewById(R.id.txtAmounnt) as TextView
                        txtAmount.setText(contribution.ctR_AMOUNT)

                        btnback.setOnClickListener {
                            dialog.dismiss();
                        }

                        btnOk.setOnClickListener {
                        }
                    }
                })
                recyclerView.adapter = mAdapter
            }

            override fun onFailure(call: Call<Rows>, t: Throwable) {
                dialogLoading.dismiss()
                Toast.makeText(
                    activity!!,
                    "Attente de vérification de votre compte " + t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }



}
