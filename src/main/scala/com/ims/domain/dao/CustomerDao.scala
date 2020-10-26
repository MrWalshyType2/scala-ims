package com.ims.domain.dao

import com.ims.DBConnection.{customerCollection, customerReader, customerWriter}
import com.ims.DBConnection.customerCollection
import com.ims.domain.model.Id
import com.ims.domain.model.customer.Customer
import reactivemongo.api.Cursor
import reactivemongo.api.bson.document
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object CustomerDao extends BaseDaoInterface[Customer] {

  override def create(t: Customer): Future[Option[Customer]] = {
    val newCustomer: Future[WriteResult] = customerCollection.flatMap(_.insert.one(t))
    newCustomer.flatMap { writeResult =>
      readById(t._id)
    }
  }

  override def readByName(name: String): Future[Option[Customer]] = ???

  override def readById(id: Id): Future[Option[Customer]] = ???

  override def readAll(): Future[Seq[Customer]] = {
    val customers: Future[List[Customer]] = customerCollection.flatMap(_.find(document())
      .cursor[Customer]()
      .collect[List](-1, Cursor.FailOnError[List[Customer]]()))
    customers
  }

  override def update(t: Customer): Future[Option[Customer]] = ???

  override def delete(id: Id): Unit = ???
}
