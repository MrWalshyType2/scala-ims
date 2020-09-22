package com.ims.controller

import java.util.{Scanner, UUID}
import java.util.logging.Logger

import com.ims.controller.OrderController.{LOGGER, getInput}
import com.ims.dao.CustomerDAO
import com.ims.domain.Customer
import reactivemongo.api.bson.{BSONObjectID, BSONString}

object CustomerController extends Controller {

  private val SCANNER = new Scanner(Console.in)
  private val LOGGER = Logger.getLogger(OrderController.getClass.getSimpleName)

  def getInput(): String = {
    SCANNER.nextLine()
  }

  override def create: Unit = {
    LOGGER.info("FORENAME:")
    val forename = getInput()
    LOGGER.info("SURNAME:")
    val surname = getInput()

    CustomerDAO.create(new Customer(BSONString(BSONObjectID.generate().stringify), forename, surname))
  }

  override def readAll: Unit = {
    CustomerDAO.readAll()
  }

  override def update: Unit = {
    LOGGER.info("Enter the customer id:")
    val id = getInput()
    val customer: Customer = CustomerDAO.readById(id)
    LOGGER.info("Retrieved Customer successfully")
    LOGGER.info(customer.toString)

    LOGGER.info("FORENAME:")
    val forename = getInput()
    LOGGER.info("SURNAME:")
    val surname = getInput()
    val updatedCustomer = new Customer(customer._id, forename, surname)
    CustomerDAO.update(updatedCustomer)
  }

  override def delete: Unit = ???
}
