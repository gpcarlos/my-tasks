package challenges

object challenge1 {

  def combinations (total: Int, addend: Int): List[List[Int]] = {
    val b = (1 to 9 toList).combinations(addend)
    b.filter { v => v.sum == total } toList
  }

}
