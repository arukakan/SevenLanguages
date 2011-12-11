val words = List("first", "second", "third");
println(words.foldLeft(0)((sum, word) => sum + word.length))