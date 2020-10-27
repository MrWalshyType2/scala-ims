package com.ims.domain.model.item

import reactivemongo.api.bson.BSONObjectID

//case class Description(paragraph1: String, paragraph2: String)
//case class Price(amount: Double)

//abstract class AbstractItem(val _id: BSONObjectID, val name: String, val description: Description)

case class Item(val _id: BSONObjectID, val name: String, val description: String,
                value: Double, quantity: Int)

//case class FutureItem(override val _id: BSONObjectID, override val name: String, override val description: Description)
//  extends AbstractItem(_id, name, description)



