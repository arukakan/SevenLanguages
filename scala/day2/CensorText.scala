trait Censor {
  val document: List[String]
  val dictionary = Map("Shoot" -> "Pucky", "Darn" -> "Beans")
  
  def censor = document.map(word => dictionary.getOrElse(word, word))
}

class Text extends Censor {
  val document = List("Gosh", "Darn", "It", "Shit", "Shoot")
}

object CensorText{
	def main(args : Array[String]) : Unit = {
		val text = new Text()
		val censorList = List(("Shoot", "Pucky"), ("Darn", "Beans"))
		print(text.censor)
	}
}