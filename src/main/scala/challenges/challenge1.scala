package challenges

object challenge1 {

  def combinations (total: Int, addend: Int): List[List[Int]] = {
    (1 to 9 toList).combinations(addend).filter(_.sum == total).toList
  }

}
