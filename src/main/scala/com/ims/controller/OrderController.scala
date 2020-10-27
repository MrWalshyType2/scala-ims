package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import reactivemongo.api.bson.{BSONDocument, BSONElement, BSONInteger, BSONObjectID, BSONString}

import scala.:+
import scala.annotation.tailrec

object OrderController extends Controller {

  private val LOGGER = Logger.getLogger(OrderController.getClass.getSimpleName)

//  override def create: Unit = {
//    @tailrec
//    def addItemLoop(order: Order): Unit = {
//      LOGGER.info("ENTER ITEM ID:")
//      val itemId = getInput()
//      LOGGER.info("ENTER ITEM QUANTITY:")
//      var quantity = getInput()
//
//      val orderItems = order.orderItems ++ (itemId -> quantity.toInt)
//      orderItems.elements.foreach(element => {
//        println(s"ITEM: ${element.name}")
//        println(s"QUANTITY: ${BSONInteger.unapply(element.value).get}")
//      })
//
//      val updatedOrder = Order(order._id, order.customer_id, orderItems)
//
//      LOGGER.info("CONTINUE? Y/N")
//      val answer = getInputLoop(Seq("Y", "N"))
//
//      if (answer == "N") {
//        OrderDAO.create(updatedOrder)
//        LOGGER.info(updatedOrder.toString)
//        LOGGER.info(f"ORDER COST: £${calculateCost(updatedOrder.orderItems.elements)}%.2f")
//        LOGGER.info("Order created successfully!")
//      }
//      else addItemLoop(updatedOrder)
//    }
//
//    LOGGER.info("ENTER CUSTOMER ID:")
//    val customerId = BSONString(getInput())
//
//    LOGGER.info("ENTER ITEM ID:")
//    val itemId = getInput()
//    LOGGER.info("ENTER ITEM QUANTITY:")
//    val quantity = getInput()
//
//    val order = Order(BSONString(BSONObjectID.generate().stringify), customerId, BSONDocument(itemId -> quantity.toInt))
//    LOGGER.info("Would you like to continue adding items to your order? Y/N")
//    val answer = getInputLoop(Seq("Y", "N"))
//
//    if (answer.equalsIgnoreCase("Y")) addItemLoop(order)
//    else {
//      OrderDAO.create(order: Order)
//      LOGGER.info("Order created successfully!")
//      LOGGER.info(order.toString)
//      LOGGER.info(f"ORDER COST: £${calculateCost(order.orderItems.elements)}%.2f")
//    }
//  }
//
//  override def readAll: Unit = {
//    OrderDAO.readAll()
//  }
//
//  override def update: Unit = {
//    LOGGER.info("Please enter your order ID: ")
//    val orderId = getInput()
//    val order = OrderDAO.readById(orderId)
//    LOGGER.info(s"${order.toString}\nRetrieved Order successfully")
//
//    var updatedOrder: Order = Order(BSONString(""), BSONString(""), BSONDocument())
//    LOGGER.info("Would you like to add items to your order? Y/N")
//    val answer = getInputLoop(Seq("Y", "N"))
//    if (answer.equalsIgnoreCase("Y")) updatedOrder = addItemLoop(order)
//    else {
//      LOGGER.info("Would you like to delete an item from your order? Y/N")
//      val answer = getInputLoop(Seq("Y", "N"))
//      if (answer.equalsIgnoreCase("Y")) updatedOrder = deleteItemLoop(order)
//    }
//    OrderDAO.update(updatedOrder)
//    LOGGER.info("Order updated successfully")
//    LOGGER.info(updatedOrder.toString)
//    LOGGER.info(f"ORDER COST: £${calculateCost(updatedOrder.orderItems.elements)}%.2f")
//  }
//
//  override def delete: Unit = {
//    LOGGER.info("Enter the order id:")
//    val id = getInput()
//
//    OrderDAO.delete(id)
//    LOGGER.info("Order deleted successfully")
//  }
//
//  private def calculateCost(itemIds: Seq[BSONElement]): Double = {
//    val items = itemIds.map { itemTuple => itemTuple.name -> BSONInteger.unapply(itemTuple.value).get }
//
//    @tailrec
//    def calculate(cost: Double, count: Int, items: Seq[Tuple2[String, Int]]): Double = {
//      if (count < 0) cost / 100.00
//      else calculate(cost + (ItemDAO.readById(items(count)._1).value * items(count)._2), count - 1, items) // cost + (item_value * amount)
//    }
//    calculate(0, items.length - 1, items)
//  }
//
//  @tailrec
//  private def getInputLoop(validTerms: Seq[String]): String = {
//    val userInput: String = getInput()
//    if (validTerms.contains(userInput)) userInput
//    else getInputLoop(validTerms)
//  }
//
//  @tailrec
//  def addItemLoop(order: Order): Order = {
//    LOGGER.info("ENTER ITEM ID:")
//    val itemId = getInput()
//    LOGGER.info("ENTER ITEM QUANTITY:")
//    var quantity = getInput()
//
//    val orderItems = order.orderItems ++ (itemId -> quantity.toInt)
//    orderItems.elements.foreach(element => {
//      println(s"ITEM: ${element.name}")
//      println(s"QUANTITY: ${BSONInteger.unapply(element.value).get}")
//    })
//
//    val updatedOrder = Order(order._id, order.customer_id, orderItems)
//
//    LOGGER.info("CONTINUE? Y/N")
//    val answer = getInputLoop(Seq("Y", "N"))
//
//    if (answer == "N") updatedOrder
//    else addItemLoop(updatedOrder)
//  }
//
//  @tailrec
//  def deleteItemLoop(order: Order): Order = {
//    LOGGER.info("ENTER ITEM ID:")
//    val itemId = getInput()
//
//    val orderItems = order.orderItems -- itemId
//    val updatedOrder = Order(order._id, order.customer_id, orderItems)
//
//    LOGGER.info(updatedOrder.toString)
//    LOGGER.info("Would you like to delete another item? Y/N")
//    val answer = getInputLoop(Seq("Y", "N"))
//
//    if (answer.equalsIgnoreCase("N")) updatedOrder
//    else deleteItemLoop(updatedOrder)
//  }
  override def create: Unit = ???

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???
}
