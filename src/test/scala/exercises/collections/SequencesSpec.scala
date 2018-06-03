package exercises.collections

import org.scalatest._

class SequencesSpec extends FlatSpec with Matchers {

  "Sequences and arrays" should "sequentialCollectionSequencesandArrays" in {
    val l = List(1, 2, 3)
    val a = l.toArray
    a should equal(Array(1, 2, 3))
  }

  it should "orderedElementsSequencesandArrays" in {
    val a = Array(1, 2, 3)
    val s = a.toSeq
    val l = s.toList
    l should equal(List(1, 2, 3))
  }

  it should "fromForComprehensionSequencesandArrays" in {
    val s = for (v <- 1 to 4) yield v
    s.toList should be(List(1, 2, 3, 4))
  }

  it should "withConditionSequencesandArrays" in {
    val s = for (v <- 1 to 10 if v % 3 == 0) yield v
    s.toList should be(List(3, 6, 9))
  }

  it should "filterPredicateSequencesandArrays" in {
    val s = Seq("hello", "to", "you")
    val filtered = s.filter(_.length > 2)
    filtered should be(Seq("hello", "you"))
  }

  it should "filterArraySequencesandArrays" in {
    val a = Array("hello", "to", "you", "again")
    val filtered = a.filter(_.length > 3)
    filtered should be(Array("hello", "again"))
  }

  it should "mapValuesSequencesandArrays" in {
    val s = Seq("hello", "world")
    val r = s map {_.reverse}

    r should be(Seq("olleh", "dlrow"))
  }

}
