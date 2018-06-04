package exercises.statements

import org.scalatest._
import scala.language.implicitConversions

class ImplicitsSpec extends FlatSpec with Matchers {

  // We can't have more than one implicit with the same type

  it should "Implicit conversion within the scope" in {
    class KoanIntWrapper(val original: Int) {
      def isOdd = original % 2 != 0
    }

    // implicits are identified by the type
    implicit def thisMethodNameIsIrrelevant(value: Int) =
      new KoanIntWrapper(value)

    // Now all Int have the isOdd method
    19.isOdd should be(true)
    20.isOdd should be(false)
  }

  it should "Implicit conversion by importing into the scope" in {
    object MyPredef {

      class KoanIntWrapper(val original: Int) {
        def isOdd = original % 2 != 0

        def isEven = !isOdd
      }

      implicit def thisMethodNameIsIrrelevant(value: Int) =
        new KoanIntWrapper(value)
    }

    import MyPredef._ // imported implicits come into effect within this scope
    19.isOdd should be(true)
    20.isOdd should be(false)
  }

  it should "Implicit conversion of types" in {
    import java.math.BigInteger
    implicit def Int2BigIntegerConvert(value: Int): BigInteger =
      new BigInteger(value.toString)

    def add(a: BigInteger, b: BigInteger) = a.add(b)

    add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9) should be(true)

    add(3, 6) == 9 should be(false)
    add(3, 6) == Int2BigIntegerConvert(9) should be(true)

    add(3, 6) == (9: BigInteger) should be(true)
    add(3, 6).intValue == 9 should be(true)
  }

  it should "Declaring default implicit value" in {
    def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) =
      dollarsPerHour * hours

    implicit val hourlyRate: BigDecimal = BigDecimal(34)

    howMuchCanIMake_?(30) should be(1020)
  }

  it should "Declaring default implicit values" in {
    def howMuchCanIMake_?(hours: Int)(implicit amount: BigDecimal, currencyName: String) =
      (amount * hours).toString() + " " + currencyName

    implicit val hourlyRate: BigDecimal = BigDecimal(34)
    implicit val currencyName: String   = "Dollars"

    howMuchCanIMake_?(30) should be("1020 Dollars")
  }

  it should "Declaring default arguments" in  {
    def howMuchCanIMake_?(hours: Int, amount: BigDecimal = 34, currencyName: String = "Dollars") =
      (amount * hours).toString() + " " + currencyName

    howMuchCanIMake_?(30) should be("1020 Dollars")

    howMuchCanIMake_?(30, 95) should be("2850 Dollars")
  }

}
