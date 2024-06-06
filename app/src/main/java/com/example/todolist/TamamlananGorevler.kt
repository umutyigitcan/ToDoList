package com.example.todolist

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todolist.databinding.FragmentTamamlananGorevlerBinding

class TamamlananGorevler : Fragment() {

    private lateinit var adapter: tamamlananRVAdapter
    private lateinit var gorevList: ArrayList<gorevListData>
    private lateinit var tasarim: FragmentTamamlananGorevlerBinding
    private var handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentTamamlananGorevlerBinding.inflate(inflater, container, false)
        gorevList = ArrayList()
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        adapter = tamamlananRVAdapter(requireContext(), gorevList)
        tasarim.rv.adapter = adapter

        fetchData()

        handler.post(runnable)

        return tasarim.root
    }

    private fun fetchData() {
        val vt = tamamlananGorevListVeriTabaniYardimcisi(requireContext())
        val gelenData = tamamlananGorevDao().gorevleriListele(vt)
        gorevList.clear()
        for (k in gelenData) {
            gorevList.add(gorevListData(k.gorevId, k.gorevIsim))
        }
        adapter.notifyDataSetChanged()
    }

    private val runnable = object : Runnable {
        override fun run() {
            fetchData()
            handler.postDelayed(this, 1000) // 1 saniye sonra tekrar çalıştır
        }
    }
}
