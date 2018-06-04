package challenges

object challenge2 {

  def combinations (total: Int, addend: Int, secure: Set[Int] = Set.empty): List[List[Int]] =
    (1 to 9 toList).combinations(addend).filter(v => v.sum == total && secure.subsetOf(v.toSet)).toList

}
