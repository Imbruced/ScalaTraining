class MergeSort {
  def sort(array: List[Int]): List[Int] = {
    List(1, 2)
  }

  def mergeSort(array: List[Int]): List[Int] = {
    if (array.length <= 1){
      array
    }
    else{
      val n = array.length
      val mid = n/2
      merge(mergeSort(array.slice(0, mid)), mergeSort(array.slice(mid, n)))
    }

  }
  def merge(left: List[Int], right: List[Int]): List[Int]={
    if (right.isEmpty){
      left
    }
    else if (left.isEmpty){
     right
    }
    else{
      if (left.head < right.head) left.head :: merge(left.tail, right)
      else right.head :: merge(right.tail, left)
    }
  }

}

object MergeSort {
  def sort(array: List[Int]): List[Int] = {
    new MergeSort().mergeSort(array)
  }
}

