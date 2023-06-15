package com.kamel.fardi.formulaonear.View.Fragement
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import com.kamel.fardi.formulaonear.MyPagerAdapter
import com.kamel.fardi.formulaonear.R
import com.kamel.fardi.formulaonear.Utils.*
import com.kamel.fardi.formulaonear.ViewModel.UserViewModel
import com.kamel.fardi.formulaonear.View.Activity.Login
import java.io.*
import java.util.*


class ProfileFragment : Fragment() {
    ///
    private lateinit var MySharedPref: SharedPreferences
    private lateinit var MyName: TextView
    private lateinit var txtBio:TextView
    private lateinit var MyToolbar: Toolbar
    private lateinit var btnEditProfile : Button
    //
    private lateinit var userAvatar: ImageView
    private lateinit var tabLayoutProfile : TabLayout
    private lateinit var viewpagerProfile : ViewPager2
    //
    private var imgUri: Uri? = null
    var viewModell = UserViewModel()
    private val STORAGE_PERMISSION_CODE = 111
    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //
        initView();
        SetUserData();
        SetToolbar()
        OpenGalery()
        SetTabPager()
        OpenEditProfile()
    }

    fun OpenEditProfile()
    {
        btnEditProfile.setOnClickListener{
            //fragmentManager?.beginTransaction()?.replace(R.id.container, EditProfil())?.commit()
            fragmentManager?.beginTransaction()?.replace(R.id.container, EditProfil())?.addToBackStack("Edit")?.commit()
        }
    }

    fun SetTabPager()
    {
        val myPagerAdapter = MyPagerAdapter(requireActivity())
        viewpagerProfile.setAdapter(myPagerAdapter)
        tabLayoutProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpagerProfile.setCurrentItem(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewpagerProfile.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayoutProfile.getTabAt(position)!!.select();
                if (position == 0) {
                    // you are on the first page
                }
                else if (position == 1) {
                    // you are on the second page
                }
                super.onPageSelected(position)
            }
        })
    }

    fun OpenGalery()
    {
        userAvatar = requireView().findViewById(R.id.userAvatar)
        userAvatar.setOnClickListener {
            openGallery()
        }
    }

    fun initView() {
        MySharedPref =requireContext().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        MyName = requireView().findViewById(R.id.txtUser)
        txtBio = requireView().findViewById(R.id.txtBio)
        userAvatar = requireView().findViewById(R.id.userAvatar)
        btnEditProfile = requireView().findViewById(R.id.btnEditProfile)
        btnEditProfile.setBackgroundResource(R.drawable.btn_dark); // Set Button Style @Null on the xml
        //
        tabLayoutProfile = requireView().findViewById(R.id.tabLayoutProfile)
        viewpagerProfile = requireView().findViewById(R.id.view_pageProfile)
    }

    fun SetUserData() {
        val nameUser = MySharedPref.getString(NAMEUSER, null)
        val avatarUser = MySharedPref.getString(AVATARUSER, null)

        var NameUserMajuscule = nameUser?.substring(0,1)?.toUpperCase() + nameUser?.substring(1)?.toLowerCase();
        MyName.text = NameUserMajuscule
        //
        val bioUser = MySharedPref.getString(BIOUSER, null)
        txtBio.text = bioUser

        val ImagelINKAvatar = ("https://serverdiscovery.onrender.com/imaguser/"+avatarUser)

        //
        if(avatarUser!!.toLowerCase()!=nameUser!!.toLowerCase()) {
            Glide
                .with(requireContext())
                .load(ImagelINKAvatar)
                .fitCenter()
                .error(R.drawable.avatar)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Log.d(TAG, "onLoadFailed")
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        Log.d(TAG, "OnResourceReady")

                        return false
                    }
                }).into(userAvatar);
        }
        else{
            userAvatar.setImageResource(R.drawable.avatar)
        }

    }
    fun SetToolbar()
    {
        MyToolbar = requireView().findViewById(R.id.toolbarProfile)
        (activity as AppCompatActivity).setSupportActionBar(MyToolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        //
        MySharedPref =
            requireContext().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        val Role = MySharedPref.getString(ROLEUSER, null)
        if(Role=="Admin") {
            MyToolbar.setNavigationIcon(R.drawable.ic_username);
        }
        //MyToolbar.setNavigationIcon(R.drawable.ic_adminpanel);
        /*MyToolbar.setNavigationOnClickListener{
            println("Hamdoulllaaaaaah")
            val intent = Intent(context, AdminPanel::class.java)
            context?.startActivity(intent)
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> Logout()
        }
        return super.onOptionsItemSelected(item)
    }
    fun Hamdoullah()
    {
        println("Hamdoullllaaah ")
    }

    fun Logout() {
        println("Logout CLicked !!")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation!")
        builder.setMessage("Are you sure do want to logout?")
        builder.setPositiveButton("Yes"){ dialogInterface, which ->
            //
            MySharedPref.edit().apply {
                putString(RememberEmail, "")
                putString(RememberPassword, "")
            }.apply()
            activity?.let{
                val intent = Intent (it, Login::class.java)
                it.startActivity(intent)
                it.finish()
            }
        }
        builder.setNegativeButton("No"){dialogInterface, which ->
            dialogInterface.dismiss()
        }
        builder.create().show()
    }




    @SuppressLint("Recycle")
    fun UploadAvatar(context: Context){
        //
        MySharedPref =
            context.getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        val IdUser = MySharedPref.getString(IDUSER, null)
        val TokenUser = MySharedPref.getString(TOKENUSER, null)
        val EmailUser = MySharedPref.getString(EMAILUSER, null)
        //
        val fileDir=context.filesDir
        val file= File(fileDir,"image.jpg")
        val inputStream=context.contentResolver.openInputStream(imgUri!!)
        val outputStream= FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("image", file.name,requestBody)


        viewModell= ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        //val idUser= IdUser.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        //val tokenUser= TokenUser.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val emailUser=EmailUser.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        viewModell.UploadAvatar(emailUser,image,context)
        //
        viewModell._UserLiveData.observe(viewLifecycleOwner,{
            if (it!=null){
                //Toast.makeText(context,  file.name, Toast.LENGTH_LONG).show()
                CustomToast(requireContext(), "Uploaded Successfully!","GREEN").show()
            }else{
                CustomToast(requireContext(), "Sorry, Something Goes Wrong!","RED").show()
                //Toast.makeText(context,  file.name, Toast.LENGTH_LONG).show()
                // CustomToast(this@Login, "Email or password is incorrect!","RED").show()
            }
        })
    }


    private fun openGallery() {
        //requestStoragePermission()
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).apply {
            type="image/*"
            startActivityForResult(this,IMAGE_GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_GALLERY_REQUEST_CODE) {
            if (data != null && data.data != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    imgUri= data.data!!
                    userAvatar.setImageURI(imgUri)
                    //
                    UploadAvatar(requireContext())
                    //
                }
            }
        }
    }

    private fun requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            AlertDialog.Builder(requireContext())
                .setTitle("Autorisation nécessaire")
                .setMessage("Cette autorisation est nécessaire pour ajouter une image")
                .setPositiveButton("D'accord",
                    DialogInterface.OnClickListener { dialog, which ->
                        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODE)
                    })
                .setNegativeButton("Annuler",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .create().show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
                Toast.makeText(requireContext(), "Permission accordée", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission refusée", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
