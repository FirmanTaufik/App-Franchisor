package com.appfranchisor.app.helper

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.DialogAutoCopleteTextViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.permissionx.guolindev.PermissionX
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


object Utils {

    private val TAG = "UtilsTAG"

    fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = act.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(act.resources.getColor(color))
        }
    }

    fun getDateyyyyMMddHHmmss(): String {
        val simpleDate = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val cal = Calendar.getInstance()
        val current = cal.time
        return simpleDate.format(current)
    }


    val getDateNow: String
        get() {
            val simpleDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val dateyyyyMMdd: String
        get() {
            val simpleDate = SimpleDateFormat("yyyy-MM-dd")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val dateddMMyyyy: String
        get() {
            val simpleDate = SimpleDateFormat("ddMMyyyy")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val dateddMMyy: String
        get() {
            val simpleDate = SimpleDateFormat("ddMMyy")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val hour: String
        get() {
            val simpleDate = SimpleDateFormat("HH:mm:ss")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val hours: String
        get() {
            val simpleDate = SimpleDateFormat("HHmmss")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDate.format(current)
        }
    val datehour: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDateFormat.format(current)
        }

    val month: String
        get() {
            val simpleDateFormat = SimpleDateFormat("MM")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDateFormat.format(current)
        }

    val year: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy")
            val cal = Calendar.getInstance()
            val current = cal.time
            return simpleDateFormat.format(current)
        }

    fun changeDateToYYYYmmddHHmmssToddMMYYYY(value: String): String {
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date = sf.parse(value)

            val dateFormater = SimpleDateFormat("dd-MM-yyyy")
            return dateFormater.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""

    }

    fun changeDateToYYYYmmddHHmmssToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return df.parse(date).time

    }

    fun convertToyyyMMdd(date: Long): String? {
        val dateFormater = SimpleDateFormat("yyyy-MM-dd")
        return dateFormater.format(date)
    }



    fun String.showAsToast(){
        Toast.makeText(App.getContext(), this, Toast.LENGTH_SHORT).show()
    }


    fun loadImage(
        context: Context,
        url: String,
        image: ImageView,
        errorDrawwableImage: Int? = null
    ) {
        val glide = Glide.with(context).load(url)
        if (errorDrawwableImage != null) {
            glide.error(errorDrawwableImage)
        }
        glide.into(image)

    }

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        return formatter.format(this)
    }

    fun showDateDialog(context: Context, onSelect: (select: String) -> Unit) {
        val calendar: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateLongInput = calendar.timeInMillis
                val value = convertToyyyMMdd(dateLongInput).toString()
                onSelect(value)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    fun showDateRangeDialog(
        supportFragmentManager: FragmentManager,
        param: MaterialPickerOnPositiveButtonClickListener<in androidx.core.util.Pair<Long, Long>>
    ) {
        val materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker()
            .setTheme(R.style.MaterialCalendarTheme)
        materialDateBuilder.setTitleText("Pilih Tanggal")
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.addOnPositiveButtonClickListener(param)
        materialDatePicker.show(supportFragmentManager, "Pilih Tanggal")
    }

    fun showCustomDialog(view1: View, context: Context): AlertDialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(view1)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
        return alertDialog
    }

    fun TextInputLayout.addSearchDialog(context: Context, fetchAsList: ArrayList<String>, onSelect: (select: String) -> Unit) {
        this.editText?.setOnClickListener {
            val view  = LayoutInflater.from(context)
                .inflate(R.layout.dialog_auto_coplete_text_view, null, false)
            val materialDialog =  MaterialAlertDialogBuilder(context, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
                .setCancelable(true)
                .setView(view)
            val dialog : androidx.appcompat.app.AlertDialog =  materialDialog.show()
            val mBinding = DialogAutoCopleteTextViewBinding.bind(view)
            println("dialogAutoCompleteTextView "+ fetchAsList.groupingBy { it }.eachCount().filter { it.value > 1 })
            val arrayAdapter =SearchableAdapter(context,android.R.layout.simple_list_item_1,fetchAsList)
            mBinding.listView.adapter =arrayAdapter
            mBinding.inputPilih.editText.listenOnChange {
                arrayAdapter.filter.filter(it)
            }
            mBinding.listView.setOnItemClickListener { parent, view, position, id ->
                val selectedPoi = parent.adapter.getItem(position) as String?
                if (selectedPoi != null) {
                    this.editText?.setText(selectedPoi)
                    onSelect(selectedPoi)
                    dialog.dismiss()
                }
            }
        }
    }



    fun showDialogDefaultConfirmation(
        context: Context,
        title: String? = null,
        message: String,
        positiveText: String? = "Ya",
        negativeText: String? = "Tidak",
        onPositive:() ->Unit,
        onNegative:() ->Unit,
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        if (title != null) builder.setTitle(title)

        builder.setMessage(message)
        builder.setPositiveButton(
            positiveText
        ) { dialog, which ->
            onPositive()
            dialog.dismiss()
        }

        builder.setNegativeButton(
            negativeText
        ) { dialog, which ->
            onNegative()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = false
    }

    fun popupCek(context: Context, title: String?, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            "Oke"
        ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun imageAlfabet(name: String, image: ImageView) {
        val aplhabet = name.uppercase().subSequence(0, 1)
        val generator = ColorGenerator.MATERIAL
        val drawable = TextDrawable.builder()
            .beginConfig()
            .width(50) // width in px
            .height(50) // height in px
            .endConfig()
            .buildRect(aplhabet as String?, generator.getColor(name))
        image.setImageDrawable(drawable)
    }

    fun TextInputLayout.addDropdown(
        data: List<String>,
        onSelectId: (select: Int) -> Unit
    ) {
        val adapter = ArrayAdapter(App.getContext()!!, android.R.layout.simple_spinner_dropdown_item, data)
        (editText as? AutoCompleteTextView)?.apply {
             setAdapter(adapter)
             setOnItemClickListener { _, view, i, l ->
                onSelectId(i)
            }
        }
    }

    fun closeKeyboard(act: Activity) {
        val view = act.currentFocus
        if (view != null) {
            val manager: InputMethodManager =
                act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    fun showKeyboard() {
        val inputMethodManager =
            App.getContext()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


    fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }


    fun Any.convertNulltoDouble(): Double {
        Log.d(TAG, "convertNulltoDouble:${this.toString()} ")
        return if (this == "null") return 0.0
        else this.toString().toDouble()
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    fun base64tobitmap(datas1: String?): Bitmap? {
        try {
            val imageBytes =
                Base64.decode(datas1, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }catch (i :IllegalArgumentException) {
            print("base64tobitmap ${i.message}")
        }
        return null
    }




    fun View.hide(){
        this.visibility =View.GONE
    }

    fun View.show(){
        this.visibility =View.VISIBLE
    }

    fun checkPermission(activity: Activity, list: ArrayList<String>, onGranted :() ->Unit ) {
        PermissionX.init(activity as FragmentActivity)
            .permissions(list)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "You need to allow necessary permissions in Settings manually", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                deniedList.forEach {
                    println("tolak $it")
                }
                if (allGranted) {
                    onGranted()
                } else  "Permission di tolak".showAsToast()
            }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap? {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }


    fun EditText?.listenOnChange(onChangeListener: (String)->Unit ) {
        var beforeChangeText = ""
        this?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                beforeChangeText = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (beforeChangeText!= s.toString()) {
                    val onValue = this@listenOnChange.text
                    if ( !onValue.isNullOrEmpty() ||  onValue.isNotBlank()) {
                        onChangeListener(s.toString())
                    }
                }


            }

            override fun afterTextChanged(s: Editable?) {
                val onValue = this@listenOnChange.text
                this@listenOnChange.setSelection(onValue.length)

            }
        })
    }

 

    fun showDialogPriceNotProvided(context: Context, back:()->Unit) {
        val alertDialogBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Harga belum tersedia!")
        alertDialogBuilder.setMessage("Silahkan cek kembali tujuan anda, jika tidak ada harap hubungi cabang")
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setNegativeButton(
            "Close"
        ) { dialog: DialogInterface?, which: Int ->
            back()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    val ymd :String  get() {
        val c1 = Calendar.getInstance()
        val sdf1 = SimpleDateFormat("yyyyMMdd")
       return sdf1.format(c1.time)
    }


    val time :String  get() {
        val c1 = Calendar.getInstance()
        val sdf1 = SimpleDateFormat("HHmmssSSS")
        return sdf1.format(c1.time)
    }

    val tglResi :String  get() {
        val c1 = Calendar.getInstance()
        val sdf1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf1.format(c1.time)
    }

    fun  <T>Response<T> .convertErrorMessage(): String? {
        val jsobObjects  : JSONObject = if (this.body() != null) {
            JSONObject(this.body().toString())
        }  else  {
            val response  = this.errorBody()?.source()?.buffer?.snapshot()?.utf8()
            JSONObject(response)
        }
        return jsobObjects.getString("message")
    }


    fun getRealPathFromURIPath(contentURI: Uri, context: Context): String? {
        if (contentURI.toString().startsWith("content://com.android.providers")) {
            val wholeID = DocumentsContract.getDocumentId(contentURI)
            val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val column = arrayOf(MediaStore.Images.Media.DATA)
            val sel = MediaStore.Images.Media._ID + "=?"
            val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, arrayOf(id), null
            )
            var filePath: String? = ""
            val columnIndex = cursor!!.getColumnIndex(column[0])
            if (cursor!!.moveToFirst()) {
                filePath = cursor!!.getString(columnIndex)
            }

            cursor!!.close()
            return filePath
        }else{
            val cursor: Cursor? = context.contentResolver.query(contentURI, null, null, null, null)
            return if (cursor == null) {
                contentURI.path
            } else {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                cursor.getString(idx)
            }
        }

    }
}