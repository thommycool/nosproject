package com.nos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nos.data.DReservasi
import kotlinx.android.synthetic.main.list_reservasi.view.*

class  ReservasiAdapter (private val context: Context, private val arrayList: ArrayList<DReservasi>) : RecyclerView.Adapter<ReservasiAdapter.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasiAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_reservasi,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.txtRuangan.text = arrayList?.get(position)?.nama_ruangan
        holder.view.txtKegiatan.text = "Acara/Kegiatan : "+arrayList?.get(position)?.kegiatan
        holder.view.txtBidang.text = "Bidang : "+arrayList?.get(position)?.pemakai
        holder.view.txtTgl1.text = "Tanggal Dan Jam Awal : "+arrayList?.get(position)?.tglawal
        holder.view.txtTgl2.text = "Tanggal Dan Jam Akhir : "+arrayList?.get(position)?.tglakhir
        holder.view.txtKet.text = "Keterangan : "+arrayList?.get(position)?.keterangan


        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ApprovalActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("kegiatan",arrayList?.get(position)?.kegiatan)
            i.putExtra("pemakai",arrayList?.get(position)?.pemakai)
            i.putExtra("tglawal",arrayList?.get(position)?.tglawal)
            i.putExtra("tglakhir",arrayList?.get(position)?.tglakhir)
            i.putExtra("keterangan",arrayList?.get(position)?.keterangan)
            context.startActivity(i)



        }



    }

}