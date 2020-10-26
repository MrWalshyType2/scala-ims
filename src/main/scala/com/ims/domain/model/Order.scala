package com.ims.domain.model

import reactivemongo.api.bson.{BSONDocument}

case class Order(_id: Id, customer_id: Id, orderItems: BSONDocument)
