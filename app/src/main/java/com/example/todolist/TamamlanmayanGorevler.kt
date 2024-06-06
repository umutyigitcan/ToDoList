package com.example.todolist

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todolist.databinding.FragmentTamamlanmayanGorevlerBinding
import com.google.android.material.snackbar.Snackbar

class TamamlanmayanGorevler : Fragment() {
    private lateinit var adapter: RVAdapter
    private lateinit var tasarim: FragmentTamamlanmayanGorevlerBinding
    private lateinit var gorevList: ArrayList<gorevListData>
    private var handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentTamamlanmayanGorevlerBinding.inflate(inflater, container, false)
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        gorevList = ArrayList()
        val vt = gorevListVeriTabaniYardimcisi(requireContext())
        val gelenData = gorevListVeriTabaniYardimcisiDao().gorevleriListele(vt)
        for (k in gelenData) {
            gorevList.add(gorevListData(k.gorevId, k.gorevIsim))
        }
        adapter = RVAdapter(requireContext(), gorevList)
        tasarim.rv.adapter = adapter

        tasarim.add.setOnClickListener { nesne ->
            handler.post(runnable)
            val tasarim = layoutInflater.inflate(R.layout.alert_tasarim, null)
            val edittext = tasarim.findViewById(R.id.edittext) as EditText
            val ad = AlertDialog.Builder(requireContext())
            ad.setView(tasarim)
            ad.setIcon(R.drawable.tdl)
            ad.setTitle("Görev ekle")
            ad.setPositiveButton("EKLE") { dialogInterface, i ->
                val alinanText = edittext.text.toString()
                val vt = gorevListVeriTabaniYardimcisi(requireContext())
                gorevListVeriTabaniYardimcisiDao().gorevEkle(vt, alinanText)
                Snackbar.make(nesne, "Görev başarıyla eklendi...", Snackbar.LENGTH_SHORT).show()
                val yeniGelenData = gorevListVeriTabaniYardimcisiDao().gorevleriListele(vt)
                gorevList.clear()
                for (k in yeniGelenData) {
                    gorevList.add(gorevListData(k.gorevId, k.gorevIsim))
                }
                adapter.notifyDataSetChanged()
            }
            ad.setNegativeButton("İPTAL") { dialogInterface, i -> }
            ad.create().show()
        }

        return tasarim.root
    }

    private val runnable = object : Runnable {
        override fun run() {
            val vt = gorevListVeriTabaniYardimcisi(requireContext())
            val yeniGelenData = gorevListVeriTabaniYardimcisiDao().gorevleriListele(vt)
            if (gorevList.size != yeniGelenData.size) {
                gorevList.clear()
                for (k in yeniGelenData) {
                    gorevList.add(gorevListData(k.gorevId, k.gorevIsim))
                }
                adapter.notifyDataSetChanged()
            }
            handler.postDelayed(this, 1000) // 1 saniye sonra tekrar çalıştır
        }
    }
}
