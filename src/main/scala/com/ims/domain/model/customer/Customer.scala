package com.ims.domain.model.customer

import com.ims.domain.model.Id
import reactivemongo.api.bson.BSONString

case class Customer(_id: Id = Id(BSONString("")), forename: String = "", surname: String = "") {
  def setId(_id: String, customer: Customer): Customer = {
    val newId = BSONString(_id)
    customer.copy(_id = Id(newId))
  }

  def removeId(customer: Customer): Customer = customer.copy(_id = Id())

  def setForename(forename: String, customer: Customer): Customer = customer.copy(forename = forename)

  def removeForename(customer: Customer): Customer = customer.copy(forename = "")

  def setSurname(surname: String, customer: Customer): Customer = customer.copy(surname = surname)

  def removeSurname(customer: Customer): Customer = customer.copy(surname = "")
}
