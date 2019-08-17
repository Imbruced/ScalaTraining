class Ls extends Command {
  override def apply(state: State): State = {
    printDirsRecursively(state.wd)
    state.setMessage(state.wd.path)
  }

  def printDirsRecursively(directory: Directory): Integer = {
    def helperFunction(values: List[DirEntry], starting_index: Integer): Unit = {
      if (starting_index < values.length){
        println(values(starting_index).name)
        helperFunction(values, starting_index + 1)
      }
    }
    if (!directory.isEmpty){
      helperFunction(directory.contents, 0)
    }
    else println("Directory is empty")

    1
  }
}
