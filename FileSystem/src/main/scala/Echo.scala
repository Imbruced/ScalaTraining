class Echo(fileName: String, message: String) extends Command {
  override def apply(state: State): State = {
    val seekFile = state.wd.filterEntry(fileName)
    val updatedFile = seekFile.asInstanceOf[AbstractFile].write(message)
    val withRemovedFile = state.wd.removeDir(fileName)
    val withUpdatedFile = withRemovedFile.add(updatedFile)

    Directory.updateRoot(state, withUpdatedFile)
  }
}
