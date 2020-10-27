package com.ims.controller

import java.util.logging.Logger

import com.ims.Utility.getInput
import com.ims.domain.dao.{BaseDaoInterface, OrderDao}
import com.ims.domain.model.order.Order
import reactivemongo.api.bson.{BSONDocument, BSONElement, BSONInteger, BSONObjectID, BSONString}

import scala.:+
import scala.annotation.tailrec
import scala.concurrent.Future

object OrderController extends Controller[Order] {

  val dao = OrderDao

  override def create(order: Order): Future[Option[Order]] = dao.create(order)

  override def readById(id: BSONObjectID): Future[Option[Order]] = dao.readById(id)

  override def readAll(): Future[List[Order]] = dao.readAll()

  override def update(order: Order): Future[Option[Order]] = dao.update(order)

  override def delete(id: BSONObjectID): Unit = dao.delete(id)
}
