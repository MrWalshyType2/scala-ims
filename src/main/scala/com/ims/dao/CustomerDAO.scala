package com.ims.dao

import java.util.UUID
import java.util.logging.Logger

import com.ims.DBConnection.{customerCollection, customerReader, customerWriter}
import com.ims.domain.Customer
import com.sun.corba.se.spi.ior.ObjectId
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.{Collection, Cursor}
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, BSONString, ElementProducer, document, generateId}

import scala.List
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object CustomerDAO extends DAO[Customer] {

//  private val LOGGER = Logger.getLogger(CustomerDAO.getClass.getSimpleName)
//
//  override def create(customer: Customer): Unit = {
//    // first _ is an object that is not yet created in memory
//    customerCollection.flatMap(_.insert.one(customer).map(_ => { }))
//  }
//
//  override def readAll(): Unit = {
//    val customers: Future[List[Customer]] = customerCollection.flatMap(_.find(document())
//      .cursor[Customer]()
//      .collect[List](-1, Cursor.FailOnError[List[Customer]]()))
//
//    customers andThen {
//      case Success(value) => {
//        value.foreach(customer => LOGGER.info(customer.toString))
//      }
//      case Failure(e) => {
//        LOGGER.severe(e.getMessage.toString)
//      }
//    }
//  }
//
//  override def update(customer: Customer): Unit = {
//    val selector = document(
//      "_id" -> customer._id
//    )
//    customerCollection.flatMap(_.update.one(selector, customer).map(_.n))
//  }
//
//  override def delete(id: String): Unit = {
//    val selector = document(
//      "_id" -> BSONString(id)
//    )
//    customerCollection.flatMap(_.delete.one(selector).map(_.n))
//  }
//
//  def readById(id: String): Customer = {
////    val oid = BSONObjectID.parse(id) // conv id to BSONObjectID
//    val query = BSONDocument("_id" -> id) // create query
//
//    val customers: Future[List[Customer]] = customerCollection.flatMap(_.find(query) // parse query
//      .cursor[Customer]()
//      .collect[List](1, Cursor.FailOnError[List[Customer]]())) // collect results into a List of Customer
//
//    customers andThen {
//      case Success(value) => {
//        value.foreach(customer => LOGGER.info(customer.toString))
//      }
//      case Failure(e) => {
//        LOGGER.severe(e.getMessage)
//      }
//    }
//
//    var customer: Customer = Customer(BSONString(BSONObjectID.generate().stringify), "", "")
//    Await.result(customers, Duration.Inf) {
//      customer = customers.value.get.get.head
//      0
//    }
//    customer
//  }
  override def create(t: Customer): Unit = ???

  override def readAll(): Unit = ???

  override def update(t: Customer): Unit = ???

  override def delete(id: String): Unit = ???
}
