class State(val root: Directory, val wd: Directory, val output: String) {
  def show: Unit = {
    print(output)
    print(State.SHELL_TOKEN)

  }
  def setMessage(message: String): State = {
    State(root, wd, message)
  }
}

object State {

  val SHELL_TOKEN = "$ "

  def apply(root: Directory, wd: Directory, output: String = Directory.SEPARATOR): State = new State(root, wd, output)

}

