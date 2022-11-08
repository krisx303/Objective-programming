# Odpowiedzi na pytania

### Lab 3 zadanie 10
### Odpowiedz na pytanie: jak zaimplementować mechanizm, który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu.

Można to osiągnąć na kilka sposobów. Wszystko zależy od tego na jakiej ilości danych będziemy operować.

### Lazy solution:
W przypadku małego obszaru (np 4x4 jak w zadaniu) na którym możliwe będzie przemieszczanie się obiektów (co jednoznacznie wpływa na maksymalną
liczbę obiektów Animal) najprostszym rozwiązaniem byłaby lista tych obiektów. Zawierałaby ona wszystkie 
stworzone obiekty (prawdopodobnie byłby one dodawane przy inicjalizacji).
Wtedy przy każdym wykonanym ruchu Animal iteracyjnie sprawdzalibyśmy czy nowa pozycja jest wolna.

### Not the worst solution:
Można też stworzyć tablicę bool isFreeCell[x][y] gdzie (x, y) to rozmiar mapy. Takie rozwiązanie będzie dużo
sprawniejsze obliczeniowo O(1) lecz kosztem pamięci ponieważ nawet dla 1 obiektu przechowujemy informacje o wszystkich
potencjalnych miejscach.
Każdy obiekt Animal ma jednoznacznie określoną pozycję a więc podczas przesunięcia ustawiamy poprzednią komórkę na
'dostępną' a nową na 'zajętą'

### Best solution:
Dla większego zbioru danych dużo efektywniejszym rozwiązaniem będzie użycie Hashmapy, dzięki której niskim 
kosztem pamięci jak i obliczeń będziemy mogli jednoznacznie stwierdzić czy pole jest wolne. 
Kluczem byłby Obiekt Vector2d (wymagane użycie hashCode) a wartością zmienna logiczna.
Przy poruszeniu się, zmieniamy wartość dla poprzedniego wektora na false (lub usuwamy całkowicie zostawiając w
hashmapie jedynie wartosci true) i dodajemy / (zmieniamy isniejący) wektor obecnej pozycji na true. ;)
