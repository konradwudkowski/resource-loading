package example

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
