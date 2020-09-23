package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import com.ims.dao.{ItemDAO, OrderDAO}
import com.ims.domain.{Item, Order}
import reactivemongo.api.bson.{BSONDocument, BSONInteger, BSONObjectID, BSONString}

import scala.:+
import scala.annotation.tailrec

object OrderController extends Controller {

  private val LOGGER = Logger.getLogger(OrderController.getClass.getSimpleName)

  override def create: Unit = {
    @tailrec
    def addItemLoop(order: Order): Unit = {
      LOGGER.info("ENTER ITEM ID:")
      val itemId = getInput()
      LOGGER.info("ENTER ITEM QUANTITY:")
      var quantity = getInput()

      val orderItems = order.orderItems ++ (itemId -> quantity.toInt)
      orderItems.elements.foreach(element => {
        println(s"ITEM: ${element.name}")
        println(s"QUANTITY: ${BSONInteger.unapply(element.value).get}")
      })

      val updatedOrder = Order(order._id, order.customer_id, orderItems)

      LOGGER.info("CONTINUE? Y/N")
      val answer = getInputLoop(Seq("Y", "N"))

      if (answer == "N") {
        OrderDAO.create(updatedOrder: Order)
        LOGGER.info("Order created successfully!")
      }
      else addItemLoop(updatedOrder)
    }

    LOGGER.info("ENTER CUSTOMER ID:")
    val customerId = BSONString(getInput())

    LOGGER.info("ENTER ITEM ID:")
    val itemId = getInput()
    LOGGER.info("ENTER ITEM QUANTITY:")
    val quantity = getInput()

    val order = Order(BSONString(BSONObjectID.generate().stringify), customerId, BSONDocument(itemId -> quantity.toInt))
    LOGGER.info("Would you like to continue adding items to your order? Y/N")
    val answer = getInputLoop(Seq("Y", "N"))

    if (answer.equalsIgnoreCase("Y")) addItemLoop(order)
    else
      OrderDAO.create(order: Order)
      LOGGER.info("Order created successfully!")
  }

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???

  @tailrec
  private def getInputLoop(validTerms: Seq[String]): String = {
    val userInput: String = getInput()
    if (validTerms.contains(userInput)) userInput
    else getInputLoop(validTerms)
  }
}
