package com.ims

import java.util.Scanner
import java.util.logging.{Level, Logger}

object Ims {

  private val LOGGER = Logger.getLogger(Ims.getClass.getSimpleName)

  def init(): Unit = {
    LOGGER.info("USERNAME:")
    val USERNAME = getInput()

    LOGGER.info("PASSWORD:")
    val PASSWORD = getInput()

    if (init(USERNAME, PASSWORD)) {

    }
    else {
      LOGGER.log(Level.SEVERE, "Could not connect to DB")
      LOGGER.info("Try again?")

      if (getInput().toLowerCase() == "y") init()
      System.exit(1) // Something went wrong connecting to the db
    }
  }

  def init(username: String, password: String): Boolean = {
    // TO-DO - Implement db connection
    false
  }
}
