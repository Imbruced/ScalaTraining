class Rm(flag: String, name: String) extends Command{
  override def apply(state: State): State = {
    val newState = try{
      checkParams(state)
    }
    catch {case e: java.lang.IndexOutOfBoundsException => {
      println("Path does not exists")
      state
    }}

    newState
  }

  def checkParams(state: State): State = {
    val dirToRemove = state.wd.getDirEntry(name)
    if (flag == "-r" && ! state.wd.isFile){
      val updatedWd = state.wd.removeDir(dirToRemove.name)
      State.apply(state.root, updatedWd, updatedWd.path)
    }
    else {
      println("Nothing happened")
      state
    }
  }
}

