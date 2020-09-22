package com.ims.domain

import reactivemongo.api.bson.BSONString

case class Item(_id: BSONString, name: String, value: Int) {

  override def toString: String = s"\nID: ${_id}\nNAME: ${name}\nVALUE: £${value/100}"
}
