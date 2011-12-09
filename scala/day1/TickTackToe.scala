class TickTackToe(boardSize : Int){ 
	val (notset, circle, cross) = (0, 1, 2)
	val item = Array("-", "O", "X")
	val board = Array.ofDim[Int](boardSize, boardSize)
	for(i <- 0 until boardSize){
		for(j <- 0 until boardSize) board(i)(j) = notset
	}
	
	val searchLineLength = boardSize * 2 + 2
	val searchLines = Array.ofDim[(Int, Int)](searchLineLength, boardSize)
	for(i <- 0 until boardSize){
		for(j <- 0 until boardSize){
			searchLines(i)(j) = (i, j)
			searchLines(j + boardSize)(i) = (j, i)
		}
	}
	for(i <- 0 until boardSize){
		searchLines(boardSize * 2)(i) = (i, i)
		searchLines(boardSize * 2 + 1)(i) = (boardSize - i - 1, i)
	}
	
	def setCirle(x : Int, y : Int) : Boolean = {
		if(board(x)(y) == notset){
			board(x)(y) = circle
			return true
		}
		
		return false
	}
	
	def setCross(x : Int, y  : Int) : Boolean = {
		if(board(x)(y) == notset){
			board(x)(y) = cross
			return true
		}
		
		return false
	}
	
	def check() : String = {
		for(i <- 0 until searchLines.length){
			val answer = checkLine(i)
			if(answer != notset) return item(answer)
		}
		
		return item(notset)
	}
	
	def checkLine(line : Int) : Int = {
		var point = searchLines(line)(0)
		val value = board(point._1)(point._2)
		if(value == notset) return notset
			
		for(j <- 1 until searchLines(line).length){
			point = searchLines(line)(j)
			if(board(point._1)(point._2) != value) return notset
		}
		
		return value
	}
	
	def printBoard(){
		for(i <- 0 until boardSize){
			for(j <- 0 until boardSize){			
				print(item(board(i)(j)) + " ")
			}
			
			println()
		}
	}
}

object TickTackToe {
	val item = Array("O", "X")		

	def main(args: Array[String]): Unit = {
		val size = 3

		val tick = new TickTackToe(size)

		doTickTackToe(0, size, tick)
	}
	
	def doTickTackToe(counter : Int, size: Int, tick : TickTackToe) : Unit = {
		if(counter >= size * size){
			println("draw game")
			return	
		} 
		
		println("Turn " + counter.toString +" Input " + item(counter % 2) + "'s Position like 1,2")
		var x = -1
		var y = -1
		
		try {
			val line = readLine.split(',')
			x = line(0).toInt
			y = line(1).toInt
			if(x >= size || y >= size) throw new Exception("error")
	    } catch {
	      case e:Exception => {
				println("error. please re-input")
				return doTickTackToe(counter, size, tick)	
			}
	    }
	
		if(counter % 2 == 0){
			if(!tick.setCirle(x,y)){
				println("error. please re-input")
				return doTickTackToe(counter, size, tick)
			}
		}
		else{
			if(!tick.setCross(x,y)){
				println("error. please re-input")
				return doTickTackToe(counter, size, tick)
			}
		} 

		tick.printBoard		
		val winner = tick.check
		if(winner != "-"){
			println("Winner is " + winner)
			return
		}

		return doTickTackToe(counter + 1, size, tick)
	}
}

