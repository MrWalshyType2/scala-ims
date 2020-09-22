package com.ims.dao

import java.util.UUID

trait DAO[T] {

  def create(t: T)
  def readAll(id: UUID)
  def update(t: T)
  def delete(id: UUID)
}
