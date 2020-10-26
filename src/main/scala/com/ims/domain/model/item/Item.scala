package com.ims.domain.model.item

import com.ims.domain.model.Id

case class Description(paragraph1: String, paragraph2: String)
case class Price(amount: Double)

abstract class AbstractItem(val _id: Id, val name: String, val description: Description)

case class Item(override val _id: Id, override val name: String, override val description: Description,
                value: Price, quantity: Int) extends AbstractItem(_id, name, description)

case class FutureItem(override val _id: Id, override val name: String, override val description: Description)
  extends AbstractItem(_id, name, description)



