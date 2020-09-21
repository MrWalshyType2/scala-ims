package com.ims.crud

object CRUD extends Enumeration {

  type CRUD = Value

  val CREATE = Value("CREATE: Create and store an entity")
  val READALL = Value("READ ALL: Read all stored entities")
  val UPDATE = Value("UPDATE: Update a stored entity")
  val DELETE = Value("DELETE: Delete a stored entity")

  def getValues(): ValueSet = {
    CRUD.values
  }
}
