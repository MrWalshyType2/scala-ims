package com.ims.domain.dao

import com.ims.domain.model.Id

import scala.concurrent.Future
import scala.util.Try

trait BaseDaoInterface[T] {
  def create(t: T): Future[Option[T]]
  def readByName(name: String): Future[Option[T]]
  def readById(id: Id): Future[Option[T]]
  def readAll(): Future[Seq[T]]
  def update(t: T): Future[Option[T]]
  def delete(id: Id)
}
