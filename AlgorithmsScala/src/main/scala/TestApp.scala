object TestApp extends App {
  // create an array of random 10m random ints
  val r = scala.util.Random
  val randomArray = Array(10, 20, 56, 45, 32, 67, 12, 34, 7, 8)

  // do the sorting
  val sortedArray = QuickSortFP.quickSort(randomArray)
  sortedArray.foreach(x => print(x + ","))
  println()
  println("-"*30)
  println(BinarySearch.search(sortedArray, 45))

  println("-"*20)
  println(randomArray.toList)
  println(MergeSort.sort(randomArray.toList))
  println(Bfs.travel(0))

  val emptyStack = new EmptyStack[Int](5)
  println(emptyStack.
    push(10).
    push(20).
    push(30).
    push(100).
    push(100).
    pop.
    pop.
    pop.
    pop.
    pop
  )


}
