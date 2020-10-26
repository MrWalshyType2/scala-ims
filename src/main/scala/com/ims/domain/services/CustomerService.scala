package com.ims.domain.services

import com.ims.domain.model.Id
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.BSONString

trait CustomerService extends CustomerServiceInterface {

  override def setId(_id: String, customer: Customer): Customer = {
    val newId = BSONString(_id)
    customer.copy(_id = Id(newId))
  }

  override def removeId(customer: Customer): Customer = customer.copy(_id = Id())

  override def setForename(forename: String, customer: Customer): Customer = customer.copy(forename = forename)

  override def removeForename(customer: Customer): Customer = customer.copy(forename = "")

  override def setSurname(surname: String, customer: Customer): Customer = customer.copy(surname = surname)

  override def removeSurname(customer: Customer): Customer = customer.copy(surname = "")
}
