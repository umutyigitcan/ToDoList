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

class tamamlananRVAdapter(var mContext: Context,var gelenListe:ArrayList<gorevListData>):RecyclerView.Adapter<tamamlananRVAdapter.cardViewNesneleriniTutucu>() {
    inner class cardViewNesneleriniTutucu(view: View): RecyclerView.ViewHolder(view){
        var gorevismi: TextView
        var delete: ImageView
        var checkbox: ImageView
        var cardView: CardView
        init {
            gorevismi=view.findViewById(R.id.gorevismi)
            delete=view.findViewById(R.id.delete)
            checkbox=view.findViewById(R.id.checkbox)
            cardView=view.findViewById(R.id.cardView)
        }
    }

    override fun getItemCount(): Int {
        return gelenListe.size
    }

    override fun onBindViewHolder(holder: cardViewNesneleriniTutucu, position: Int) {
        var tutucu=gelenListe[position]
        holder.gorevismi.text=tutucu.gorevIsim
        holder.checkbox.setImageResource(R.drawable.tdl)
        holder.delete.setOnClickListener { nesne ->
            var ad = AlertDialog.Builder(mContext)
            ad.setMessage("Silmek istediğinize emin misiniz?")
            ad.setPositiveButton("Sil") { dialogInterface, i ->
                val vt = tamamlananGorevListVeriTabaniYardimcisi(mContext)
                tamamlananGorevDao().gorevSil(vt, tutucu.gorevId)
                val newPosition = gelenListe.indexOf(tutucu)
                gelenListe.removeAt(newPosition)
                notifyItemRemoved(newPosition)
                Snackbar.make(nesne, "Görev başarıyla silindi!", Snackbar.LENGTH_SHORT).show()
            }
            ad.setNegativeButton("İptal") { dialogInterface, i ->
            }
            ad.create().show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewNesneleriniTutucu {
        var tasarim=LayoutInflater.from(mContext).inflate(R.layout.thingstodocardview,null)
        return cardViewNesneleriniTutucu(tasarim)
    }
}