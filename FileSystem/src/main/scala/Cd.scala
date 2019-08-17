class Cd(path: String) extends Command{
  override def apply(state: State): State = {
    val paths = state.wd.split
    val splittedTarget = Directory.split(path)

    val pathsForward = addElementPaths(paths, splittedTarget)

    val newDirLocation = try{
      state.root.moveToDir(pathsForward)

    }
    catch {
      case e: java.lang.IndexOutOfBoundsException =>  {
        println(s"No Such file or directory $e")
      }
        state.wd }
    State.apply(state.root, newDirLocation, newDirLocation.path)
  }

  def addElementPaths(paths: List[String], elements: List[String]): List[String] = {
    def helperFunction(paths: List[String], elements: List[String], startingIndex: Int): List[String]={
      if (startingIndex >= elements.length){
        paths
      }
      else{
        val updatedPaths = addPathElement(paths, elements(startingIndex))
        helperFunction(updatedPaths, elements, startingIndex+1)
      }
    }
    helperFunction(paths, elements, 0)
  }

  def addPathElement(paths: List[String], element: String): List[String] = {
    if (element == ".."){
      if (paths.nonEmpty ) {
        val pathsRemoved = paths.dropRight(1)
        pathsRemoved
      }
      else paths
    }
    else if (element == "."){
      paths
    }
    else paths ++ List(element)
  }

}
