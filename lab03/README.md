## Laboratorium 3 - monitors

* W języku java istnieja również nowsze (bardziej elastyczne) mechanizmy synchronizacji, zaimplementowane w pakiecie java.util.concurrent.locks
* W zadaniach nalezy użyc tego mechanizmu. Nalezy korzystac z lock, condition, await, signal
* Przed zrobieniem zadań przeanalizuj monitor BoundedBuffer przeznaczony dla producentow i konsumentow.
#### 1. Drukarki
Grupa wątków P (ilosc N) korzysta z M drukarek N>M. Dzialanie wątku
```
forever{
  Utworz_zadanie_do_druku();
  nr_drukarki=Monitor_Drukarek.zarezerwuj();
  drukuj(nr_drukarki);
  Monitor_Drukarek.zwolnij(nr_drukarki);
}
```
Napisz monitor Monitor_Drukarek.

#### 2. Stolik dwuosobowy
Napisz monitor Kelner sterujacy dostepem do stolika dwuosobowego. Ze stolika korzysta N par osob. Algorytm osoby z pary o numerze j:
```
forever{
  wlasne sprawy;
  Kelner.chce_stolik(j);
  jedzenie;
  Kelner.zwalniam();
}
```
Stolik jest przydzielany parze w momencie gdy obydwie osoby tego zazadaja, zwalnianie nie musi byc jednoczesne.

#### Literatura
---
Katarzyna Rycerz, kzajac at agh.edu.pl