package com.ims.controller

import java.util.{Scanner, UUID}
import java.util.logging.Logger

import com.ims.controller.OrderController.LOGGER
import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.customer.Customer
import com.ims.domain.services.CustomerService
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

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
    val id = BSONObjectID.generate
    val customer = Customer(id, forename, surname)

    val newCustomer = CustomerDao.create(customer)
    newCustomer.map {
      case Some(value) => println("Customer created:" + value)
      case None => println("Something went wrong")
    }
  }

  override def readAll: Unit = {
    val customers = CustomerDao.readAll()
    customers andThen {
      case Success(value) => value.foreach(println)
      case Failure(exception) => println(exception)
    }
  }

  override def update: Unit = {
    LOGGER.info("Enter the customer id:")
    val cid = BSONObjectID.parse(getInput())
    val id = cid match {
      case Success(value) => value
      case Failure(exception) => throw exception
    }

    val customer: Future[Option[Customer]] = CustomerDao.readById(id)
    customer.flatMap {
      case Some(value) => {
        LOGGER.info("Retrieved Customer successfully")
        LOGGER.info(value.toString)
        Future(None)
      }
      case None => {
        LOGGER.info("Customer does not exist")
        Future(None)
      }
    }
    LOGGER.info("FORENAME:")
    val forename = getInput()
    LOGGER.info("SURNAME:")
    val surname = getInput()
    val updatedCustomer = Customer(id, forename, surname)
    val cust = CustomerDao.update(updatedCustomer)

    cust andThen {
      case Success(value) => value match {
        case Some(value) => println("UPDATED CUSTOMER: " + updatedCustomer)
        case None => println("Customer failed to update successfully")
      }
      case Failure(exception) => println(exception)
    }
  }

  override def delete: Unit = {
    LOGGER.info("Enter the customer id:")
    val id = BSONObjectID.parse(getInput()) match {
      case Success(value) => value
      case Failure(exception) => throw exception
    }
    CustomerDao.delete(id)
  }
}
