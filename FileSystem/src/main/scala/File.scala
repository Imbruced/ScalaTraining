abstract class AbstractFile(parentPath: String, name: String, extension: String) extends DirEntry(parentPath, name) {
  def read: String
  def write(message: String): AbstractFile
  def isEmpty: Boolean = true

}

class EmptyFile(parentPath: String, name: String, extension: String) extends AbstractFile(parentPath: String, name: String, extension: String){
  def read: String = ""
  def write(message: String): AbstractFile = {
    new File(parentPath, name, extension, message)
  }
  override def isEmpty: Boolean = true
}

class File(parentPath: String, name: String, extension: String, contest: String = "") extends AbstractFile(parentPath, name, extension) {
  def read: String = {
    contest
  }

  def write(message: String): File = {
    new File(parentPath, name, extension, contest ++ message)
  }

  override def isEmpty: Boolean = false

}

