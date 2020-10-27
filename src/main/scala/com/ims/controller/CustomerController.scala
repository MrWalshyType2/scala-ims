package com.ims.controller

import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.Future

object CustomerController extends Controller[Customer] {

  val dao = CustomerDao

  override def create(customer: Customer): Future[Option[Customer]] = dao.create(customer)

  override def readById(id: BSONObjectID): Future[Option[Customer]] = dao.readById(id)

  override def readAll(): Future[List[Customer]] = dao.readAll()

  override def update(customer: Customer): Future[Option[Customer]] = dao.update(customer)

  override def delete(id: BSONObjectID): Unit = dao.delete(id)
}
