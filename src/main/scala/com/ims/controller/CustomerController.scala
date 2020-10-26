package com.ims.controller

import java.util.{Scanner, UUID}
import java.util.logging.Logger

import com.ims.controller.OrderController.LOGGER
import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.Id
import com.ims.domain.model.customer.Customer
import com.ims.domain.services.CustomerService
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

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
    val id = Id(BSONString(BSONObjectID.generate.stringify))
    val customer = Customer(id, forename, surname)

    val newCustomer = CustomerDao.create(customer)
    newCustomer.map {
      case Some(value) => println("Customer created:" + value)
      case None => println("Something went wrong")
    }
  }

  override def readAll: Unit = {
    CustomerDao.readAll()
  }

  override def update: Unit = {
    LOGGER.info("Enter the customer id:")
    val id = Id(BSONString(getInput()))
    val customer: Future[Option[Customer]] = CustomerDao.readById(id)
    LOGGER.info("Retrieved Customer successfully")
    LOGGER.info(customer.toString)

    LOGGER.info("FORENAME:")
    val forename = getInput()
    LOGGER.info("SURNAME:")
    val surname = getInput()
    val updatedCustomer = Customer(id, forename, surname)
    CustomerDao.update(updatedCustomer)
  }

  override def delete: Unit = {
    LOGGER.info("Enter the customer id:")
    val id = Id(BSONString(getInput()))

    CustomerDao.delete(id)
  }
}
