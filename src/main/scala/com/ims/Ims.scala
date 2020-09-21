package com.ims

import java.util.logging.{Level, Logger}

import com.ims.crud.CRUD
import com.ims.domain.Domain
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.{AsyncDriver, Cursor, DB, MongoConnection}
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros, document}

import scala.concurrent.{ExecutionContext, Future}

object Ims {

  private val LOGGER = Logger.getLogger(Ims.getClass.getSimpleName)
  private var USERNAME: String = ""
  private var PASSWORD: String = ""



  // get application context
  import ExecutionContext.Implicits.global

  // connection settings
  val mongoURI = "mongodb://localhost:27017/scala-ims"

  // Connect to the db
  val driver = new AsyncDriver()
  val parsedURI = MongoConnection.fromStringWithDB(mongoURI)

  val connection = parsedURI.flatMap(driver.connect(_))
  def db: Future[DB] = connection.flatMap(_.database("scala-ims"))
  def customerCollection: Future[BSONCollection] = db.map(_.collection("customer"))
  def itemCollection: Future[BSONCollection] = db.map(_.collection("item"))
  def orderCollection: Future[BSONCollection] = db.map(_.collection("order"))

  def menu(): Unit = {
    Domain.getDomains().foreach(domain => println(domain))
    val domain: String = getInput()

    if (domain.equalsIgnoreCase("EXIT")) System.exit(0)
    else
      LOGGER.info(s"What would you like to do with $domain?")
      CRUD.getValues().foreach(value => println(value))

      val operation: String = getInput()

      // gonna want some function magic here!!!
      if (operation.equalsIgnoreCase("CREATE")) createCustomer(new Customer("test", "test"))
      else if (operation.equalsIgnoreCase("READ")) ???
      else if (operation.equalsIgnoreCase("UPDATE")) ???
      else if (operation.equalsIgnoreCase("DELETE")) ???
      else if (operation.equalsIgnoreCase("RETURN")) ???
      else LOGGER.warning(s"Invalid input | DOMAIN: $domain, OPERATION: $operation")

    menu()
  }
  implicit def customerWriter: BSONDocumentWriter[Customer] = Macros.writer[Customer]

  def createCustomer(customer: Customer): Future[Unit] =
    customerCollection.flatMap(_.insert.one(customer).map(_ => {}))

  case class Customer(forename: String, surname: String)

}
