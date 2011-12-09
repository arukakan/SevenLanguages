% Reverse the elements of a list.
myReverse([], []).
myReverse(Answer, [Head|Tail])
 :- myReverse(List, Tail), append(List, [Head], Answer).

%Find the smallest element of a list.
myMin(X, [X|[]]).
myMin(Ans, [Head|Tail]) :- myMin(Ans2, Tail), Ans is min(Head, Ans2).

%Sort the elements of a list.(InsertionSort)
myInsert(Answer, AddObject, []) :- append([AddObject], [], Answer).
myInsert(Answer, AddObject, [Head|Tail])
 :- (AddObject < Head), append([AddObject], [Head|Tail], Answer).
myInsert([Head|Answer], AddObject, [Head|Tail])
 :- \+(AddObject < Head), myInsert(Answer, AddObject, Tail).

myInsertionSort(Answer, [Head|[]]) :- myInsert(Answer, Head, []).
myInsertionSort(Answer, [Head|Tail]) :- myInsertionSort(SubAnswer, Tail), myInsert(Answer, Head, SubAnswer).

%Fibonacci
fib(1, 0).
fib(1, 1).
fib(Answer, Counter) :-
  Counter > 1,
  SubC1 is Counter - 1, fib(SubA1, SubC1),
  SubC2 is Counter - 2, fib(SubA2, SubC2),
  Answer is SubA1 + SubA2.

%Factorial
fact(1, 0).
fact(Answer, Counter) :-
  Counter > 0,
  SubCounter is Counter - 1,
  fact(SubAnswer, SubCounter),
  Answer is SubAnswer * Counter.
  