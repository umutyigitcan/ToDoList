package com.example.todolist

class gorevListVeriTabaniYardimcisiDao {
    fun gorevEkle(vt:gorevListVeriTabaniYardimcisi,gorevIsim:String){
        var db=vt.writableDatabase
        db.execSQL("INSERT INTO gorevList(gorevIsim) VALUES('$gorevIsim')")
        db.close()
    }

    fun gorevleriListele(vt:gorevListVeriTabaniYardimcisi):ArrayList<gorevListData>{
        var db=vt.writableDatabase
        var gorevListe=ArrayList<gorevListData>()
        var cursor=db.rawQuery("SELECT * FROM gorevList",null)
        while (cursor.moveToNext()){
            var gelenData=gorevListData(cursor.getInt(cursor.getColumnIndexOrThrow("gorevId")),
                cursor.getString(cursor.getColumnIndexOrThrow("gorevIsim")))
            gorevListe.add(gelenData)
        }
        return gorevListe
    }

    fun gorevSil(vt:gorevListVeriTabaniYardimcisi,gorevId:Int){
        var db=vt.writableDatabase
        db.execSQL("DELETE FROM gorevList WHERE gorevId=$gorevId")
        db.close()
    }
}