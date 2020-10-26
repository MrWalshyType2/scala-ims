package com.ims.domain.dao

import com.ims.DBConnection.{customerReader, customerWriter}
import com.ims.DBConnection.customerCollection
import com.ims.domain.model.customer.Customer
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, document}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object CustomerDao extends BaseDaoInterface[Customer] {

  override def create(customer: Customer): Future[Option[Customer]] = {
    val newCustomer: Future[WriteResult] = customerCollection.flatMap(_.insert.one(customer))
    newCustomer.flatMap { writeResult =>
      readById(customer._id)
    }
  }

  override def readById(id: BSONObjectID): Future[Option[Customer]] = {
    val selector = document("_id" -> id)
    customerCollection.flatMap(_.find(selector).one)
  }

  override def readAll(): Future[List[Customer]] = {
    val customers: Future[List[Customer]] = customerCollection.flatMap(_.find(document())
      .cursor[Customer]()
      .collect[List](-1, Cursor.FailOnError[List[Customer]]()))
    customers
  }

  override def update(customer: Customer): Future[Option[Customer]] = {
    val selector = document("_id" -> customer._id)
    val writeResult = customerCollection.flatMap(_.findAndUpdate(selector, customer))
    writeResult.map(_.result[Customer])
  }

  override def delete(id: BSONObjectID): Unit = {
    val selector = document("_id" -> id)
    customerCollection.flatMap(_.delete.one(selector))
  }
}
