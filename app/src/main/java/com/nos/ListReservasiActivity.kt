package com.nos

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.nos.data.DReservasi
import kotlinx.android.synthetic.main.activity_list_reservasi.*
import org.json.JSONObject
import java.util.ArrayList

class ListReservasiActivity : AppCompatActivity() {
    var arrayList = ArrayList<DReservasi>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_reservasi)

        RVReservasi.setHasFixedSize(true)
        RVReservasi.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadDataReservasi()
    }
    private fun loadDataReservasi(){

        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()


        AndroidNetworking.get(ApiEndPoint.READ)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(applicationContext,"Data Reservasi Masih Kosong",
                            Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add(DReservasi(
                            jsonObject.getString("id"),
                            jsonObject.getString("id_ruangan"),
                            jsonObject.getString("nama_ruangan"),
                            jsonObject.getString("kegiatan"),
                            jsonObject.getString("pemakai"),
                            jsonObject.getString("tglawal"),
                            jsonObject.getString("tglakhir"),
                            jsonObject.getString("keterangan"),
                            jsonObject.getString("pemesan"),
                            jsonObject.getString("status")

                            ))


                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = ReservasiAdapter(applicationContext,arrayList)
                            adapter.notifyDataSetChanged()
                            RVReservasi.adapter = adapter

                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })




    }

}
