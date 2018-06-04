package challenges

import org.scalatest._
import challenges.challenge2._

class Challenge2Spec extends FlatSpec with Matchers {

  case class Approach2(total: Int, addends: Int, secure: Set[Int] = Set.empty) {
    def comb: List[List[Int]] = combinations(total, addends, secure)
  }

  "Approach2" should "be able to find the list of combinations" in {

    val approach1 = Approach2(10, 3, Set(2))
    val approach2 = Approach2(24, 4, Set(3, 8))
    val approach3 = Approach2(11, 4)
    val approach4 = Approach2(15, 3, Set(7, 2))

    approach1.comb shouldBe List(
      List(1, 2, 7),
      List(2, 3, 5))

    approach2.comb shouldBe List(
      List(3, 4, 8, 9),
      List(3, 6, 7, 8))

    approach3.comb shouldBe List(List(1, 2, 3, 5))

    approach4.comb shouldBe List(List(2, 6, 7))

  }

}
