class BinarySearch {

  def search(array: Array[Int], left: Int, right: Int, x: Int): Int ={
    if (left >= right-1) -1
    else{
      val mediumIndex = (right+left)/2
      if (x == array(mediumIndex)){
        mediumIndex
      }
      else if (x > array(mediumIndex)){
        search(array, mediumIndex, right, x)
      }
      else {
        search(array, left, mediumIndex, x)
      }

    }
  }
}

object BinarySearch {

  def search(array: Array[Int], value: Int): Int = {
    val searchEngine = new BinarySearch()

    searchEngine.search(array, 0, array.length, value)
  }
}
