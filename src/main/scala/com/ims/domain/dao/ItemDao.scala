package com.ims.domain.dao

import com.ims.DBConnection.{itemCollection, itemReader, itemWriter}
import com.ims.domain.model.item.Item
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, document}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ItemDao extends BaseDaoInterface[Item] {

  override def create(item: Item): Future[Option[Item]] = {
    val newItem: Future[WriteResult] = itemCollection.flatMap(_.insert.one(item))
    newItem.flatMap { writeResult =>
      readById(item._id)
    }
  }

  override def readById(id: BSONObjectID): Future[Option[Item]] = {
    val selector = document("_id" -> id)
    itemCollection.flatMap(_.find(selector).one)
  }

  override def readAll(): Future[List[Item]] = {
    itemCollection.flatMap(_.find(document()).cursor[Item]()
      .collect[List](-1, Cursor.FailOnError[List[Item]]()))
  }

  override def update(item: Item): Future[Option[Item]] = {
    val selector = document("_id" -> item._id)
    val writeResult = itemCollection.flatMap(_.findAndUpdate(selector, item))
    writeResult.map(_.result[Item])
  }

  override def delete(id: BSONObjectID): Unit = {
    val selector = document("_id" -> id)
    itemCollection.flatMap(_.delete.one(selector))
  }
}
