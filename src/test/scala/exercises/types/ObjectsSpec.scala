package exercises.types

import exercises.types.objects.{Greeting, Movie, Nerd}
import org.scalatest._

class ObjectsSpec extends FlatSpec with Matchers{

  "Objects" should "have all its instances as equivalents" in {

    val x = Greeting
    val y = x
    val z = Greeting

    // The eq method tests whether the first argument (x) is a reference to the second argument (y)

    x.eq(y) shouldBe true
    x.eq(z) shouldBe true

  }

  it should "have companion object as factory" in {

    Movie.academyAwardBestMoviesForYear(1932).get.name should be ("Grand Hotel")
    // DO NOT USE .get IN THE REAL LIFE :)
  }

  it should "have companion object which access to private values" in {

    val rafa = new Nerd("Rafa", "Superman")
    val paco = new Nerd("Paco", "Spider-Man")

    // rafa.superheroName will fail

    Nerd.showMeInnerSecret(rafa) shouldBe "Superman"
    Nerd.showMeInnerSecret(paco) shouldBe "Spider-Man"
  }

}
