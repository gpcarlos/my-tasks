package exercises.functions

import org.scalatest._

class ByNameParameterSpec extends FlatSpec with Matchers {

  it should "Passing a function with Unit as argument" in {
    // Unit == void
    def calc(x: () => Int): Either[Throwable, Int] = {
      try {
        Right(x()) // An explicit call of the x function
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc { () => // Having explicitly declaring that Unit is a parameter with ()
      14 + 15
    }

    y should be(Right(29))
  }

  it should "Using by-name parameter" in {
    def calc(x: => Int): Either[Throwable, Int] = {
      // x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc {
      // This looks like a natural block
      println("Here we go!") // Some superfluous call
      val z = List(1, 2, 3, 4) // Another superfluous call
      49 + 20
    }

    y should be(Right(69))
  }

  it should "Using by-name parameter in objects" in {
    object PigLatinizer {
      def apply(x: => String) = x.tail + x.head + "ay"
    }

    val result = PigLatinizer {
      val x = "pret"
      val z = "zel"
      x ++ z //concatenate the strings
    }

    result should be("retzelpay")
  }

}
