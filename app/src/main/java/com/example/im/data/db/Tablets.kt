package com.example.im.data.db

/**
 * @author  Mloong
 * date  2019/6/29 11:53
 */
//1，定义了ContactTable类
//2，创建了ContactTable的一个实例。通过类名，直接访问实例。实现单例的一种方式

object  ContactTable{

    val  NAME = "contact"

    //定义字段
    val ID = "_id"
    val CONTACT = "name"
}