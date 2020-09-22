package com.ims.controller

import java.util.Scanner
import java.util.logging.Logger

import com.ims.dao.CustomerDAO
import com.ims.domain.Customer

object OrderController extends Controller {

  private val SCANNER = new Scanner(Console.in)
  private val LOGGER = Logger.getLogger(OrderController.getClass.getSimpleName)

  def getInput(): String = {
    SCANNER.nextLine()
  }

  override def create: Unit = println("order create test")

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???
}
