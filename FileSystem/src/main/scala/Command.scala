trait Command {
  def apply(state: State): State

}

object Command {

  val MKDIR = "mkdir"
  val LS = "ls"
  val CD = "cd"
  val RM = "rm"
  val PWD = "pwd"
  val TOUCH = "touch"
  val ECHO = "echo"
  val CAT = "cat"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }
  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(name + " incomplete command")
  }
  def wrongCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(name + " wrong command")
  }
  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if (tokens.isEmpty) emptyCommand
    else if (MKDIR.equals(tokens(0))) {
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else if (tokens.length == 2) new Mkdir(tokens(1))
      else wrongCommand(MKDIR)
    }
    else if (LS.equals(tokens(0))){
      if (tokens.length == 1){
        new Ls
      }
      else incompleteCommand(LS)
    }
    else if (CD.equals(tokens(0))){
      if (tokens.length == 2 ){
        new Cd(tokens(1))
      }
      else incompleteCommand(LS)
    }
    else if (RM.equals(tokens(0))){
      if (tokens.length == 3 ){
        new Rm(tokens(1), tokens(2))
      }
      else incompleteCommand(RM)
    }
    else if (PWD.equals(tokens(0))){
      if (tokens.length == 1 ){
        new Pwd()
      }
      else incompleteCommand(PWD)
    }

    else if (TOUCH.equals(tokens(0))){
      if (tokens.length == 2 ){
        new Touch(tokens(1))
      }
      else incompleteCommand(TOUCH)
    }
    else if (ECHO.equals(tokens(0))){
      val echoTokens = input.split(ECHO)(1)
      val fileName = echoTokens.split(">>")(1).trim
      val message = echoTokens.split(">>")(0).trim
        new Echo(fileName, message)
    }

    else if (CAT.equals(tokens(0))){
      if (tokens.length == 2 ){
        new Cat(tokens(1))
      }
      else incompleteCommand(CAT)
    }
    else new UnknownCommand
  }
}
