case class DirEntry(parentPath: String, name: String) {
  def isFile: Boolean = false
  def contents: List[DirEntry] = ???
}
