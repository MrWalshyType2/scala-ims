package com.ims.controller

import java.util.Scanner
import java.util.logging.Logger

import com.ims.controller.OrderController.{LOGGER, getInput}
import com.ims.dao.CustomerDAO
import com.ims.domain.Customer

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

    CustomerDAO.create(new Customer(forename, surname))
  }

  override def readAll: Unit = {
    CustomerDAO.readAll()
  }

  override def update: Unit = {

  }

  override def delete: Unit = ???
}
