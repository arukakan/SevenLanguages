import scala.io._
import scala.actors._
import Actor._
import scala.xml.XML

object PageLoder{
	def getPageSize(url : String) = Source.fromURL(url).mkString.length
	def getPage(url : String) = Source.fromURL(url).mkString
}

val urls = List("http://www.twitter.com/",
				"http://arukansoft.net/",
				"http://www.nicovideo.jp/",				
				"http://www.yahoo.co.jp/")
				
def timeMethod(method : () => Unit) = {
	val start = System.nanoTime
	method()
	val end = System.nanoTime
	println("Method took " + (end - start) / 1000000000.0 + " seconds.")
}

def getPageSizeSequentially() = {
	println("sequentially")
	for(url <- urls){
	println(url)		
		println("Size for " + url + ": " + PageLoder.getPageSize(url))
	}
}

def getLinks(url : String) : Unit = {
	println("getLinks1")
	val html = PageLoder.getPage(url)
	val dt = "<a.*?href.*?>".r ;
	val links = dt.findAllIn(html)
	println(links.length)
}

def getPageSizeConcurrently() = {
	val caller = self
	
	for(url <- urls){
		actor { caller ! (url, PageLoder.getPageSize(url))}
	}
	
	for(i <- 1 to urls.size){
		receive{
			case(url, size) => println("Size for " + url + ": " + size)
		}
	}
}

getLinks("http://arukansoft.net/")
//println("Sequential run:")
//timeMethod{getPageSizeSequentially}
//println("Concurrent run:")
//timeMethod{getPageSizeConcurrently}

