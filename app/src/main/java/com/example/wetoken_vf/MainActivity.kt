package com.example.wetoken_vf

import `in`.mayanknagwanshi.countrypicker.CountrySelectActivity
import `in`.mayanknagwanshi.countrypicker.bean.CountryData
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import com.airweb.contributionwenext.SharedPref
import com.airweb.contributionwenext.SharedPref1
import com.example.denden.changeLang
import com.example.wetoken_vf.mobile_connect.ConnectFragment
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    SMSReceiver.OTPReceiveListener {

    lateinit var photoURI: Uri

     var smsReceiver: SMSReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        val sharedPref1 = SharedPref1(this)
        val sharedPref = SharedPref(this)


        if (sharedPref1.getenglishmode() == true) {

            this.setTheme(R.style.english)

        } else
            this.setTheme(R.style.frensh)
       setSupportActionBar(toolbar)
        this.supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.customtoolbar)

         this.supportActionBar?.hide()

        this.window.statusBarColor = Color.parseColor("#FFFFFF")

        val view = supportActionBar?.customView
        val btnBack = view?.findViewById<ImageButton>(R.id.btnBack)
        val btnMenu = view?.findViewById<ImageButton>(R.id.btnMenu)
        btnBack?.visibility = View.GONE
        val txtTitle = view?.findViewById<TextView>(R.id.txtTitle)

       val builder =  StrictMode.VmPolicy.Builder()
           StrictMode.setVmPolicy(builder.build())

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val header = navigationView?.getHeaderView(0)
        photoURI = "no image".toUri()

      // val imgProfil= header?.findViewById(R.id.imgProfil) as ImageView;

        val pkgmanager = this.packageManager
        startSMSListener();
/*
        imgProfil.setOnClickListener {
            println("test")
          /* if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )

            } else {*/
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(pkgmanager)?.also {
                        //startActivityForResult(takePictureIntent, 1)
                        // Create the File where the photo should go
                        val photoFile: File? = try {
                            println("susces")
                            createImageFile()

                        } catch (ex: IOException) {
                            println(ex)
                            null
                        }
                        photoFile?.also {
                            photoURI = Uri.fromFile(createImageFile())
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(takePictureIntent, 1)
                        }
                    }
                }
           // }
        }*/

      ///  btnBack?.visibility = View.GONE
       //// btnMenu?.visibility = View.GONE
         //txtTitle?.setText("WeToken")

       /// btnBack?.setOnClickListener {
          ///  onBackPressed()
       /// }


        btnMenu?.setOnClickListener {

            if (changeLang.getLang(applicationContext) == "ar") {
                println(changeLang.getLang(applicationContext))
                drawer_layout.openDrawer(Gravity.RIGHT)
            } else {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }


        nav_view.setNavigationItemSelectedListener(this)


   /*
        }



                         */

        // val signin = AcceuilSalarie()
        // val signin = Signin()
val contribution = refund_position()
        val fragManager = supportFragmentManager
        val transac = fragManager.beginTransaction()
         transac.replace(R.id.fragmnt, contribution)
        //  transac.replace(R.id.fragmnt, connectFragment)
        transac.addToBackStack(null)
        transac.commit()

        val window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(Color.parseColor("#3629B7"));


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            //R.id.action_settings -> true
            R.id.English -> {
                println(Locale.getDefault().language + "---lnaa")
                var lcal = Locale("en")
                Locale.setDefault(lcal)
                var cnf = Configuration(this.resources?.configuration)
                //var cn       f = Configuration()
                cnf.setLocale(lcal)
                // activity?.baseContext?.createConfigurationContext(cnf)
                this.createConfigurationContext(cnf)
                changeLang.saveLang("en",this)
                this.recreate()
                return true
            }
            R.id.French -> {
                println("test")


                    println(Locale.getDefault().language + "---lnaa")
                    var lcal = Locale("fr")
                    Locale.setDefault(lcal)
                    var cnf = Configuration(this.resources?.configuration)
                    //var cn       f = Configuration()
                    cnf.setLocale(lcal)
                    // activity?.baseContext?.createConfigurationContext(cnf)
                    this.createConfigurationContext(cnf)
                    changeLang.saveLang("fr",this)
                    this.recreate()

                return true
            }



            R.id.spain -> {
                println("test")


                println(Locale.getDefault().language + "---lnaa")
                var lcal = Locale("sp")
                Locale.setDefault(lcal)
                var cnf = Configuration(this.resources?.configuration)
                //var cn       f = Configuration()
                cnf.setLocale(lcal)
                // activity?.baseContext?.createConfigurationContext(cnf)
                this.createConfigurationContext(cnf)
                changeLang.saveLang("sp",this)
                this.recreate()

                return true
            }




            R.id.Arab -> {
                println(Locale.getDefault().language + "---lnaa")
                var lcal = Locale("ar")
                Locale.setDefault(lcal)
                var cnf = Configuration(this.resources?.configuration)
                //var cn       f = Configuration()
                cnf.setLocale(lcal)
                // activity?.baseContext?.createConfigurationContext(cnf)
                this.createConfigurationContext(cnf)
                changeLang.saveLang("ar",this)
                this.recreate()
                return true
            }



            R.id.chinese -> {
                println(Locale.getDefault().language + "---lnaa")
                var lcal = Locale("ch")
                Locale.setDefault(lcal)
                var cnf = Configuration(this.resources?.configuration)
                //var cn       f = Configuration()
                cnf.setLocale(lcal)
                // activity?.baseContext?.createConfigurationContext(cnf)
                this.createConfigurationContext(cnf)
                changeLang.saveLang("ch",this)
                this.recreate()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(changeLang.attach(newBase!!,changeLang.getLang(newBase)))
    }
/*
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        changeLang.attach(this,"en")
    }
*/



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println(item.itemId)
        when(item.itemId){

            R.id.nav_home -> {
                val connectedHomeFragment = Salarie_Menu()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, connectedHomeFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }


            R.id.nav_Contribution_management -> {
                val connectedHomeFragment = ContributionManagment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, connectedHomeFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_Check_Hisorty -> {
                val newrefundFragment = NewrefundFragment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, newrefundFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }




            R.id.nav_Profit_management -> {
                val connectedHomeFragment = ConnectedHomeFragment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, connectedHomeFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_Performanece_bonus -> {
                val newrefundFragment = NewrefundFragment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, newrefundFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }



            R.id.nav_refund_history -> {
                val connectedHomeFragment = ConnectedHomeFragment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, connectedHomeFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_refund_new -> {
                val newrefundFragment = NewrefundFragment()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, newrefundFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }

            R.id.settings -> {
                val newrefundFragment = Settings()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, newrefundFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }



            R.id.nav_cacheinout -> {
                val newrefundFragment = CashMenu()
                val fragManager = supportFragmentManager
                val transac = fragManager.beginTransaction()
                transac.replace(R.id.fragmnt, newrefundFragment)
                transac.addToBackStack(null)
                transac.commit()
                drawer_layout.closeDrawer(GravityCompat.START)
            }





        }
        return true
    }


/*
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
       if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
           val countryData = data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData
           println(countryData.countryFlag)
           img.setImageResource(countryData.countryFlag)
           edittxtPays.setText(countryData.countryISD)
       }
   }*/



    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)!!
        println(storageDir.path + File.separator + timeStamp + ".jpg")
        return File(
            storageDir.path + File.separator + timeStamp + ".jpg" /* directory */
        )
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        println("here"+resultCode)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {


            // btnChoisirImage.setImageURI(photoURI)


            if (data != null) {
                if (data.extras?.get("data") != null) {
                    //val imageBitmap = data.extras.get("data") as Bitmap
                    //btnChoisirImage?.setImageBitmap(imageBitmap)
                }
            }
            val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
            val header = navigationView?.getHeaderView(0)
            photoURI = "no image".toUri()

            val imgProfil= header?.findViewById(R.id.imgProfil) as ImageView;
            imgProfil.setImageURI(photoURI)
            println("test" + photoURI)
            // btnChoisirImage?.setImageURI(photoURI)

        }
    }

    /*@Throws(IOException::class)
    //@Needs(Manifest.permission.WRITE_EXTERNAL_STORAGE)
      private fun createImageFile() : File{
        // Create an image file name
        val timeStamp= SimpleDateFormat("yyyyMMdd_HHmmss").format( Date());
        val imageFileName = "JPEG_" + timeStamp + "_";
        val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)!!


        // Save a file: path for use with ACTION_VIEW intents

        return File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );
    }*/
    */
    private fun startSMSListener() {
        try {
            val smsReceiver = SMSReceiver();
            smsReceiver.setOTPListener(this);

            val intentFilter = IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);

            val client = SmsRetriever.getClient(this);

            val task = client.startSmsRetriever();
            task.addOnSuccessListener(OnSuccessListener<Void>() {
                println(it)
            });

            task.addOnFailureListener(OnFailureListener() {
                println("error")
            });
        } catch (e:java.lang.Exception) {
            e.printStackTrace();
        }
    }


    override
    public fun onOTPReceived(otp:String) {
        showToast("OTP Received: " + otp);
        println(otp)
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);

        }
    }

override
    public fun onOTPTimeOut() {
        showToast("OTP Time out");
    }

override
    public fun onOTPReceivedError(error:String) {
        showToast(error);
    }


    override
    protected fun onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null
        }
    }


    private fun showToast(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
