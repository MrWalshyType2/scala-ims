package com.ims.domain.model

import reactivemongo.api.bson.{BSONDocument, BSONObjectID}

case class Order(_id: BSONObjectID, customer_id: BSONObjectID, orderItems: BSONDocument)
