package com.ims.ui

import java.awt.{Color, ComponentOrientation, Font}

import com.ims.controller.{Controller, CustomerController}

import scala.swing.Swing.{CompoundBorder, EmptyBorder, EtchedBorder, TitledBorder}
import scala.swing.event.{ButtonClicked, EditDone}
import scala.swing.{Action, BorderPanel, BoxPanel, Button, ButtonGroup, CheckBox, Component, Dimension, FlowPanel, Graphics2D, GridBagPanel, GridPanel, Label, ListView, MainFrame, Menu, MenuBar, MenuItem, Orientation, Panel, PasswordField, RadioButton, Reactor, ScrollPane, Separator, SplitPane, Swing, TabbedPane, Table, TextArea, TextField, TextPane, ToggleButton}
import com.ims.domain.dao.{BaseDaoInterface, CustomerDao}
import com.ims.domain.model.customer.Customer
import com.ims.domain.model.order.Order
import com.ims.domain.services.CustomerService
import reactivemongo.api.bson.BSONObjectID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, Future}
import scala.concurrent.impl.Promise
import scala.util.{Failure, Success}

class UI extends MainFrame {
  // change to Controller once reprogrammed
//  val customerController = CustomerController
  object customerService extends CustomerService {
    override val dao: BaseDaoInterface[Customer] = CustomerDao

    override val controller: Controller[Customer] = CustomerController
  }
  import customerService._

  val customPurple = new Color(76,2,195)
  val customBlack = new Color(24,24,24)

  title = "IMS"

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }

  val customerBtn = new Button {text = "Customer"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32)}
  val itemBtn = new Button {text = "Item"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32)}
  val orderBtn = new Button {text = "Order"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32)}

  val createBtn = new Button { text = "Create"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32) }
  val readBtn = new Button { text = "Read"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32) }
  val updateBtn = new Button { text = "Update"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32) }
  val deleteBtn = new Button { text = "Delete"; background = customBlack; foreground = Color.WHITE; font = new Font("Arial", 0, 32) }

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

  val orderRowData = Array(
    Array[Any]("jr49h849ht943h", "jf9438j98390u8j", Array[Any]("40398u4u98tu4", "jf9438tj493u4u", "u9849ut489ytyhh").toString, 25.50)
//    Array("jr49h849ht943h", "jf9438j98390u8j", Array[String]("40398u4u98tu4", "jf9438tj493u4u", "u9849ut489ytyhh"))
  )
  //new Order(id bsonid, customer_id bsonid, orderItems BSONDoc)
  var table = new ScrollPane(
    new Table(customerRowData, customerHeaders){
      font = new Font("Arial", 0, 20)
      rowHeight = 24
      autoResizeMode = Table.AutoResizeMode.NextColumn
    }
  )

  var mainPanel = mainPanelBuilder(topButtons, leftMenu, table)

  listenTo(`customerBtn`, `itemBtn`, `orderBtn`, `createBtn`, `readBtn`, `updateBtn`, `deleteBtn`)

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
          table.contents = new Table(arrArr, customerHeaders){
            font = new Font("Arial", 0, 20)
            rowHeight = 24
            autoResizeMode = Table.AutoResizeMode.NextColumn
          }
        }
        case Failure(exception) => throw exception
      }
    }
    case ButtonClicked(`itemBtn`) => {
      println("Item btn clicked")
      contents = mainPanelBuilder(topButtons, leftMenu, table)
      table.contents = new Table(getItemData(), itemHeaders){
        font = new Font("Arial", 0, 20)
        rowHeight = 24
        autoResizeMode = Table.AutoResizeMode.NextColumn
      }
    }
    case ButtonClicked(`orderBtn`) => {
      println("Order btn clicked")
      contents = mainPanelBuilder(topButtons, leftMenu, table)
      table.contents = new Table(getOrderData(), orderHeaders){
        font = new Font("Arial", 0, 20)
        rowHeight = 24
        autoResizeMode = Table.AutoResizeMode.NextColumn
      }
    }
    case ButtonClicked(`createBtn`) => contents = mainPanelBuilder(topButtons, leftMenu, Form.creationForm)
    case ButtonClicked(`updateBtn`) => contents = mainPanelBuilder(topButtons, leftMenu, Form.updateForm)
    case ButtonClicked(`deleteBtn`) => contents = mainPanelBuilder(topButtons, leftMenu, Form.deleteForm)
    case ButtonClicked(`readBtn`) => contents = mainPanelBuilder(topButtons, leftMenu, Form.readForm)
    case ButtonClicked(button) => println(s"Clicked button: ${button.text}")
  }

  contents = mainPanel

  def mainPanelBuilder(top: GridPanel, left: GridPanel, centre: ScrollPane): BorderPanel = {
    preferredSize = new Dimension(1940, 920)
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
    controller.readAll()
  }

  def getItemData() = {
    itemRowData
  }

  def getOrderData(): Array[Array[Any]] = {
    orderRowData
  }
}


