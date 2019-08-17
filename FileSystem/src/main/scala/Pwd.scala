class Pwd extends Command{
  override def apply(state: State): State = {
    println(state.wd.path)
    state
  }
}
