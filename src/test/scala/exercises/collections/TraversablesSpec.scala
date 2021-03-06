package exercises.collections

import org.scalatest._
import scala.collection.immutable.Stream.cons

class TraversablesSpec extends FlatSpec with Matchers {

  // Traversables are the superclass of List, Array, Map, Set...

  "Traversables" should "topOfCollectionTraversables" in {
    val set = Set(1, 9, 10, 22)
    val list = List(3, 4, 5, 10)
    val result = set ++ list
    result.size should be(7)

    val result2 = list ++ set
    result2.size should be(8)

    // The resulting Traversable is the same type of the first element
  }

  it should "mapFunctionTraversables" in {
    val set = Set(1, 3, 4, 6)
    val result = set.map(_ * 4)
    result.lastOption should be(Option(24))
  }

  it should "flattenFunctionTraversables" in {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    list.flatten should be(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  }

  it should "flatMapFunctionTraversables" in {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    val result = list.flatMap(_.map(_ * 4))
    result should be(List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40))
  }

  it should "flatMapOfOptionsTraversables" in {
    val list = List(1, 2, 3, 4, 5)
    val result = list.flatMap(it => if (it % 2 == 0) Some(it) else None)
    result should be(List(2, 4))
  }

  it should "collectFunctionTraversables" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.collect {
      case x: Int if (x % 2 == 0) => x * 3
    }
    result should be(List(12, 18, 24, 42))
  }

  it should "collectFunctionIITraversables" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val partialFunction1: PartialFunction[Int, Int] = {
      case x: Int if x % 2 == 0 ⇒ x * 3
    }
    val partialFunction2: PartialFunction[Int, Int] = {
      case y: Int if y % 2 != 0 ⇒ y * 4
    }
    val result = list.collect(partialFunction1 orElse partialFunction2)
    result should be(List(12, 18, 28, 24, 36, 52, 42))
  }

  it should "foreachFunctionTraversables" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    list.foreach(num ⇒ println(num * 4))
    list should be(List(4, 6, 7, 8, 9, 13, 14))
  }

  it should "toArrayFunctionTraversables" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toArray
    result.isInstanceOf[Array[Int]] should be(true)
  }

  it should "toListFunctionTraversables" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toList

    result.isInstanceOf[List[_]] should be(true)
  }

  it should "toListFunctionIITraversables" in {
    val list = List(5, 6, 7, 8, 9)
    val result = list.toList
    result eq list should be(true)
  }

  it should "toIterableFunctionTraversables" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIterable
    result.isInstanceOf[Iterable[_]] should be(true)
  }

  it should "toSeqFunctionTraversables" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toSeq
    result.isInstanceOf[Seq[_]] should be(true)
  }

  it should "toIndexedSeqFunctionTraversables" in {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIndexedSeq // Vector
    result.isInstanceOf[IndexedSeq[_]] should be(true)
  }

  it should "toStreamFunctionTraversables" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.toStream
    result.isInstanceOf[Stream[_]] should be(true)
    (result take 3) should be(List(4, 6, 7))
  }

  it should "toSetFunctionTraversables" in {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.toSet
    result.isInstanceOf[Set[_]] should be(true)
  }

  it should "toMapFunctionTraversables" in {
    val list = List("Phoenix" -> "Arizona", "Austin" -> "Texas")
    val result = list.toMap
    result.isInstanceOf[Map[_, _]] should be(true)
  }

  it should "toMapFunctionIITraversables" in {
    val set = Set("Phoenix" -> "Arizona", "Austin" -> "Texas")
    val result = set.toMap
    result.isInstanceOf[Map[_, _]] should be(true)
  }

  it should "isEmptyFunctionTraversables" in {
    val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
    map.isEmpty should be(false)

    val set = Set()
    set.isEmpty should be(true)
  }

  it should "nonEmptyFunctionTraversables" in {
    val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
    map.nonEmpty should be(true)

    val set = Set()
    set.nonEmpty should be(false)
  }

  it should "sizeFunctionTraversables" in {
    val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
    map.size should be(2)
  }

  it should "hasDefiniteSizeFunctionTraversables" in {
    val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
    map.hasDefiniteSize should be(true)

    val stream = cons(0, cons(1, Stream.empty))
    stream.hasDefiniteSize should be(false)
  }

  it should "headFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.head should be(10)
  }

  it should "headOptionFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.headOption should be(Some(10))

    val list2 = List()
    list2.headOption should be(None)
  }

  it should "lastFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.last should be(22)
  }

  it should "lastOptionFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.lastOption should be(Some(22))

    val list2 = List()
    list2.lastOption should be(None)
  }

  it should "findFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.find(_ % 2 != 0) should be(Some(19))

    val list2 = List(4, 8, 16)
    list2.find(_ % 2 != 0) should be(None)
  }

  it should "tailFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.tail should be(List(19, 45, 1, 22))
  }

  it should "initFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.init should be(List(10, 19, 45, 1))
  }

  it should "sliceFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.slice(1, 3) should be(List(19, 45))
  }

  it should "takeFunctionTraversables" in {
    val list = List(10, 19, 45, 1, 22)
    list.take(3) should be(List(10, 19, 45))
  }

  it should "takeFunctionIITraversables" in {
    def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))

    val a = streamer(2)
    (a take 3 toList) should be(List(2, 3, 4))
  }

  it should "dropFunctionTraversables" in {
    def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))

    val a = streamer(2)

    ((a drop 6) take 3).toList should be(List(8, 9, 10))
  }

  it should "takeWhileFunctionTraversables" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    list.takeWhile(_ < 100) should be(List(87, 44, 5, 4))
  }

  it should "dropWhileFunctionTraversables" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    list.dropWhile(_ < 100) should be(List(200, 10, 39, 100))
  }

  it should "filterFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    array.filter(_ < 100) should be(Array(87, 44, 5, 4, 10, 39))
  }

  it should "filterNotFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    array.filterNot(_ < 100) should be(Array(200, 100))
  }

  it should "splitAtFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array splitAt 3
    result._1 should be(Array(87, 44, 5))
    result._2 should be(Array(4, 200, 10, 39, 100))
  }

  it should "spanFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array span (_ < 100)
    result._1 should be(Array(87, 44, 5, 4))
    result._2 should be(Array(200, 10, 39, 100))
  }

  it should "partitionFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    val result = array partition (_ < 100)
    result._1 should be(Array(87, 44, 5, 4, 10, 39))
    result._2 should be(Array(200, 100))
  }

  it should "groupByFunctionTraversables" in {
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)

    val oddAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x % 2 != 0 && x < 100 ⇒ "Odd and less than 100"
    }

    val evenAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x != 0 && x % 2 == 0 && x < 100 ⇒ "Even and less than 100"
    }

    val negativePartial: PartialFunction[Int, String] = {
      case x: Int if x < 0 ⇒ "Negative Number"
    }

    val largePartial: PartialFunction[Int, String] = {
      case x: Int if x > 99 ⇒ "Large Number"
    }

    val zeroPartial: PartialFunction[Int, String] = {
      case x: Int if x == 0 ⇒ "Zero"
    }

    val result = array groupBy {
      oddAndSmallPartial orElse
        evenAndSmallPartial orElse
        negativePartial orElse
        largePartial orElse
        zeroPartial
    }

    (result("Even and less than 100") size) should be(3)
    (result("Large Number") size) should be(2)
  }

  it should "forallFunctionTraversables" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list forall (_ < 100)
    result should be(false)
  }

  it should "existsFunctionTraversables" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list exists (_ < 100)
    result should be(true)
  }

  it should "countFunctionTraversables" in {
    val list = List(87, 44, 5, 4, 200, 10, 39, 100)
    val result = list count (_ < 100)
    result should be(6)
  }

  it should "foldLeftFunctionTraversables" in {
    val list = List(5, 4, 3, 2, 1)
    val result = (0 /: list) { (`running total`, `next element`) ⇒
      `running total` - `next element`
    }
    result should be(-15)

    val result2 = list.foldLeft(0) { (`running total`, `next element`) ⇒
      `running total` - `next element`
    }
    result2 should be(-15)

    val result3 = (0 /: list) (_ - _) //Short hand
    result3 should be(-15)

    val result4 = list.foldLeft(0)(_ - _)
    result4 should be(-15)

    (((((0 - 5) - 4) - 3) - 2) - 1) should be(-15)
  }

  it should "foldRightFunctionTraversables" in {
    val list = List(5, 4, 3, 2, 1)
    val result = (list :\ 0) { (`next element`, `running total`) ⇒
      `next element` - `running total`
    }
    result should be(3)

    val result2 = list.foldRight(0) { (`next element`, `running total`) ⇒
      `next element` - `running total`
    }
    result2 should be(3)

    val result3 = (list :\ 0) (_ - _) //Short hand
    result3 should be(3)

    val result4 = list.foldRight(0)(_ - _)
    result4 should be(3)

    (5 - (4 - (3 - (2 - (1 - 0))))) should be(3)
  }


  it should "reduceLeftFunctionTraversables" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceLeft {_ + _} should be(15)

    val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    stringList.reduceLeft {
      _ + _
    } should be("DoReMeFaSoLaTeDo")
  }

  it should "reduceRightFunctionTraversables" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceRight {_ + _} should be(15)

    val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
    stringList.reduceRight {
      _ + _
    } should be("DoReMeFaSoLaTeDo")
  }

  it should "sumFunctionTraversables" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.sum should be(15)
    intList.product should be(120)
    intList.max should be(5)
    intList.min should be(1)
  }

  it should "reduceRightAsReduceLeft" in {
    val intList = List(5, 4, 3, 2, 1)
    intList.reduceRight((x, y) => x - y) should be(3)
    intList.reverse.reduceLeft((x, y) => y - x) should be(3)
    intList.reverse.reduce((x, y) => y - x) should be(3)
  }

  it should "transposeFunctionTraversables" in {
    val list = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    list.transpose should be(List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9)))

    val list2 = List(List(1), List(4))
    list2.transpose should be(List(List(1, 4)))
  }

  it should "mkStringFunctionTraversables" in {
    val list = List(1, 2, 3, 4, 5)
    list.mkString(",") should be("1,2,3,4,5")
  }

  it should "mkStringFunctionIITraversables" in {
    val list = List(1, 2, 3, 4, 5)
    list.mkString(">", ",", "<") should be(">1,2,3,4,5<")
  }

  it should "addStringFunctionTraversables" in {
    val stringBuilder = new StringBuilder()
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    stringBuilder.append("I want all numbers 6-12: ")
    list.filter(it ⇒ it > 5 && it < 13).addString(stringBuilder, ",")
    stringBuilder.mkString should be("I want all numbers 6-12: 6,7,8,9,10,11,12")
  }

}
