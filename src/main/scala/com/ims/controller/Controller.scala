package com.ims.controller

import com.ims.domain.dao.BaseDaoInterface
import reactivemongo.api.bson.BSONObjectID

import scala.concurrent.Future

trait Controller[T] {
  protected def dao: BaseDaoInterface[T]

  def create(t: T): Future[Option[T]]
  def readById(id: BSONObjectID): Future[Option[T]]
  def readAll(): Future[List[T]]
  def update(t: T): Future[Option[T]]
  def delete(id: BSONObjectID)
}
