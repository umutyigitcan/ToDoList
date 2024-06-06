package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.databinding.FragmentAnasayfaBinding
import com.google.android.material.tabs.TabLayoutMediator

class Anasayfa : Fragment() {


    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var fragmentListesi:ArrayList<Fragment>
    private lateinit var fragmentBaslikListesi:ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentAnasayfaBinding.inflate(inflater,container,false)
        fragmentListesi= ArrayList()
        fragmentBaslikListesi= ArrayList()
        fragmentListesi.add(TamamlanmayanGorevler())
        fragmentListesi.add(TamamlananGorevler())
        fragmentBaslikListesi.add("Tamamlanmayan\nGörevler")
        fragmentBaslikListesi.add("Tamamlanan\nGörevler")
        val adapter=myViewPagerAdapter(requireActivity())
        tasarim.vp.adapter=adapter
        TabLayoutMediator(tasarim.tl,tasarim.vp){taab,position->
            taab.setText(fragmentBaslikListesi[position])
        }.attach()



        return tasarim.root
    }
    inner class myViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity){
        override fun createFragment(position: Int): Fragment {
            return fragmentListesi[position]
        }

        override fun getItemCount(): Int {
            return fragmentListesi.size
        }
    }
}