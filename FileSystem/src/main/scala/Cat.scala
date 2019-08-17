class Cat(fileName: String) extends Command{
  override def apply(state: State): State = {
    val newState = try{
      val seekFile = state.wd.filterEntry(fileName)
      println(seekFile.asInstanceOf[AbstractFile].read)
      state
    }
    catch {case e: java.lang.IndexOutOfBoundsException => {
      println("File does not exists")
      state
    }}

    newState
  }

}
