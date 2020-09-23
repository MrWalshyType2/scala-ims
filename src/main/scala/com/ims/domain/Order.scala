package com.ims.domain

import reactivemongo.api.bson.{BSONDocument, BSONString}

case class Order(_id: BSONString, customer_id: BSONString, orderItems: BSONDocument)
