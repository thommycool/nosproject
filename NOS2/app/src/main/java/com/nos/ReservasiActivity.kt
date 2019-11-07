package com.nos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Build
import android.renderscript.RenderScript
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_reservasi.*
import kotlinx.android.synthetic.main.activity_reservasi.cbBidang
import kotlinx.android.synthetic.main.testlayout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONObject






class ReservasiActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var button_date1: Button? = null
    var textview_date1: TextView? = null
    var button_date2: Button? = null
    var textview_date2: TextView? = null
    private var pemesan = "Thommy"

    var cal1 = Calendar.getInstance()
    var cal2 = Calendar.getInstance()
    var list_of_items = arrayOf("OPERASI", "PEKOM", "SDM", "PENGADAAN","K3L","LAIN-LAIN")
    var list_ruangan = arrayOf("SEGARA ANAK", "KELIMUTU 1", "KELIMUTU 2", "SEMBALUN","R. GM","LAIN-LAIN")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlayout)

        cbBidang!!.setOnItemSelectedListener(this)
        cbRuang!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_ruangan)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        cbBidang!!.setAdapter(aa)
        cbRuang!!.setAdapter(ab)


        textview_date1 = this.textTgl1

        //button_date1 = this.btnTgl1
        textview_date2 = this.textTgl2
        //button_date2 = this.btnTgl2

        //textview_date1!!.text = "-- -- ----"

        // create an OnDateSetListener tanggal awal / pertama
        val dateSetListener1 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal1.set(Calendar.YEAR, year)
                cal1.set(Calendar.MONTH, monthOfYear)
                cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView1()
            }
        }

        // create an OnDateSetListener tanggal akhir / kedua
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal2.set(Calendar.YEAR, year)
                cal2.set(Calendar.MONTH, monthOfYear)
                cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView2()
            }
        }


        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textview_date1!!.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@ReservasiActivity,
                    dateSetListener1,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal1.get(Calendar.YEAR),
                    cal1.get(Calendar.MONTH),
                    cal1.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textview_date2!!.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@ReservasiActivity,
                    dateSetListener2,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal2.get(Calendar.YEAR),
                    cal2.get(Calendar.MONTH),
                    cal2.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        //fungsi pilih waktu
        //jam pertama /awal
        //val mPickTimeBtn1 = findViewById<Button>(R.id.btnJam1)
        val textView1     = findViewById<TextView>(R.id.textJam1)

        textView1.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView1.text =SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //jam kedua /akhir
        //val mPickTimeBtn2 = findViewById<Button>(R.id.btnJam2)
        val textView2     = findViewById<TextView>(R.id.textJam2)

        textView2.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView2.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

       val  btntambah: Button = findViewById(R.id.btnTmbhdata)
        btntambah.setOnClickListener(){
            tambahreservasi()

        }

    }

    private fun updateDateInView1() {
        //val myFormat = "MM/ddd/yyyy" // mention the format you need
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date1!!.text = sdf.format(cal1.getTime())

    }

    private fun updateDateInView2() {
        //val myFormat = "MM/ddd/yyyy" // mention the format you need
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date2!!.text = sdf.format(cal2.getTime())

    }

    private fun tambahreservasi(){

        // Parse the input date
        var jam1: TextView=findViewById(R.id.textJam1)
        var jam2: TextView=findViewById(R.id.textJam2)
        var tgl1: TextView=findViewById(R.id.textTgl1)
        var tgl2: TextView=findViewById(R.id.textTgl2)
        var fmt1 = SimpleDateFormat("yyyy-MM-dd HH:mm")
        var fmt2 = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val inputDate1 = fmt1.parse(tgl1.text.toString()+" "+jam1.text.toString())
        val inputDate2 = fmt2.parse(tgl2.text.toString()+" "+jam2.text.toString())



// Create the MySQL datetime string
        fmt1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        fmt2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateString1 = fmt1.format(inputDate1)
        val dateString2 = fmt2.format(inputDate2)


        val kegiatan :TextView = findViewById(R.id.editAcara)


        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()
        //val acara:EditText=findViewById(R.id.editAcara)


        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("nama_ruangan",cbRuang.selectedItem.toString())
            //.addBodyParameter("id_ruangan","3")
            //.addBodyParameter("nama_ruangan","SEGARA ANAK")
            .addBodyParameter("pemakai",cbBidang.selectedItem.toString())
            .addBodyParameter("kegiatan",kegiatan.text.toString())
            .addBodyParameter("pemesan",pemesan)
            .addBodyParameter("tglawal",dateString1.toString())
            .addBodyParameter("tglakhir",dateString2.toString())
            .addBodyParameter("keterangan",editKet.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ReservasiActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("WOIERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }

            })

    }


    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


}
