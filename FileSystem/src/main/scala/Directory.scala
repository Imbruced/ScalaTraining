class Directory(override val parentPath: String, override val name: String, override val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def add(directory: DirEntry): Directory = {
    if (!this.contents.contains(directory)) {
      new Directory(this.parentPath, this.name, this.contents ++ List(directory))
    }
    else {
      println(s"Directory ${directory.name} already exists")
      this
    }
  }

  def hasEntry(name: Directory): Boolean = {
    this.contents.contains(name)
  }
  def filterEntry(name: String): DirEntry = {
    val elements = contents.filter(x => x.name == name)
    try{
      elements.head

    }
    catch {
      case e: java.util.NoSuchElementException => throw new java.lang.IndexOutOfBoundsException(s"There is no path $name")
    }
  }

  def getDirEntry(name: String): Directory = {
    val target = filterEntry(name)
    if (!target.isFile){
      new Directory(target.parentPath, target.name, target.contents)
    }
    else{
      throw new java.lang.Exception("This is not directory")
    }
  }

  def path: String = {
    if (this.isRoot){
      Directory.SEPARATOR
    }
    else{
      if (this.parentPath == Directory.SEPARATOR){
        this.parentPath + this.name
      }
      else this.parentPath + Directory.SEPARATOR + this.name
    }
  }
  def isEmpty: Boolean = {
    if (this.contents.isEmpty) {
      true
    }
    false
  }
  override def isFile: Boolean = false
  def split: List[String] = {
    Directory.split(path)
  }

  def isRoot: Boolean = {
    if (parentPath == ""){
      true
    }
    else false
  }

  def moveToDir(paths: List[String]): Directory = {
    def helperMoveToDir(directory: Directory, paths: List[String], startingIndex: Int): Directory = {
      if (startingIndex >= paths.length){
        directory
      }
      else {
        val dirEntry = directory.filterEntry(paths(startingIndex))
        val newDirectory = new Directory(dirEntry.parentPath, dirEntry.name, dirEntry.contents)

        helperMoveToDir(newDirectory, paths, startingIndex+1)
      }


      }

    helperMoveToDir(this, paths, 0)
  }

  def removeDir(name: String): Directory = {
    val removedDirEntry = contents.span(x => x.name != name) match { case (before, atAndAfter) => before ::: atAndAfter.drop(1) }
    new Directory(this.parentPath, this.name, removedDirEntry)
  }


}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "/")
  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())

  def split(path: String): List[String] = {
    val dirSplitted = path.split(Directory.SEPARATOR).toList
    dirSplitted.filter(x => x!="")
  }

  def getDifferenceHelper(oneLocations: List[String], twoLocations: List[String], data: List[String], starting_index: Int): List[String] = {
    if (oneLocations(starting_index) != twoLocations(starting_index) || starting_index >= oneLocations.length) data
    else {
      val addData = data ++ List(oneLocations(starting_index))
      getDifferenceHelper(oneLocations, twoLocations, addData, starting_index+1)
    }
  }
  def getPathDifference(oneLocations: List[String], twoLocations: List[String]): List[String] = {
    val data = List()
    getDifferenceHelper(oneLocations, twoLocations, data, 0)
  }

  def updateRoot(state: State, newWd: Directory): State = {
    val updatedRoot = updatePathsHierarchy(state, newWd)
    State(updatedRoot, newWd, newWd.path)
  }

  def updatePathsHierarchy(state: State, wd: Directory): Directory = {
    def helperUpdater(state: State, wd: Directory, parentPathDir: String): Directory = {
      if (wd.isRoot){
        wd
      }
      else {
        val splittedParent = Directory.split(parentPathDir)

        val currentWdDir = state.root.moveToDir(splittedParent)

        val withRemoved = currentWdDir.removeDir(wd.name)

        helperUpdater(state, withRemoved.add(wd), withRemoved.parentPath)
      }
    }
    helperUpdater(state, wd, wd.parentPath)
  }

}