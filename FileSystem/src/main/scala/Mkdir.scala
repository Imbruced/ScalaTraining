class Mkdir(name: String) extends Command {
  override def apply(state: State): State = {
    val newDirectory = Directory.empty(state.wd.path, name)
    val updatedWd = state.wd.add(newDirectory)

    if (state.wd.isRoot){
      State(
        updatedWd,
        updatedWd,
        updatedWd.path
      )
    }
    else {
      val updatedState = Directory.updateRoot(state, updatedWd)
      updatedState
    }

  }

}
