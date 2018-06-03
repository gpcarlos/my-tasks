package exercises.collections

import org.scalatest._

class SetsSpec extends FlatSpec with Matchers {

  "Sets" should "be created easily" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet.size should be(4)
  }

  it should "contain distinct values" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Michigan")
    mySet.size should be(3)
  }

  it should "be added to easily" in {
    val mySet   = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet + "Illinois"

    aNewSet.contains("Illinois") should be(true)
    mySet.contains("Illinois") should be(false)
  }

  it should "be of mixed type" in {
    val mySet = Set("Michigan", "Ohio", 12)

    mySet.contains(12) should be(true)
    mySet.contains("MI") should be(false)
  }

  it should "be checked for member existence" in {
    val mySet = Set("Michigan", "Ohio", 12)

    mySet(12) should be(true)
    mySet("MI") should be(false)
  }

  it should "have elements can be removed easily" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Michigan"

    aNewSet.contains("Michigan") should be(false)
    mySet.contains("Michigan") should be(true)
  }

  it should "have elements can be removed in multiple" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet -- List("Michigan", "Ohio")

    aNewSet.contains("Michigan") should be(false)
    aNewSet.contains("Wisconsin") should be(true)
    aNewSet.size should be(2)
  }

  it should "have elements can be removed with a tuple" in {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - ("Michigan", "Ohio") // Notice: single '-' operator for tuples

    aNewSet.contains("Michigan") should be(false)
    aNewSet.contains("Wisconsin") should be(true)
    aNewSet.size should be(2)
  }

  it should "attempted removal of nonexistent elements from a set is handled gracefully" in {
    val mySet   = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Minnesota"

    aNewSet.equals(mySet) should be(true)
  }

  it should "be intersected easily:" in {
    val mySet1  = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2  = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 intersect mySet2
    // NOTE: Scala 2.7 used **, deprecated for & or intersect in Scala 2.8

    aNewSet.equals(Set("Michigan", "Wisconsin")) should be(true)
  }

  it should "be joined as their union easily" in {
    val mySet1  = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2  = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 union mySet2 // NOTE: You can also use the "|" operator

    aNewSet.equals(Set("Michigan", "Wisconsin", "Ohio", "Iowa", "Minnesota")) should be(true)
  }

  it should "be either a subset of another set or it isn't" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val mySet3 = Set("Wisconsin", "Michigan")

    mySet2 subsetOf mySet1 should be(false)
    mySet3 subsetOf mySet1 should be(true)
  }

  it should "provide a way to obtain the difference between two sets" in {
    val mySet1  = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2  = Set("Wisconsin", "Michigan")
    val aNewSet = mySet1 diff mySet2 // Note: you can use the "&~" operator if you *really* want to.

    aNewSet.equals(Set("Ohio", "Iowa")) should be(true)
  }

  it should "have equals method, which is independent of order" in {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Ohio", "Iowa")

    mySet1.equals(mySet2) should be(true)
  }

}
