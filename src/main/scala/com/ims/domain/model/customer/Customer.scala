package com.ims.domain.model.customer

import reactivemongo.api.bson
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.util.{Failure, Success}

case class Customer(_id: BSONObjectID = BSONObjectID.generate, forename: String = "", surname: String = "") {

  def setId(_id: String, customer: Customer): Customer = {
    val newId = BSONObjectID.parse(_id)
    newId match {
      case Success(value) => customer.copy(_id = value)
      case Failure(exception) => customer
    }
  }

  def removeId(customer: Customer): Customer = {
    BSONObjectID.parse("") match {
      case Success(value) => customer.copy(_id = value)
      case Failure(exception) => customer
    }
  }

  def setForename(forename: String, customer: Customer): Customer = customer.copy(forename = forename)

  def removeForename(customer: Customer): Customer = customer.copy(forename = "")

  def setSurname(surname: String, customer: Customer): Customer = customer.copy(surname = surname)

  def removeSurname(customer: Customer): Customer = customer.copy(surname = "")
}
