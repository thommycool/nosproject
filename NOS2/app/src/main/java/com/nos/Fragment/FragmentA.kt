package com.nos.Fragment

//import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
//import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.nos.ListReservasiActivity
import com.nos.R
import com.nos.ReservasiActivity
//import kotlinx.android.synthetic.main.fragment_a.view.*


class FragmentA : Fragment(){

    lateinit var mView: View

    override fun  onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_a, container, false)
        val btnReserv :ImageView = mView.findViewById(R.id.btnReservasi)
        btnReserv.setOnClickListener {
            //val intent = Intent(activity, ReservasiActivity::class.java)
            //activity?.startActivity(intent)
            //val intent = Intent (this, ReservasiActivity::class.java)

            //startActivity(intent)

            val intents = Intent(activity, ReservasiActivity::class.java)
            //startActivity(intent)
            requireContext().startActivity(intents)
        }
        val btnlistreservasi:ImageView = mView.findViewById(R.id.imListReservasi)
        btnlistreservasi.setOnClickListener{
            val intent = Intent(activity,ListReservasiActivity::class.java)
            startActivity(intent)
        }




        return mView

    }




    companion object {
        fun newInstance(): FragmentA {
            return FragmentA()
        }
    }





}
