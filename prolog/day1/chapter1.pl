book(fukui, aegis).
book(fukui, tweleveYO).
book(jeff, cooking_for_geeks).

music(jon, guitar).
music(dick, guitar).
music(bill, piano).
music(lenon, vocal).
music(misia, vocal).
music(kato, guitar).

searchAllBooks(X, Y) :- book(X, Y).
searchGuitarist(X) :- music(X, guitar).