package exercises.functions

import org.scalatest._
import scala.language.postfixOps

class InfixPrefixandPostfixOperatorsSpec  extends FlatSpec with Matchers {

  it should "Any method which takes a single parameter can be used as an infix operator" in {
    val g: Int = 3
    (g + 4) should be(7) // + is an infix operator
    g.+(4) should be(7)  // same result but not using the infix operator
  }

  it should "Infix operators do NOT work if an object has a method that takes two parameters" in {
    val g: String = "Check out the big brains on Brad!"

    g indexOf 'o' should be(6) // indexOf(Char) can be used as an infix operator

    // g indexOf 'o', 4 should be (6) // indexOf(Char, Int) cannot be used as an infix operator

    g.indexOf('o', 7) should be(25) // indexOf(Char, Int) must use standard java/scala calls
  }

  it should "Any method which does not require a parameter can be used as a postfix operator" in  {
    val g: Int = 31
    (g toHexString) should be("1f") // toHexString takes no params therefore can be called as a postfix operator.
  }

  it should "Prefix operators work if an object has a method name that starts with `unary_`" in {
    val g: Int = 31
    (-g) should be(-31)
  }

  it should "Creating a prefix operator for our own class" in {
    class Stereo {
      def unary_+ = "on"

      def unary_- = "off"
    }

    val stereo = new Stereo
    (+stereo) should be("on")
    (-stereo) should be("off")
  }

}
