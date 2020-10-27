package com.ims.ui

import java.awt.Color

import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.BSONObjectID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.swing.{BoxPanel, Button, Component, GridPanel, Label, Orientation, ScrollPane, Table, TextField}
import scala.swing.event.ButtonClicked
import scala.util.{Failure, Success}

object Form {

  val customPurple = new Color(130,85,255)
  // Change to Controller once reprogrammed
  val customerDao = CustomerDao

  def creationForm = new BoxPanel(Orientation.Vertical) {
    val customerHeaders = Array("ID", "Forename", "Surname")
    val itemHeaders = Array("ID", "Name", "Description", "Price", "Quantity")
    val orderHeaders = Array("ID", "Customer ID", "Items", "Cost")

    val customerId = new TextField()
    val customerForename = new TextField()
    val customerSurname = new TextField()
    val createCustomerBtn = new Button("Submit")
    createCustomerBtn.background = customPurple

    contents += new GridPanel(9, 12) {
      background = Color.WHITE
      contents += new Label("Customer") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, customerHeaders.length - 1) {
        customerHeaders.foreach(header => if (header != "ID") contents += new Label(header))
        contents += customerForename
        contents += customerSurname
      }
      contents += createCustomerBtn

      contents += new Label("Item") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, itemHeaders.length) {
        itemHeaders.foreach(header => contents += new Label(header))
        itemHeaders.foreach(header => contents += new TextField(header))
      }
      val createItemBtn = new Button("Submit"){background = customPurple}
      contents += createItemBtn

      contents += new Label("Order") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, orderHeaders.length) {
        orderHeaders.foreach(header => contents += new Label(header))
        orderHeaders.foreach(header => contents += new TextField(header))
      }
      val createOrderBtn = new Button("Submit") {background = customPurple}
      contents += createOrderBtn

      listenTo(`createCustomerBtn`, `createItemBtn`, `createOrderBtn`)

      reactions += {
        case ButtonClicked(`createCustomerBtn`) => {
          println("Creating customer")
          createCustomer(customerForename.text, customerSurname.text)
        }
      }
    }
  }

  private def createCustomer(customerForename: String, customerSurname: String): Unit = {
    val customer = Customer(BSONObjectID.generate(), customerForename, customerSurname)
    customerDao.create(customer)
  }

  /**
   * Requires full-screen to view Results table.
   * @return
   */
  def readForm: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val customerHeaders = Array("ID")
    val itemHeaders = Array("ID")
    val orderHeaders = Array("ID")

    val customerId = new TextField()
    val readCustomerBtn = new Button("Submit")

    contents += new GridPanel(12, 12) {
      background = Color.WHITE
      contents += new Label("Customer") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, 1) {
        contents += new Label("ID")
//        customerHeaders.foreach(header => contents += new Label(header))
        contents += customerId
      }
      contents += readCustomerBtn

      contents += new Label("Item") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, 1) {
        contents += new Label("ID")
//        itemHeaders.foreach(header => contents += new Label(header))
//        itemHeaders.foreach(header => contents += new TextField(header))
      }
      val createItemBtn = new Button("Submit")
      contents += createItemBtn

      contents += new Label("Order") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, 1) {
//        orderHeaders.foreach(header => contents += new Label(header))
//        orderHeaders.foreach(header => contents += new TextField(header))
        contents += new Label("ID")
      }
      val createOrderBtn = new Button("Submit")
      contents += createOrderBtn

      contents += new Label("Results") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      var table = new ScrollPane() {
        contents = new Table(Array(Array[Any]("", "", "")), Array("ID", "Forename", "Surname"))
      }
//      val tablewrapper = new GridPanel(2, 3)
//      tablewrapper.contents = table
      contents += table

      listenTo(`readCustomerBtn`, `createItemBtn`, `createOrderBtn`)

      reactions += {
        case ButtonClicked(`readCustomerBtn`) => {
          println("Reading customer")
          val customer = readCustomer(customerId.text)
          customer andThen {
            case Success(value) => {
              value match {
                case Some(value) => {
                  println(value)
                  table.contents = new Table(Array(Array[Any](value._id.toString(), value.forename, value.surname)), Array("ID", "Forename", "Surname"))
                }
                case None => new Table(Array(Array[Any]("ERROR", "ERROR", "ERROR")), Array("ID", "Forename", "Surname"))
              }
            }
            case Failure(exception) => println(exception)
          }
        }
      }
    }
  }

  def readCustomer(id: String): Future[Option[Customer]] = {
    val bsonId = BSONObjectID.parse(id)
    bsonId match {
      case Failure(exception) => {
        println(exception)
        Future(None)
      }
      case Success(value) => customerDao.readById(value)
    }
  }

  def updateForm: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val customerHeaders = Array("ID", "Forename", "Surname")
    val itemHeaders = Array("ID", "Name", "Description", "Price", "Quantity")
    val orderHeaders = Array("ID", "Customer ID", "Items", "Cost")

    val customerId = new TextField()
    val customerForename = new TextField()
    val customerSurname = new TextField()
    val updateCustomerBtn = new Button("Submit")

    contents += new GridPanel(9, 12) {
      background = Color.WHITE
      contents += new Label("Customer") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, customerHeaders.length) {
        customerHeaders.foreach(header => contents += new Label(header))
        contents += customerId
        contents += customerForename
        contents += customerSurname
      }
      contents += updateCustomerBtn

      contents += new Label("Item") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, itemHeaders.length) {
        itemHeaders.foreach(header => contents += new Label(header))
        itemHeaders.foreach(header => contents += new TextField(header))
      }
      val createItemBtn = new Button("Submit")
      contents += createItemBtn

      contents += new Label("Order") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, orderHeaders.length) {
        orderHeaders.foreach(header => contents += new Label(header))
        orderHeaders.foreach(header => contents += new TextField(header))
      }
      val createOrderBtn = new Button("Submit")
      contents += createOrderBtn

      listenTo(`updateCustomerBtn`, `createItemBtn`, `createOrderBtn`)

      reactions += {
        case ButtonClicked(`updateCustomerBtn`) => {
          println("Updating customer")
          updateCustomer(customerId.text, customerForename.text, customerSurname.text)
        }
      }
    }
  }

  private def updateCustomer(id: String, forename: String, surname: String): Unit = {
    val bsonId = BSONObjectID.parse(id)
    bsonId match {
      case Failure(exception) => println(exception)
      case Success(value) => {
        val customer = Customer(value, forename, surname)
        customerDao.update(customer)
      }
    }
  }

  def deleteForm: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val customerHeaders = Array("ID")
    val itemHeaders = Array("ID")
    val orderHeaders = Array("ID")

    val customerId = new TextField()
    val deleteCustomerBtn = new Button("Submit")

    contents += new GridPanel(9, 12) {
      background = Color.WHITE
      contents += new Label("Customer") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, customerHeaders.length) {
        customerHeaders.foreach(header => contents += new Label(header))
        contents += customerId
      }
      contents += deleteCustomerBtn

      contents += new Label("Item") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, itemHeaders.length) {
        itemHeaders.foreach(header => contents += new Label(header))
        itemHeaders.foreach(header => contents += new TextField(header))
      }
      val createItemBtn = new Button("Submit")
      contents += createItemBtn

      contents += new Label("Order") {
        background = Color.DARK_GRAY
        opaque = true
        foreground = Color.WHITE
      }
      contents += new GridPanel(2, orderHeaders.length) {
        orderHeaders.foreach(header => contents += new Label(header))
        orderHeaders.foreach(header => contents += new TextField(header))
      }
      val createOrderBtn = new Button("Submit")
      contents += createOrderBtn

      listenTo(`deleteCustomerBtn`, `createItemBtn`, `createOrderBtn`)

      reactions += {
        case ButtonClicked(`deleteCustomerBtn`) => {
          println("Deleting customer")
          deleteCustomer(customerId.text)
        }
      }
    }
  }

  private def deleteCustomer(id: String): Unit = {
    val bsonId = BSONObjectID.parse(id)
    bsonId match {
      case Failure(exception) => println(exception)
      case Success(value) => customerDao.delete(value)
    }
  }
}
