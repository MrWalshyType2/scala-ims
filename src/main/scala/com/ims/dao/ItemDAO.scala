package com.ims.dao

import java.util.logging.Logger

import com.ims.DBConnection.{itemCollection, itemReader, itemWriter}
import com.ims.domain.Item
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, BSONString, document}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object ItemDAO extends DAO[Item] {

  private val LOGGER = Logger.getLogger(ItemDAO.getClass.getSimpleName)

  override def create(item: Item): Unit = {
    itemCollection.flatMap(_.insert.one(item).map(_ => { }))
  }

  override def readAll(): Unit = {
    val items: Future[List[Item]] = itemCollection.flatMap(_.find(document())
      .cursor[Item]()
      .collect[List](-1, Cursor.FailOnError[List[Item]]()))

    items andThen {
      case Success(value) => {
        value.foreach(item => {
          LOGGER.info(s"\nID: ${item._id}\nNAME: ${item.name}\nVALUE: £${item.value/100}")
        })
      }
      case Failure(e) => {
        LOGGER.severe(e.getMessage.toString)
      }
    }
  }

  override def update(item: Item): Unit = {
    val selector = document(
      "_id" -> item._id
    )
    itemCollection.flatMap(_.update.one(selector, item).map(_.n))
  }

  override def delete(id: String): Unit = ???

  def readById(id: String): Item = {
    var item: Item = Item(BSONString(BSONObjectID.generate().stringify), "", 0)
    val query = BSONDocument("_id" -> id) // create query

    val items: Future[List[Item]] = itemCollection.flatMap(_.find(query) // parse query
      .cursor[Item]()
      .collect[List](1, Cursor.FailOnError[List[Item]]())) // collect results into a List of Customer

    items andThen {
      case Success(value) => {
        //value.foreach(item => LOGGER.info(item.toString))
      }
      case Failure(e) => {
        LOGGER.severe(e.getMessage)
      }
    }

    Await.result(items, Duration.Inf) {
      item = items.value.get.get.head
      0
    }
    item
  }
}
