package com.ims.ui

import java.awt.Color

import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.BSONObjectID

import scala.swing.{BoxPanel, Button, Component, GridPanel, Label, Orientation, TextField}
import scala.swing.event.ButtonClicked

class CreationForm {

  val customerDao = CustomerDao

  def creationForm = new BoxPanel(Orientation.Vertical) {
    val customerHeaders = Array("ID", "Forename", "Surname")
    val itemHeaders = Array("ID", "Name", "Description", "Price", "Quantity")
    val orderHeaders = Array("ID", "Customer ID", "Items", "Cost")

    val customerId = new TextField()
    val customerForename = new TextField()
    val customerSurname = new TextField()
    val createCustomerBtn = new Button("Submit")

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

      listenTo(`createCustomerBtn`, `createItemBtn`, `createOrderBtn`)

      reactions += {
        case ButtonClicked(`createCustomerBtn`) => {
          println("Creating customer")
          createCustomer(customerForename.text, customerSurname.text)
        }
      }
    }
  }

  def createCustomer(customerForename: String, customerSurname: String): Unit = {
    val customer = Customer(BSONObjectID.generate(), customerForename, customerSurname)
    customerDao.create(customer)
  }
}
