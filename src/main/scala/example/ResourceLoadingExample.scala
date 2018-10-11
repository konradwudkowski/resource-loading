package example
import scala.io.Source

object ResourceLoadingExample extends App {

  val resource = getClass.getResource("/file1")
  val path     = resource.getPath
  val text     = Source.fromFile(path).mkString

  println(text)

}
