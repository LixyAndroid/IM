package com.example.im.data.db

import com.example.im.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

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


    fun getAllContacts():List<Contact> = databaseHelper.use {
            select(ContactTable.NAME).parseList(object  : MapRowParser<Contact>{

                override fun parseRow(columns: Map<String, Any?>): Contact {

                    return Contact(columns.toMutableMap())
                }

            })
        }


    fun  deleteAllContacts(){
        databaseHelper.use {
            delete(ContactTable.NAME,null,null)
        }
    }



}