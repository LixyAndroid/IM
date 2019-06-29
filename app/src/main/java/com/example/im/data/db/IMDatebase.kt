package com.example.im.data.db

import com.example.im.extentions.toVarargArray
import org.jetbrains.anko.db.insert

/**
 * @author  Mloong
 * date  2019/6/29 12:55
 */
class IMDatebase {

    companion object{
        val  databaseHelper = DatabaseHelper()
        val instance = IMDatebase()

    }

    fun  saveContact(contact: Contact){
        databaseHelper.use {
            //SQLiteDatabase的扩展方法

            insert(ContactTable.NAME,*contact.map.toVarargArray())

        }

    }

}