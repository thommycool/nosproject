package com.nos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nos.data.DReservasi
import kotlinx.android.synthetic.main.list_reservasi.view.*

class  AllReservasiAdapter ( private val arrayList: ArrayList<DReservasi>) : RecyclerView.Adapter<AllReservasiAdapter.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllReservasiAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_reservasi,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.txtRuangan.text = arrayList?.get(position)?.nama_ruangan
        holder.view.txtKegiatan.text = "Acara/Kegiatan : "+arrayList?.get(position)?.kegiatan
        holder.view.txtBidang.text = "Bidang : "+arrayList?.get(position)?.pemakai
        holder.view.txtTgl1.text = "Tanggal Dan Jam Awal : "+arrayList?.get(position)?.tglawal
        holder.view.txtTgl2.text = "Tanggal Dan Jam Akhir : "+arrayList?.get(position)?.tglakhir
        holder.view.txtKet.text = "Keteraangan : "+arrayList?.get(position)?.keterangan
    }




}