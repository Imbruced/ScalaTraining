object QuickSortFP {

  def quickSort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        quickSort(xs filter (pivot >)),
        xs filter (pivot ==),
        quickSort(xs filter (pivot <)))
    }
  }

}