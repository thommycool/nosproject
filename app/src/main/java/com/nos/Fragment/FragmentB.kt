package com.nos.Fragment

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.nos.AllReservasiAdapter
import com.nos.ApiEndPoint

import com.nos.R

import com.nos.data.DReservasi

import kotlinx.android.synthetic.main.fragment_b.*
import org.json.JSONObject
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class FragmentB : Fragment() {
    lateinit var MView: View
    var arrayList = ArrayList<DReservasi>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MView= inflater.inflate(R.layout.fragment_b, container, false)
        //LRVReservasi = MView.findViewById(R.id.LRVReservasi) as RecyclerView
        //LRVReservasi.setHasFixedSize(true)
        //LRVReservasi.layoutManager = LinearLayoutManager(activity)
        loadDataReservasi()
        return MView


    }



    private fun loadDataReservasi(){

        val loading = ProgressDialog(activity)
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
                        //Toast.makeText()
                        Toast.makeText(activity,"Data Reservasi Masih Kosong",
                            Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add(
                            DReservasi(
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

                            )
                        )
                        println(jsonArray?.optJSONObject(i))


                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = AllReservasiAdapter(arrayList)
                            adapter.notifyDataSetChanged()
                            LRVReservasi.adapter = adapter

                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(activity,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })




    }



}