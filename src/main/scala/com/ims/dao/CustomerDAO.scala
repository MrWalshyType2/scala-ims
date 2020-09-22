package com.ims.dao

import java.util.UUID

import com.ims.DBConnection.{customerCollection, customerReader, customerWriter}
import com.ims.domain.Customer
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.{Collection, Cursor}
import reactivemongo.api.bson.document

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, ExecutionContext, Future}

object CustomerDAO extends DAO[Customer] {

  override def create(customer: Customer): Unit = {
    customerCollection.flatMap(_.insert.one(customer).map(_ => {}))
  }

  override def readAll(): Unit = {
    val customers: Future[List[Customer]] = customerCollection.flatMap(_.find(document())
    .cursor[Customer]().collect[List](-1, Cursor.FailOnError[List[Customer]]()))

    customers.andThen()

    customers.onComplete(_ => {
      println(customers)
    })

    Await.ready(customers, Duration(1000, MILLISECONDS))
  }

  override def update(t: Customer): Unit = ???

  override def delete(id: UUID): Unit = ???
}
