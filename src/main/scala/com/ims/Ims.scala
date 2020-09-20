package com.ims

import java.util.Scanner
import java.util.logging.Logger

object Ims {

  private val LOGGER = Logger.getLogger(Ims.getClass.getSimpleName)

  def init(): Unit = {
    LOGGER.info("USERNAME:")
    val USERNAME = getInput()

    LOGGER.info("PASSWORD:")
    val PASSWORD = getInput()
  }
}
