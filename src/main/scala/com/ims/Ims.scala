package com.ims

import java.util.Scanner
import java.util.logging.{Level, Logger}

import com.ims.domain.Domain

object Ims {

  private val LOGGER = Logger.getLogger(Ims.getClass.getSimpleName)
  private var USERNAME: String = ""
  private var PASSWORD: String = ""

  def init(): Unit = {
    LOGGER.info("USERNAME:")
    USERNAME = getInput()

    LOGGER.info("PASSWORD:")
    PASSWORD = getInput()

    if (init(USERNAME, PASSWORD)) menu()
    else {
      LOGGER.log(Level.SEVERE, "Could not connect to DB")
      LOGGER.info("Try again?")

      if (getInput().toLowerCase() == "y") init()
      System.exit(1) // Something went wrong connecting to the db
    }
  }

  def init(username: String, password: String): Boolean = {
    // TO-DO - Implement db connection
    true
  }

  def menu(): Unit = {
    Domain.getDomains().foreach(domain => println(domain))
  }
}
