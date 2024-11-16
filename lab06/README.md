## Laboratorium 6: Wstęp do Node.js

### Podstawy
Node.js to platforma zbudowana na silniku V8 JavaScript Engine wspierająca budowanie szybkich, skalowalnych aplikacji sieciowych. Podstawą Node.js jest zarządzanie zdarzeniami (event-driven) wywołanymi przez asynchroniczne operacje wejscia-wyjścia (non-blocking I/O).
Wykład wsprowadzający. Zalecane obejrzenie!

### Instalacja, moduły
1. Strona domowa: http://nodejs.org
2. Rejestr modułów - NPM: http://npmjs.org
    Instalacja potrzebnego modułu:
    ```
    npm install <nazwa_modułu>
    ```

### Model przetwarzania Node.js
1. Everything runs in parallel except your code.
2. setTimeout(callback, delay[, arg][, ...]) szereguje wywołanie callbacku po zadanym czasie. Ponieważ nie można przewidzieć ile innych callbackow będzie zarejestrowane w pętli zdarzeń do wykonania do tego czasu, callback może nie wykonać się dokładnie w zadanym czasie (ale w miarę blisko tego czasu).
3. Obiekt process API oraz dodatkowe wyjaśnienie
4. Dzialanie process.nextTick()
5. Funkcje (w tym anonimowe) w javascript


### Ćwiczenia
1. Uruchom kilkakrotnie następujący [program](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1.js).
2. Wymuszanie sekwencyjnego wykonania:
    [Rozwiazanie 1](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1a.js)
    [Rozwiazanie 2](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1b.js)
3. **Zadanie 1** Zaimplementuj funkcje loop, wg instrukcji w pliku z Rozwiazaniem 2
4. **Zadanie 2** Zaimplementuj funkcję inparallel wg instrukcji z pliku [node2.js](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node2.js)
5. **Zadanie 3** (Dla zainteresowanych). Zapoznaj się z ułatwieniami dot. łańcucha callbacków [Promises/ async await](https://javascript.info/async). Przestaw rozwiązanie wybranego zadania z tego lab za pomocą async/await