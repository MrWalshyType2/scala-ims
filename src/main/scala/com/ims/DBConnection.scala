package com.ims

import com.ims.domain.model.customer.Customer
import com.ims.domain.model.item.Item
import com.ims.domain.model.order.Order
import reactivemongo.api.bson.{BSONDocumentHandler, BSONDocumentReader, BSONDocumentWriter, BSONWriter, Macros}
import reactivemongo.api.{AsyncDriver, DB, MongoConnection}
import reactivemongo.api.bson.collection.BSONCollection

import scala.concurrent.{ExecutionContext, Future}

object DBConnection {

  // get application context
  import ExecutionContext.Implicits.global

  // connection settings
  val mongoURI = "mongodb://localhost:27017"

  // Connect to the db
  val driver = new AsyncDriver()
  val parsedURI = MongoConnection.fromString(mongoURI)

  val connection = parsedURI.flatMap(driver.connect(_))
  def db: Future[DB] = connection.flatMap(_.database("scala-ims"))
  def customerCollection: Future[BSONCollection] = db.map(_.collection("customer"))
  def itemCollection: Future[BSONCollection] = db.map(_.collection("item"))
  def orderCollection: Future[BSONCollection] = db.map(_.collection("order"))

  implicit def customerWriter: BSONDocumentWriter[Customer] = Macros.writer[Customer]
  implicit def customerReader: BSONDocumentReader[Customer] = Macros.reader[Customer]
  implicit def itemWriter: BSONDocumentWriter[Item] = Macros.writer[Item]
  implicit def itemReader: BSONDocumentReader[Item] = Macros.reader[Item]
  implicit def orderWriter: BSONDocumentWriter[Order] = Macros.writer[Order]
  implicit def orderReader: BSONDocumentReader[Order] = Macros.reader[Order]

}
