## Laboratorium 2 - semafory

* Semafor to mechanizm synchronizacji procesów zaproponowany przez Dijkstrę.

* Semafor jest zmienną całkowitą, która przyjmuje wartości nieujemne (≥0) lub — w przypadku semaforów binarnych — logiczne. Zmienna semaforowa musi mieć nadaną początkową wartość (nieujemną).

* Po nadaniu początkowej wartości zmiennej semaforowej można na niej wykonywać tylko dwa rodzaje operacji:
    1. P — opuszczanie semafora (hol. proberen), powoduje zmniejszenie wartości zmiennej semaforowej,
    2. V — podnoszenie semafora (hol. verhogen). powoduje zwiekszanie wartości zmiennej semaforowej.

* Wykonując operację semaforową, proces może zastać zablokowany (przejść w stan oczekiwania). Typowym przypadkiem jest blokowanie w operacji opuszczania semafora. Operacja opuszczania nie zakończy się do czasu, aż wartość zmiennej semaforowej będzie na tyle duża, że zmniejszenie jej wartości w wyniku tej operacji nie spowoduje przyjęcia wartości ujemnej.

* Rodzaje semaforów
    1. semafor binarny ma dwa stany: true (podniesiony otwarty) i false (opuszczony zamknięty). Wielokrotne podnoszenie takiego semafora nie zmieni jego stanu — skutkiem będzie stan otwarcia. W niektórych rozwiązaniach przyjmuje się, że próba podniesienia otwartego semafora sygnalizowana jest błędem.
    2. semafor ogólny „pamięta” liczbę operacji podniesienia. Zwykle inicjalizowany jest iloscia dostępnego zasobu. Można bez blokowania procesu wykonać tyle operacji opuszczenia semafora, aby jego wartość była nieujemna.

##### Problem pięciu filozofów

* Jest pięciu filozofów i pięć widelców
* Każdy filozof zajmuje sie głownie myśleniem
* Od czasu do czasu potrzebuje zjeść
* Do jedzenia potrzebne mu są oba widelce po jego prawej i lewej stronie
* Jedzenie trwa skończoną (ale nieokresloną z gory) ilość czasu, po czym filozof widelce odkłada i wraca do myślenia
* Cykl powtarza sie od początku

Rozwiązania:

1. Rozwiazanie trywialne: każdy z filozofów podnosi najpierw prawy, a później lewy widelec. Rozwiązanie prowadzi do blokady, gdy wszyscy filozofowie podniosą prawy widelec i czekają na lewy.
2. Rozwiązanie asymetryczne - filozofowie z nieparzystym numerem najpierw podnoszą widelec lewy, z parzystym -- prawy.
3. Rozwiazanie z lokajem (inaczej: kelnerem), który pilnuje, aby o widelce w tym samym czasie rywalizowało tylko 4 filozofów, pozostały czeka. O lokaju można myśleć, jak o dodatkowym semaforze licznikowym z wartością maksymalną N-1, gdzie N - liczba filozofów.
Opis problemu

* Zadanie
    * zaimplementowac semafor binarny za pomoca metod wait i notify/notifyall, uzyc go do synchronizacji wyscigu z poprzedniego laboratorium.
    * zaimplementowac semafor licznikowy (ogolny) za pomoca metod wait i notify/notifyall.
    * zaimplementowac trzy wymienione rozwiązania problemu filozofów za pomocą semaforów

#### Bibliografia
1. Z Weiss, T Gruźlewski "Programowanie współbieżne i rozproszone w przykładach i zadaniach" 

---
Katarzyna Rycerz, kzajac at agh.edu.pl
(takze na postawie opracowan dr B. Balisia)