package com.ims.dao

import java.util.logging.Logger

import com.ims.DBConnection.{itemCollection, itemWriter}
import com.ims.domain.Item

import scala.concurrent.ExecutionContext.Implicits.global

object ItemDAO extends DAO[Item] {

  private val LOGGER = Logger.getLogger(ItemDAO.getClass.getSimpleName)

  override def create(item: Item): Unit = {
    itemCollection.flatMap(_.insert.one(item).map(_ => { }))
  }

  override def readAll(): Unit = ???

  override def update(t: Item): Unit = ???

  override def delete(id: String): Unit = ???
}
