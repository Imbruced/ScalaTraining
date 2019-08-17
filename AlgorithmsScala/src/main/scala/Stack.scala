abstract class StackAbstract[T]{
  def isEmpty: Boolean = ???
  def size: Int = ???
  def push(element: T): StackAbstract[T] = ???
  def pop: StackAbstract[T] = ???
  def isFull: Boolean = ???
  def stackData: String = ???
  override def toString: String = s"(${this.stackData})"
}

class EmptyStack[T](limit: Int) extends StackAbstract[T]{
  override def isEmpty: Boolean = true
  override def size: Int = 0
  override def push(element: T): MainStack[T] = {
    new MainStack[T](List(element), limit)
  }
  override def pop = throw new IndexOutOfBoundsException()
  override def isFull: Boolean = false
  override def stackData: String = ""

}

class MainStack[T](val data: List[T], limit: Int) extends StackAbstract[T]{
  override def isEmpty: Boolean = false
  override def push(element: T): MainStack[T] ={
    if (!isFull){
      new MainStack[T](element :: this.data , this.limit)
    }
    else throw new StackOverflowError("Stack is overflow")
  }
  override def isFull: Boolean = {
    if (data.length >= limit){
      true
    }
    else false
  }
  override def pop: StackAbstract[T] = {
    if (data.length == 1){
      new EmptyStack[T](this.limit)
  }
    else new MainStack[T](this.data.dropRight(1), this.limit)
  }

  override def stackData: String = {
    this.data.mkString(", ")
  }
}


object Stack {

}