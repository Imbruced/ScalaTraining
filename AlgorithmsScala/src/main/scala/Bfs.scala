class Bfs {
  def travel(graph: Array[Array[Boolean]], startingNode: Int): Unit = {

    val visited = 0.to(graph.length).toArray.map(x => false)
    visited.update(startingNode, true)
    goGraphHelper(graph, visited, startingNode, 0)
  }


  def goGraphHelper(graph: Array[Array[Boolean]],
                    visited: Array[Boolean],
                    node: Int, startIndex: Int
                   ): (Int, Array[Boolean]) = {
    if (startIndex <= graph.length-1) {
        if (graph(node)(startIndex) && !visited(startIndex)){
          println(startIndex)
          visited.update(startIndex, true)

          goGraphHelper(graph, visited, startIndex, 0)
        }
        else {
          return goGraphHelper(graph, visited, node, startIndex+1)}
    }
    else {
      Tuple2(node, visited)
    }
  }
}

object Bfs {
  val graphArray = Array(
    Array(false, true, false, true, true, false, false),
    Array(true, false, true, false, true, false, false),
    Array(false, true, false, false, false, false, true),
    Array(true, false, false, false, true, false, true),
    Array(true, true, false, true, false, true, false),
    Array(false, false, false, false, true, false, false),
    Array(false, false, true, true, false, false, false)

  )

  val bfsGather = new Bfs()

  def travel(startPoint: Int): Unit = {
    println(s"Starting from $startPoint")
    bfsGather.travel(graphArray, startPoint)
  }
}