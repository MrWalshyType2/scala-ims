package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import com.ims.domain.dao.{BaseDaoInterface, ItemDao}
import com.ims.domain.model.item.Item
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.Future

object ItemController extends Controller[Item] {

  val dao = ItemDao

  override def create(item: Item): Future[Option[Item]] = dao.create(item)

  override def readById(id: BSONObjectID): Future[Option[Item]] = dao.readById(id)

  override def readAll(): Future[List[Item]] = dao.readAll()

  override def update(item: Item): Future[Option[Item]] = dao.update(item)

  override def delete(id: BSONObjectID): Unit = dao.delete(id)
}
