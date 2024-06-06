package com.example.todolist

class tamamlananGorevDao {
    fun gorevEkle(vt:tamamlananGorevListVeriTabaniYardimcisi,gorevId:Int,gorevIsim:String){
        var db=vt.writableDatabase
        db.execSQL("INSERT INTO tamamlananGorevList(gorevId,gorevIsim) VALUES($gorevId,'$gorevIsim')")
        db.close()
    }

    fun gorevleriListele(vt:tamamlananGorevListVeriTabaniYardimcisi):ArrayList<gorevListData>{
        var db=vt.writableDatabase
        var gorevListe=ArrayList<gorevListData>()
        var cursor=db.rawQuery("SELECT * FROM tamamlananGorevList",null)
        while (cursor.moveToNext()){
            var gelenData=gorevListData(cursor.getInt(cursor.getColumnIndexOrThrow("gorevId")),
                cursor.getString(cursor.getColumnIndexOrThrow("gorevIsim")))
            gorevListe.add(gelenData)
        }
        return gorevListe
    }
    fun gorevSil(vt:tamamlananGorevListVeriTabaniYardimcisi,gorevId:Int){
        var db=vt.writableDatabase
        db.execSQL("DELETE FROM tamamlananGorevList WHERE gorevId=$gorevId")
        db.close()
    }
}