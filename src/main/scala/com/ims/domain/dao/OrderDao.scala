package com.ims.domain.dao

import com.ims.DBConnection.{itemCollection, orderCollection, orderReader, orderWriter}
import com.ims.domain.model.order.Order
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONObjectID, document}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object OrderDao extends BaseDaoInterface[Order] {
  override def create(order: Order): Future[Option[Order]] = {
    val newOrder = orderCollection.flatMap(_.insert.one(order))
    newOrder.flatMap { writeResult =>
      readById(order._id)
    }
  }

  override def readById(id: BSONObjectID): Future[Option[Order]] = {
    val selector = document("_id" -> id)
    orderCollection.flatMap(_.find(selector).one)
  }

  override def readAll(): Future[List[Order]] = {
    orderCollection.flatMap(_.find(document()).cursor[Order]()
      .collect[List](-1, Cursor.FailOnError[List[Order]]()))
  }

  override def update(order: Order): Future[Option[Order]] = {
    val selector = document("_id" -> order._id)
    val writeResult = itemCollection.flatMap(_.findAndUpdate(selector, order))
    writeResult.map(_.result[Order])
  }

  override def delete(id: BSONObjectID): Unit = {
    val selector = document("_id" -> id)
    orderCollection.flatMap(_.delete.one(selector))
  }
}
