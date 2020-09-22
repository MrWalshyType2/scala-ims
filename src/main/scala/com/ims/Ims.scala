package com.ims

import java.util.logging.{Level, Logger}

import com.ims.controller.{Controller, CustomerController, ItemController, OrderController}
import com.ims.crud.CRUD
import com.ims.domain.Domain

object Ims {

  private val LOGGER = Logger.getLogger(Ims.getClass.getSimpleName)
  private var USERNAME: String = ""
  private var PASSWORD: String = ""

  def menu(): Unit = {
    Domain.getDomains().foreach(domain => println(domain))
    val domain: String = getInput()

    if (domain.equalsIgnoreCase("EXIT")) System.exit(0)
    else
      LOGGER.info(s"What would you like to do with $domain?")
      CRUD.getValues().foreach(value => println(value))

      val operation: String = getInput()

      val crud = List("CREATE", "READ", "UPDATE", "DELETE")

      if (crud.contains(operation.toUpperCase()))
        if (domain.equalsIgnoreCase("CUSTOMER")) doAction(CustomerController: Controller, operation)
        else if (domain.equalsIgnoreCase("ITEM")) doAction(ItemController: Controller, operation)
        else if (domain.equalsIgnoreCase("ORDER")) doAction(OrderController: Controller, operation)
      else LOGGER.warning(s"Invalid input | DOMAIN: $domain, OPERATION: $operation")

    menu()
  }

  def doAction(controller: Controller, operation: String): Unit = {
    if (operation.equalsIgnoreCase("CREATE")) controller.create
    else if (operation.equalsIgnoreCase("READ")) controller.readAll
    else if (operation.equalsIgnoreCase("UPDATE")) controller.update
    else if (operation.equalsIgnoreCase("DELETE")) controller.delete
    else println(s"Invalid --> doAction() $operation")
  }

//  def createCustomer(customer: Customer): Future[Unit] =
//    customerCollection.flatMap(_.insert.one(customer).map(_ => {}))

}
