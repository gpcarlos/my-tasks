package challenges

object challenge3 {

  def combinationsWithoutSecures (total: Int, addend: Int, secure: Set[Int] = Set.empty): List[List[Int]] =
    (1 to 9 toList).combinations(addend).filter(v => v.sum == total && secure.subsetOf(v.toSet)).map(_.diff(secure.toList)).toList

}