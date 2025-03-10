## Producenci i konsumenci - ciekawe warianty
Należy rozwiązać oba zadania za pomocą semaforów lub monitorów (do wyboru) dostępnych w języku Java. Można korzystać z:

* mechanizmu monitorów synchronized, wait, notify
* mechanizmu lock, await, signal
* mechanizmu semaforow własnych bądz z pakietu java.util concurrent
* W implementacji nie jest dozwolone korzystanie/implementowanie własnych kolejek FIFO, należy używac tylko mechanizmu monitorow lub semaforow !

#### Zadania
1. **Przetwarzanie potokowe z buforem**
    * Bufor o rozmiarze N - wspólny dla wszystkich procesow!
    * Proces A będacy producentem.
    * Proces Z będacy konsumentem.
    * Procesy B, C, ..., Y będace procesami przetwarzajacymi. Każdy proces otrzymuje daną wejściową od procesu poprzedniego, jego wyjscie zas jest konsumowane przez proces następny.
    * Procesy przetwarzają dane w miejscu, po czym przechodzą do kolejnej komórki bufora i znowu przetwarzają ją w miejscu.
    * Procesy dzialają z różnymi prędkościami.

    Zaimplementować rozwiązanie przetwarzania potokowego (Przykładowe załozenia: bufor rozmiaru 100, 1 producent, 1 konsument, 5 uszeregowanych procesów przetwarzających.) Od czego zależy prędkość obróbki w tym systemie ? Rozwiązanie za pomocą semaforów lub monitorów (dowolnie)

2. **Producenci i konsumenci z losową iloscią pobieranych i wstawianych porcji**
    * Bufor moze pomiescic 2M nierozróżnialnych elementow (kolejnosc nie istotna)
    * Jest wielu producentów i konsumentów
    * Producent wstawia do bufora losową liczbę elementów (nie wiecej niz M)
    * Konsument pobiera losową liczbę elementów (nie wiecej niz M)

    Wyjasnić po co zalozenie o M.
    Zaimplementować rozwiązanie z losową liczbą porcji w dwóch wariantach:
    * Wariant naiwny: producent / konsument jest wstrzymywany aż w buforze nie będzie wystarczająco dużo miejsca / elementów.
    * Wariant sprawiedliwy: zapobiega zagłodzeniu procesów produkujących lub konsumujących duże porcje. Wymyślić własne (NIE używać prorytetów!), bądź użyć rozwiązania według [1]

    Proszę uruchomić obydwa warianty algorytmu dla wielu producentów i konsumentów, a następnie zmierzyć i przedstawić na wykresie porównawczym (osobno dla producentów i konsumentów) średni czas oczekiwania na dostęp do bufora w zależności od wielkości porcji (czas metod put() i get()).

#### Uwagi:
* Producenci i konsumenci losują wielkość porcji przed każdą operacją.
* Każda wielkość porcji jest losowana z równym prawdopodobieństwem;
* Do pomiaru czasu proszę używać System.nanoTime()
* Przykladowy zestaw testow: M rowne 1000, 10k, 100k Konfiguracji P-K: 10P+10K, 100P+100K, 1000P+1000K

[1] Problemy pochodzą z książki Z. Weiss, T. Gruzlewski, Programowanie wspolbiezne i rozproszone.