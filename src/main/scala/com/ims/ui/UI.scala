package com.ims.ui

import java.awt.{Color, ComponentOrientation}

import scala.swing.Swing.{CompoundBorder, EmptyBorder, EtchedBorder, TitledBorder}
import scala.swing.event.{ButtonClicked, EditDone}
import scala.swing.{Action, BorderPanel, BoxPanel, Button, ButtonGroup, CheckBox, Component, Dimension, FlowPanel, Graphics2D, GridBagPanel, GridPanel, Label, ListView, MainFrame, Menu, MenuBar, MenuItem, Orientation, PasswordField, RadioButton, Reactor, ScrollPane, Separator, SplitPane, Swing, TabbedPane, Table, TextArea, TextField, TextPane, ToggleButton}
import com.ims.domain.dao.CustomerDao
import com.ims.domain.model.customer.Customer
import reactivemongo.api.bson.BSONObjectID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, Future}
import scala.concurrent.impl.Promise
import scala.util.{Failure, Success}

class UI extends MainFrame {
  val customerDao = CustomerDao

  title = "IMS"

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }

  val customerBtn = new Button {text = "Customer"}
  val itemBtn = new Button {text = "Item"}
  val orderBtn = new Button {text = "Order"}

  val createBtn = new Button { text = "Create" }
  val readBtn = new Button { text = "Read" }
  val updateBtn = new Button { text = "Update" }
  val deleteBtn = new Button { text = "Delete" }

  val topButtons = new GridPanel(1, 3) {
    contents += customerBtn
    contents += itemBtn
    contents += orderBtn
  }

  val leftMenu = new GridPanel(4, 1) {
    contents += createBtn
    contents += readBtn
    contents += updateBtn
    contents += deleteBtn
  }

  val customerHeaders = Array("ID", "Forename", "Surname")
  val itemHeaders = Array("ID", "Name", "Description", "Price", "Quantity")
  val orderHeaders = Array("ID", "Customer ID", "Items", "Cost")

  var customerRowData = Array(
    Array[Any]("4234j24rthj4398th", "Fred", "Gubbins"),
    Array[Any]("j4948u984ut98t98u", "Grognak", "Barbarian")
  )
  val itemRowData = Array(
    Array("j9jt894h9t8h598th", "Freddo", "Chocolate bar", 00.50, 20),
    Array("9t8h9453th8h9th98", "Apples", "Fruit", 00.20, 32)
  )

  var table = new ScrollPane(new Table(customerRowData, customerHeaders))

  var mainPanel = mainPanelBuilder(topButtons, leftMenu, table)

  listenTo(`customerBtn`, `itemBtn`, `orderBtn`, `createBtn`)

  reactions += {
    case ButtonClicked(`customerBtn`) => {
      println("Customer btn clicked")
      contents = mainPanelBuilder(topButtons, leftMenu, table)
      val results = getCustomerData()
      results andThen {
        case Success(value) => {
          val arr = value.toArray
          val arrArr = arr.map { c =>
            Array[Any](c._id.toString(), c.forename, c.surname)
          }
          table.contents = new Table(arrArr, customerHeaders)
        }
        case Failure(exception) => throw exception
      }
    }
    case ButtonClicked(`itemBtn`) => {
      println("Item btn clicked")
      contents = mainPanelBuilder(topButtons, leftMenu, table)
      table.contents = new Table(getItemData(), itemHeaders)
    }
    case ButtonClicked(`createBtn`) => contents = mainPanelBuilder(topButtons, leftMenu, new CreationForm().creationForm)
    case ButtonClicked(button) => println(s"Clicked button: ${button.text}")
  }

  contents = mainPanel

  def mainPanelBuilder(top: GridPanel, left: GridPanel, centre: ScrollPane): BorderPanel = {
    new BorderPanel {
      add(top, BorderPanel.Position.North)
      add(left, BorderPanel.Position.West)
      add(centre, BorderPanel.Position.Center)
    }
  }

  def mainPanelBuilder(top: GridPanel, left: GridPanel, centre: BoxPanel): BorderPanel = {
    new BorderPanel {
      add(top, BorderPanel.Position.North)
      add(left, BorderPanel.Position.West)
      add(centre, BorderPanel.Position.Center)
    }
  }

  def getCustomerData() = {
    customerDao.readAll()
  }

  def getItemData() = {
    itemRowData
  }
}


