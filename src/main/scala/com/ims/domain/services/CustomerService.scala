package com.ims.domain.services

import com.ims.domain.dao.BaseDaoInterface
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.util.{Failure, Success}

trait CustomerService extends CustomerServiceInterface {

//  override val dao: BaseDaoInterface[Customer] = ???

  override def setId(_id: String, customer: Customer): Customer = {
    val newId = BSONObjectID.parse(_id) match {
      case Success(value) => value
      case Failure(exception) => BSONObjectID.generate()
    }
    customer.copy(_id = newId)
  }

  override def removeId(customer: Customer): Customer = {
    customer.copy(_id = BSONObjectID.parse("") match {
      case Success(value) => value
      case Failure(exception) => BSONObjectID.generate()
    })
  }

  override def setForename(forename: String, customer: Customer): Customer = customer.copy(forename = forename)

  override def removeForename(customer: Customer): Customer = customer.copy(forename = "")

  override def setSurname(surname: String, customer: Customer): Customer = customer.copy(surname = surname)

  override def removeSurname(customer: Customer): Customer = customer.copy(surname = "")
}
