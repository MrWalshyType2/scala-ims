package com.ims.dao

import com.ims.DBConnection.{orderCollection, orderReader, orderWriter}
import com.ims.domain.Order

import scala.concurrent.ExecutionContext.Implicits.global

object OrderDAO extends DAO[Order] {

  override def create(order: Order): Unit = {
    orderCollection.flatMap(_.insert.one(order).map(_ => { }))
  }

  override def readAll(): Unit = ???

  override def update(order: Order): Unit = ???

  override def delete(id: String): Unit = ???
}
