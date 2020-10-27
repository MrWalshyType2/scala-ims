package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import reactivemongo.api.bson.{BSONObjectID, BSONString}

object ItemController extends Controller {

//  private val LOGGER: Logger = Logger.getLogger(ItemController.getClass.getSimpleName)
//
//  override def create: Unit = {
//    LOGGER.info("ITEM NAME:")
//    val name: String = getInput()
//    LOGGER.info("ITEM VALUE:")
//    val value: String = getInput()
//
//    ItemDAO.create(Item(BSONString(BSONObjectID.generate().stringify), name, value.toInt))
//  }
//
//  override def readAll: Unit = {
//    ItemDAO.readAll()
//  }
//
//  override def update: Unit = {
//    LOGGER.info("Enter the item id:")
//    val id = getInput()
//    val item: Item = ItemDAO.readById(id)
//
//    LOGGER.info("Item retrieved successfully")
//    LOGGER.info(item.toString)
//
//    LOGGER.info("NEW NAME:")
//    val name = getInput()
//    LOGGER.info("NEW VALUE:")
//    val value = getInput()
//    val updatedItem = Item(item._id, name, value.toInt)
//    ItemDAO.update(updatedItem)
//  }
//
//  override def delete: Unit = {
//    LOGGER.info("Enter the item id:")
//    val id = getInput()
//
//    ItemDAO.delete(id)
//  }
  override def create: Unit = ???

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???
}
