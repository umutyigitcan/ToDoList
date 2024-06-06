package com.example.todolist

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RVAdapter(var mContext: Context, var gelenListe: ArrayList<gorevListData>) : RecyclerView.Adapter<RVAdapter.cardViewNesneleriniTutucu>() {
    inner class cardViewNesneleriniTutucu(view: View) : RecyclerView.ViewHolder(view) {
        var gorevismi: TextView = view.findViewById(R.id.gorevismi)
        var delete: ImageView = view.findViewById(R.id.delete)
        var checkbox: ImageView = view.findViewById(R.id.checkbox)
        var cardView: CardView = view.findViewById(R.id.cardView)
    }

    override fun getItemCount(): Int {
        return gelenListe.size
    }

    override fun onBindViewHolder(holder: cardViewNesneleriniTutucu, position: Int) {
        val tutucu = gelenListe[position]
        holder.gorevismi.text = tutucu.gorevIsim
        holder.checkbox.setImageResource(R.drawable.tdl)
        holder.delete.setOnClickListener { nesne ->
            val ad = AlertDialog.Builder(mContext)
            ad.setMessage("Silmek istediğinize emin misiniz?")
            ad.setPositiveButton("Sil") { dialogInterface, i ->
                val vt = gorevListVeriTabaniYardimcisi(mContext)
                gorevListVeriTabaniYardimcisiDao().gorevSil(vt, tutucu.gorevId)
                val newPosition = gelenListe.indexOf(tutucu)
                gelenListe.removeAt(newPosition)
                notifyItemRemoved(newPosition)
                Snackbar.make(nesne, "Görev başarıyla silindi!", Snackbar.LENGTH_SHORT).show()
            }
            ad.setNegativeButton("İptal") { dialogInterface, i -> }
            ad.create().show()
        }
        holder.cardView.setOnClickListener { nesne ->
            val ad = AlertDialog.Builder(mContext)
            ad.setTitle("Tamamlanan görevler arasına eklemek istiyor musunuz?")
            ad.setPositiveButton("EKLE") { dialogInterface, i ->
                val vt = tamamlananGorevListVeriTabaniYardimcisi(mContext)
                val vt2 = gorevListVeriTabaniYardimcisi(mContext)
                gorevListVeriTabaniYardimcisiDao().gorevSil(vt2, tutucu.gorevId)
                tamamlananGorevDao().gorevEkle(vt, tutucu.gorevId, tutucu.gorevIsim)
                gelenListe.removeAt(position)
                notifyItemRemoved(position)
                Snackbar.make(nesne, "Görev başarıyla eklendi!", Snackbar.LENGTH_SHORT).show()
            }
            ad.setNegativeButton("İPTAL") { dialogInterface, i -> }
            ad.create().show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewNesneleriniTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.thingstodocardview, parent, false)
        return cardViewNesneleriniTutucu(tasarim)
    }
}
