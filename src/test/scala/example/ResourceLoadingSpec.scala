package example

import java.io.File
import java.nio.file.Paths

import org.scalatest._

import scala.io.Source

class ResourceLoadingSpec extends FreeSpec with Matchers {

  "Testing different ways of loading resources in scala" - {

    "without leading forward slash" - {
      "getClass.getResource(...)" in {
        intercept[NullPointerException] {
          getResource("file1")
        }
      }
      "getClass.getResourceAsStream(...)" in {
        intercept[NullPointerException] {
          getResourceAsStream("file1")
        }
      }
    }

    "with leading forward slash" - {
      "getClass.getResource(...)" in {
        getResource("/file1")
      }
      "getClass.getResourceAsStream(...)" in {
        getResourceAsStream("/file1")
      }
    }

    "Paths.get(resource.toUri()).toFile()" in {
      val resource = getClass.getResource("/file1")
      val path     = Paths.get(resource.toURI)
      val text     = Source.fromFile(path.toFile).mkString

      text shouldBe "file1 contents (test)"
    }

    "Listing files using Paths.get(resource.toUri()).toFile()" in {
      val resource = getClass.getResource("/subdir")
      val path     = Paths.get(resource.toURI)
      val files    = path.toFile().listFiles.toList

      files.map(_.getName) should contain theSameElementsAs List("subdirFile1", "subdirFile2")
    }

    "using new File and 'user.dir' system property" in {
      val userDir = System.getProperty("user.dir")
      println(s"userDir is $userDir")
      val file1 = new File(userDir + "/src/main/resources/file1")
      val text  = Source.fromFile(file1).mkString

      text shouldBe "file1 contents"
    }

    def getResource(s: String) = {
      val resource = getClass.getResource(s)
      val path     = resource.getPath
      val text     = Source.fromFile(path).mkString

      text shouldBe "file1 contents (test)"
    }

    def getResourceAsStream(s: String) = {
      val resourceAsStream = getClass.getResourceAsStream(s)
      val text             = Source.fromInputStream(resourceAsStream).mkString

      text shouldBe "file1 contents (test)"
    }

  }

}
