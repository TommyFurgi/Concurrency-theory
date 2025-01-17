import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object WebCrawler extends App {

  import org.htmlcleaner.HtmlCleaner
  import java.net.URL

  def fetchLinks(url: String): Future[Set[String]] = Future {
    println(s"Pobieranie: $url")
    try {
      val cleaner = new HtmlCleaner()
      val rootNode = cleaner.clean(new URL(url))
      val elements = rootNode.getElementsByName("a", true)

      elements.flatMap { elem =>
        Option(elem.getAttributeByName("href"))
          .filter(_.startsWith("http"))
      }.toSet
    } catch {
      case e: Exception =>
        println(s"Błąd podczas pobierania $url: ${e.getMessage}")
        Set.empty[String]
    }
  }

  def crawl(url: String, depth: Int): Future[Set[String]] = {
    if (depth == 0) Future.successful(Set(url))
    else {
      fetchLinks(url).flatMap { links =>
        val futures = links.map(link => crawl(link, depth - 1))
        Future.sequence(futures).map(_.flatten + url)
      }
    }
  }

//  val startUrl = "https://google.com"
  val startUrl = "https://www.agh.edu.pl"
  val maxDepth = 2

  val result = crawl(startUrl, maxDepth)

  result.onComplete {
    case Success(urls) =>
      println("Znalezione strony:")
      urls.foreach(println)
    case Failure(exception) =>
      println(s"Wystąpił błąd podczas crawl: ${exception.getMessage}")
  }

  Thread.sleep(100000)
}
