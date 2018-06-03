package exercises.collections

import org.scalatest._

class MapsSpec extends FlatSpec with Matchers {

  "Maps" should "have a size method" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap.size should be(4)
  }

  it should "not contain multiple identical pairs" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    myMap.size should be(3)
  }

  it should "can be added to easily" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    val aNewMap = myMap + ("IL" -> "Illinois")
    aNewMap.contains("IL") shouldBe true
  }

  it should "can be iterated" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")

    val mapValues = myMap.values
    mapValues.size should be(3)
    mapValues.head should be("Michigan") //Failed presumption: The order in maps is not guaranteed

    val lastElement = mapValues.last
    lastElement should be("Wisconsin") //Failed presumption: The order in maps is not guaranteed
  }

  it should "be accessed" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap("MI") should be("Michigan")
    myMap("IA") should be("Iowa")
  }

  it should "insertion with duplicate key updates previous entry with subsequent value" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Meechigan")
    val mapValues = myMap.values
    mapValues.size should be(3)
    myMap("MI") should be("Meechigan")
  }

  it should "have keys may be of mixed type" in {
    val myMap = Map("Ann Arbor" -> "MI", 49931 -> "MI")
    myMap("Ann Arbor") should be("MI")
    myMap(49931) should be("MI")
  }

  it should "return NoSuchElementException" in {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    intercept[NoSuchElementException] {
      myMap("TX")
    }

    myMap.getOrElse("TX", "missing data") should be("missing data")

    val myMap2 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa") withDefaultValue "missing data"
    myMap2("TX") should be("missing data")
  }

  it should "have elements can be removed easily" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MI"
    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)
  }

  it should "have elements can be removed in multiple" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap -- List("MI", "OH")

    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)

    aNewMap.contains("WI") should be(true)
    aNewMap.size should be(2)
    myMap.size should be(4)
  }

  it should "have elements can be removed with a tuple" in {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - ("MI", "WI") // Notice: single '-' operator for tuples

    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)
    aNewMap.contains("OH") should be(true)
    aNewMap.size should be(2)
    myMap.size should be(4)
  }

  it should "handle gracefully removal of nonexistent elements from a map" in {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MN"

    aNewMap.equals(myMap) should be(true)
  }

  it should "equivalency is independent of order" in {
    val myMap1 =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val myMap2 =
      Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")

    myMap1.equals(myMap2) should be(true)
  }

}
