package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import com.ims.dao.ItemDAO
import com.ims.domain.Item
import reactivemongo.api.bson.{BSONObjectID, BSONString}

object ItemController extends Controller {

  private val LOGGER: Logger = Logger.getLogger(ItemController.getClass.getSimpleName)

  override def create: Unit = {
    LOGGER.info("ITEM NAME:")
    val name: String = getInput()
    LOGGER.info("ITEM VALUE:")
    val value: String = getInput()

    ItemDAO.create(Item(BSONString(BSONObjectID.generate().stringify), name, value.toInt))
  }

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???
}
