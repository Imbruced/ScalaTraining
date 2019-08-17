object SingletonApp extends App {
  trait Session{
    val id: String
  }

  private trait InternalSession extends Session {
    def postCreate(): Unit
    def preDestroy(): Unit
  }

  private class SessionImpl(val id: String) extends InternalSession{
    override def postCreate(): Unit = println("SessionImpl postCreate")
    override def preDestroy(): Unit = println("SessionImpl preDestroy")

  }

  var sValue: Option[String] = None

  if (sValue.isEmpty){
    sValue = Option("Element")
    println(sValue.get)
  }


}
