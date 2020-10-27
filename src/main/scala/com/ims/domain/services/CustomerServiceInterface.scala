package com.ims.domain.services

import com.ims.controller.Controller
import com.ims.domain.dao.BaseDaoInterface
import com.ims.domain.model.customer.Customer

trait CustomerServiceInterface {

  protected def dao: BaseDaoInterface[Customer]
  protected def controller: Controller[Customer]

  def setId(_id: String, customer: Customer): Customer
  def removeId(customer: Customer): Customer

  def setForename(forename: String, customer: Customer): Customer
  def removeForename(customer: Customer): Customer

  def setSurname(surname: String, customer: Customer): Customer
  def removeSurname(customer: Customer): Customer
}
