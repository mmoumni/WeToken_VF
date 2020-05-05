package com.example.wetoken_vf

import android.Manifest
import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.hardware.fingerprint.FingerprintManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import com.airweb.contributionwenext.SharedPref
import com.airweb.contributionwenext.SharedPref1


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Settings.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var dateDialogFinal : AlertDialog

    lateinit var textviewchangerlalangue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_settings, container, false)


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


















        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val tf1 = Typeface.createFromAsset(activity!!.assets, "quicksand_regular.ttf")
        val tf2 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Medium.ttf")
        val tf3 = Typeface.createFromAsset(activity!!.assets, "Quicksand-Bold.ttf")

        val textviewreglages = getView()!!.findViewById<View>(R.id.textviewreglages) as TextView
        val textviewinformationspersonnelles =
            getView()!!.findViewById<View>(R.id.textviewinformationspersonnelles) as TextView
        val textviewchangerlalangue =
            getView()!!.findViewById<View>(R.id.textviewchangerlalangue) as TextView
        val textviewnightmode = getView()!!.findViewById<View>(R.id.textviewnightmode) as TextView
        val textviewchangepassword =
            getView()!!.findViewById<View>(R.id.textviewchangepassword) as TextView
        val textviewfingerprint =
            getView()!!.findViewById<View>(R.id.textviewfingerprint) as TextView
        val textviewsecurity = getView()!!.findViewById<View>(R.id.textviewsecurity) as TextView
        val l_personaldetails = getView()!!.findViewById<View>(R.id.l_personaldetails) as LinearLayout
        l_personaldetails.setOnClickListener {
           ////////passer au fragment personal details

                val personaldetails = personal_details_settings()
                 val fragManager = activity?.supportFragmentManager
                val transac = fragManager?.beginTransaction()
                transac?.replace(R.id.fragmnt, personaldetails)
                transac?.addToBackStack(null)
                transac?.commit()



        }


















        val l_changepassword  =
            getView()!!.findViewById<View>(R.id.l_changepass) as LinearLayout

        l_changepassword.setOnClickListener {
            ////////passer au fragment personal details

            val reset_password = fragment_reset__password__settings()
            val fragManager = activity?.supportFragmentManager
            val transac = fragManager?.beginTransaction()
            transac?.replace(R.id.fragmnt, reset_password)
            transac?.addToBackStack(null)
            transac?.commit()



        }






        val textviewcostumerservices = getView()!!.findViewById<View>(R.id.textviewcostumerservices) as TextView
        val textviewfacebook = getView()!!.findViewById<View>(R.id.textviewfacebook) as TextView
        val textviewinstagramm = getView()!!.findViewById<View>(R.id.textviewinstagramm) as TextView
        val textviewlinkedin = getView()!!.findViewById<View>(R.id.textviewlinkedin) as TextView
        val textviewyoutube = getView()!!.findViewById<View>(R.id.textviewyoutube) as TextView
        val textviewname = getView()!!.findViewById<View>(R.id.textviewname) as TextView
        val textlogout = getView()!!.findViewById<View>(R.id.textlogout) as TextView
        val switcherfingerprint = getView()!!.findViewById<View>(R.id.switcherfingerprint) as Switch
        val switchernightmode = getView()!!.findViewById<View>(R.id.switchernightmode) as Switch

        textlogout.setOnClickListener{
            /*val intent = Intent(activity!!, EmailActivityConnexion::class.java)

            startActivity(intent)*/

            /////passe au fragement de connction
        }










        textviewreglages.typeface = tf2
        textviewinformationspersonnelles.typeface = tf2
        textviewchangerlalangue.typeface = tf2
        textviewnightmode.typeface = tf2
        textviewchangepassword.typeface = tf2
        textviewfingerprint.typeface = tf2
        textviewsecurity.typeface = tf2
        textviewcostumerservices.typeface = tf2
        textviewfacebook.typeface = tf2
        textviewinstagramm.typeface = tf2
        textviewlinkedin.typeface = tf2
        textviewyoutube.typeface = tf2
        textviewname.typeface = tf3
        textlogout.typeface = tf3

        val sharedPref1:SharedPref1=SharedPref1(activity!!)
        val sharedPref:SharedPref=SharedPref(activity!!)

      val l_changerlalangue = getView()!!.findViewById<View>(R.id.l_changerlalangue) as LinearLayout
        l_changerlalangue.setOnClickListener(View.OnClickListener {







            val inflatr = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupview = inflatr.inflate(R.layout.popup_layout, null)
            val popup = PopupWindow(popupview, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            // popup.showAtLocation(,Gravity.CENTER,0,0)

            if (context != null) {
                var dialogview: View = layoutInflater.inflate(R.layout.popup_layout, null)
                var locationDialog = AlertDialog.Builder(activity!!,R.style.CustomAlertDialog);
                locationDialog.setCancelable(true)
                locationDialog.setView(dialogview);
                dateDialogFinal = locationDialog.create()
                dateDialogFinal.show()
               // var txtPopup = dialogview.findViewById(R.id.txtPopup) as TextView
                //txtPopup.text = getString(R.string.txtautoriseraccesposition)
                var btnOk = dialogview.findViewById(R.id.btnPopup) as Button
               var btnback = dialogview.findViewById(R.id.retour) as ImageView

               btnback.setOnClickListener {

                   dateDialogFinal.dismiss();
             }

                btnOk.setOnClickListener {
                }}

















        })


        if (sharedPref!!.loadNightModeState() == true) {
            switchernightmode.isChecked = true
        }

        switchernightmode.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedPref.setNightModeState(true)
                activity!!.recreate()


            } else {
                sharedPref.setNightModeState(false);
                activity!!.recreate()

            }
            sharedPref!!.setNightModeState(isChecked)




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
         * @return A new instance of fragment Settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
