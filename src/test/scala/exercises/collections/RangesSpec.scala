package exercises.collections

import org.scalatest._

class RangesSpec extends FlatSpec with Matchers {

  "Ranges" should "upperNotInclusiveRangeExercises" in {
    val someNumbers = Range(0, 10)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(10)
    second should be(1)
    last should be(9)
  }

  it should "untilRangeExercises" in {
    val someNumbers = Range(0, 10)
    val otherRange = 0 until 10

    (someNumbers == otherRange) should be(true)
  }

  it should "incrementsRangeExercises" in {
    val someNumbers = Range(2, 10, 3)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(3)
    second should be(5)
    last should be(8)
  }

  it should "upperInIncrementRangeExercises" in {
    val someNumbers = Range(0, 34, 2)
    someNumbers.contains(33) should be(false)
    someNumbers.contains(32) should be(true)
    someNumbers.contains(34) should be(false)
  }

  it should "specifyUpperRangeExercises" in {
    val someNumbers = Range(0, 34).inclusive

    someNumbers.contains(34) should be(true)
  }

  it should "inclusiveWithToRangeExercises" in {
    val someNumbers = Range(0, 34).inclusive
    val otherRange = 0 to 34

    (someNumbers == otherRange) should be(true)
  }
}