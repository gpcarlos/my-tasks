package exercises.functions

import org.scalatest._

class RepeatedParametersSpec extends FlatSpec with Matchers {

  def repeatedParameterMethod(x: Int, y: String, z: Any*): String =
    "%d %ss can give you %s".format(x, y, z.mkString(", "))

  it should "Repeating the last parameter" in {
    repeatedParameterMethod(3, "egg", "a delicious sandwich", "protein", "high cholesterol") should be(
      "3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }

  it should "Using a collection for the last parameter as a single object" in {
    repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol")) should be(
      "3 eggs can give you List(a delicious sandwich, protein, high cholesterol)")
  }

  it should "Expanding a collection for the last parameter" in {
    repeatedParameterMethod(
      3,
      "egg",
      List("a delicious sandwich", "protein", "high cholesterol"): _*) should be(
      "3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }


}
