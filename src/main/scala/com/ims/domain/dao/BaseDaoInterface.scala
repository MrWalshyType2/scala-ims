package com.ims.domain.dao

import reactivemongo.api.bson.BSONObjectID

import scala.concurrent.Future
import scala.util.Try

trait BaseDaoInterface[T] {
  def create(t: T): Future[Option[T]]
  def readById(id: BSONObjectID): Future[Option[T]]
  def readAll(): Future[List[T]]
  def update(t: T): Future[Option[T]]
  def delete(id: BSONObjectID)
}
