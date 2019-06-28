package com.example.im.factory

import androidx.fragment.app.Fragment
import com.example.im.R
import com.example.im.ui.fragment.ContactFragment
import com.example.im.ui.fragment.ConversationFragment
import com.example.im.ui.fragment.DynamicFragment

/**
 * @author  Mloong
 * date  2019/6/28 19:53
 */
class FragmentFactory private constructor(){


    val conversation by lazy {
        ConversationFragment()
    }

    val contract by lazy {
        ContactFragment()
    }

    val dynamic by lazy {
        DynamicFragment()
    }


    companion object{
        val instance = FragmentFactory()
    }

    fun getFragment(tabId:Int) : Fragment? {
        when(tabId){
            R.id.tab_conversation -> return  conversation
            R.id.tab_contacts -> return  contract
            R.id.tab_dynamic -> return dynamic
        }

        return null
    }

}