package com.ims.domain

object Domain extends Enumeration {

  type Domain = Value

  val CUSTOMER = Value("CUSTOMER: To perform CRUD operations on Customers.")
  val ITEM = Value("ITEM: To perform CRUD operations on Items.")
  val ORDER = Value("ORDER: To perform CRUD operations on Orders.")
  val EXIT = Value("EXIT: To exit the application.")

  def getDomains(): ValueSet = {
    Domain.values
  }
}
