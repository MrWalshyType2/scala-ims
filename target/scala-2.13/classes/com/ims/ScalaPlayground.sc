import reactivemongo.api.bson.BSONHandler.provided
import reactivemongo.api.bson.{BSONArray, BSONDocument, BSONInteger, BSONObjectID, BSONString, document}

val myMap = Map[BSONString, Int](BSONString(BSONObjectID.generate().stringify) -> 4, BSONString(BSONObjectID.generate().stringify) -> 13)
myMap.foreach(mapping => println(mapping))
//val myMap2 = myMap + Map[BSONString, Int](BSONString(BSONObjectID.generate().stringify) -> 52)
//println(myMap2)
val myMap2 = myMap + (BSONString(BSONObjectID.generate().stringify) -> 52)
println(myMap2)


val bsonDoc = BSONDocument(
  "foo" -> "bar",
  "items" -> BSONDocument(
    BSONObjectID.generate().stringify -> 32,
    BSONObjectID.generate().stringify -> 365
  )
)

val updatedDoc = BSONDocument(
  "foo" -> "bar",
  "items" -> bsonDoc.getAsOpt[BSONDocument]("items").get
)

val itemDoc = bsonDoc.get("items")
itemDoc.foreach(item => println(item))

val items = bsonDoc.getAsOpt[BSONDocument]("items")

items.get.elements.foreach(element => {
  val name = element.name
  val value = BSONInteger.unapply(element.value)
  println(name)
  println(value.get)
  println(element)
})


val upItems = items.get ++ (BSONObjectID.generate().stringify -> 32)
upItems.elements.foreach(element => {
  println(element.name)
  println(BSONInteger.unapply(element.value).get)
})

val order = Order(BSONString(BSONObjectID.generate().stringify),
  BSONString(BSONObjectID.generate().stringify),
  BSONDocument(BSONObjectID.generate().stringify -> 1))

println(order.toString())