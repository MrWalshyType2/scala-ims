package com.ims.domain

import reactivemongo.api.bson.{BSONObjectID, BSONString}

case class Customer(_id: BSONString, forename: String, surname: String)
