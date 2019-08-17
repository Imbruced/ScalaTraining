class Touch (fileName: String) extends Command {
  override def apply(state: State): State = {
    val extensionSplitted = fileName.split("\\.")
    val extension = try{
      extensionSplitted(1)
    } catch {case e: java.lang.ArrayIndexOutOfBoundsException =>
      println("Not passed extension, assuming that is txt file")
      ""}


    val updatedWd = state.wd.add(new EmptyFile(state.wd.parentPath, extensionSplitted(0), extension))
    val newState = Directory.updateRoot(state, updatedWd)
    newState
  }

}
