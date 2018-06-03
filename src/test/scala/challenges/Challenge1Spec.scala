package challenges

import org.scalatest._
import challenges.challenge1._

class Challenge1Spec extends FlatSpec with Matchers {

  case class Approach1(total: Int, addend: Int) {
    def comb: List[List[Int]] = combinations(total,addend)
  }

  "Approach1" should "be able to find the list of combinations" in {

    val approach1 = Approach1(10, 3)
    val approach2 = Approach1(24, 4)
    val approach3 = Approach1(11, 4)
    val approach4 = Approach1(15, 3)

    println(approach1.comb)

    approach1.comb shouldBe List(
      List(1, 2, 7),
      List(1, 3, 6),
      List(1, 4, 5),
      List(2, 3, 5))

    approach2.comb shouldBe List(
      List(1, 6, 8, 9),
      List(2, 5, 8, 9),
      List(2, 6, 7, 9),
      List(3, 4, 8, 9),
      List(3, 5, 7, 9),
      List(3, 6, 7, 8),
      List(4, 5, 6, 9),
      List(4, 5, 7, 8))

    approach3.comb shouldBe List(List(1, 2, 3, 5))

    approach4.comb shouldBe List(
      List(1, 5, 9),
      List(1, 6, 8),
      List(2, 4, 9),
      List(2, 5, 8),
      List(2, 6, 7),
      List(3, 4, 8),
      List(3, 5, 7),
      List(4, 5, 6))

  }
}