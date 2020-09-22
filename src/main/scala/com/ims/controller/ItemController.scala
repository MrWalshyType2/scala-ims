package com.ims.controller

import java.util.logging.Logger

object ItemController extends Controller {

  private val LOGGER: Logger = Logger.getLogger(ItemController.getClass.getSimpleName)

  override def create: Unit = {
    LOGGER.info("ITEM NAME:")

  }

  override def readAll: Unit = ???

  override def update: Unit = ???

  override def delete: Unit = ???
}
