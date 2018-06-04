package exercises.statements

import org.scalatest._

class PatternMatchingSpec extends FlatSpec with Matchers {

  it should "Basic example" in {
    val stuff = "blue"

    val myStuff = stuff match {
      case "red"    => println("RED"); 1 // The last is the return
      case "blue"   => println("BLUE"); 2
      case "green"  => println("GREEN"); 3
      case _        => println(stuff); 0
    }

    myStuff shouldBe 2
  }

  it should "Basic example matching complex values" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", "Papa") => "Papa eating porridge"
      case ("porridge", "Mama") => "Mama eating porridge"
      case ("porridge", "Baby") => "Baby eating porridge"
      case _                    => "what?"
    }

    goldilocks(("porridge", "Mama")) should be("Mama eating porridge")
  }

  it should "Basic example with wildcards" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", _)   => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby")   => "sleeping"
      case _                 => "what?"
    }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
  }

  it should "Basic example with substitution" in {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", bear) =>
        bear + " said someone's been eating my porridge"
      case ("chair", bear) => bear + " said someone's been sitting in my chair"
      case ("bed", bear)   => bear + " said someone's been sleeping in my bed"
      case _               => "what?"
    }

    goldilocks(("porridge", "Papa")) should be("Papa said someone's been eating my porridge")
    goldilocks(("chair", "Mama")) should be("Mama said someone's been sitting in my chair")
  }

  it should "Basic example with backquote" in {
    val foodItem = "porridge"

    def goldilocks(expr: Any) = expr match {
      case (`foodItem`, _)   => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby")   => "sleeping"
      case _                 => "what?"
    }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
    goldilocks(("porridge", "Cousin")) should be("eating")
    goldilocks(("beer", "Cousin")) should be("what?")
  }

  it should "Basic example with backquote in method parameter" in {
    def patternEquals(i: Int, j: Int) = j match {
      case `i` => true
      case _   => false
    }

    patternEquals(3, 3) should be(true)
    patternEquals(7, 9) should be(false)
    patternEquals(9, 9) should be(true)
  }

  it should "List example" in {
    val secondElement = List(1, 2, 3) match {
      case x :: xs => xs.head
      case _       => 0
    }

    secondElement should be(2)
  }

  it should "List example for extracting the second element" in {
    val secondElement = List(1, 2, 3) match {
      case x :: y :: xs => y
      case _            => 0
    }

    secondElement should be(2)
  }

  it should "List example for extracting the second element from a shorter list" in {
    val secondElement = List(1) match {
      case x :: y :: xs => y // only matches a list with two or more items
      case _            => 0
    }

    secondElement should be(0)
  }

  it should "List example to check size" in {
    val r = List(1, 2, 3) match {
      case x :: y :: Nil => y // only matches a list with exactly two items
      case _             => 0
    }

    r should be(0)
  }

  it should "List example extracting Nil" in {
    val r = List(1, 2, 3) match {
      case x :: y :: z :: tail => tail
      case _                   => 0
    }

    r == Nil should be(true)
  }

}
