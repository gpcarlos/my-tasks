package exercises.statements

import org.scalatest._

class ForExpressionsSpec extends FlatSpec with Matchers {

  it should "nest, with later generators varying more rapidly than earlier ones" in {
    val xValues = 1 to 4
    val yValues = 1 to 2
    val coordinates = for {
      x <- xValues
      y <- yValues
    } yield (x, y)
    // (1,1), (1,2), (2,1), (2,2), (3,1), (3,2), (4,1), (4,2)

    coordinates(4) should be ((3, 1))
  }

  it should "make more readable code" in {
    val nums = List(List(1), List(2), List(3), List(4), List(5))

    val result = for {
      numList <- nums // Extract the content of the principal List
      num <- numList // Extract the numers of the Lists
      if num % 2 == 0 // Filter
    } yield num
    // Will yield the same type (List)

    result should be (List(2, 4))

    // Which is the same as
    nums.flatMap(numList => numList).filter(_ % 2 == 0) shouldBe result

    // or the same as
    nums.flatten.filter(_ % 2 == 0) shouldBe result
  }



}
