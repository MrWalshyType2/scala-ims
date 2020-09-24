package com.ims.domain

import reactivemongo.api.bson.{BSONDocument, BSONInteger, BSONString}

case class Order(_id: BSONString, customer_id: BSONString, orderItems: BSONDocument) {

  override def toString: String = {

    val items = orderItems.elements.map { itemTuple => itemTuple.name -> BSONInteger.unapply(itemTuple.value).get }
    println("ORDER ID " + _id)
    println("CUSTOMER ID " + customer_id)

    val currOrderItemList = items.map { itemTuple => "ITEM ID: " + itemTuple.productIterator.mkString(s", QUANTITY: ") }
    val itemString = currOrderItemList.mkString("\n")

    s"\nORDER ID: ${_id}\nCUSTOMER ID: ${customer_id}\n${itemString}"
  }
}
