package challenges

import org.scalatest._
import challenges.challenge3._

class Challenge3Spec extends FlatSpec with Matchers {

  case class Spaces(total: Int, addends: Int, secure: Set[Int] = Set.empty) {
    def comb: List[List[Int]] = combinationsWithoutSecures(total, addends, secure)
  }

  case class Hand(a: Spaces, b: Spaces) {
    def solve: Int = {
      val intersection = for {
        combA <- a.comb
        combB <- b.comb
        inter <- combA.intersect(combB)
      } yield inter
      intersection.distinct match {
        case Nil => 0
        case head :: Nil => intersection.head
        case _ => -1
      }
    }
  }

  "Hand.solve" should "be able to find the solution when 2 compatible hands without secures are passed" in {

    val space1 = Spaces(13, 4, Set(1))
    val space2 = Spaces(24, 3)

    Hand(space1, space2).solve shouldBe 7
  }


  it should "be able to find the solution when 2 compatible hands with secures are passed" in {

    val space1 = Spaces(10, 2, Set(3))
    val space2 = Spaces(24, 3, Set(9))

    Hand(space1, space2).solve shouldBe 7
  }

  it should "be able to find the solution when 2 compatible hands with/without secures are passed" in {

    val space1 = Spaces(12, 4, Set(3))
    val space2 = Spaces(22, 3)
    val hand = Hand(space1, space2)

    hand.solve shouldBe 6
  }


  it should "return 0 when both spaces has no any possible intersection" in {

    val space1 = Spaces(8, 3)
    val space2 = Spaces(23, 3)
    val hand = Hand(space1, space2)

    hand.solve shouldBe 0
  }

  it should "return -1 when exist several possible solutions" in {

    val space1 = Spaces(12, 2)
    val space2 = Spaces(23, 4)
    val hand = Hand(space1, space2)

    hand.solve shouldBe -1
  }

  it should "be able to find the unique solution when secures are restrictive" in {

    val space1 = Spaces(19, 4, Set(5, 6))
    val space2 = Spaces(16, 4, Set(7))

    Hand(space1, space2).solve shouldBe 1
  }
}
