## Lab 8

#### Zadanie 1

![alt text](pictures/image.png)

**Graf osiągalności**
Mamy 4 stany, 1 token i 4 możliwych posiadaczy tego tokenu więc każdy stan reprezentuje posiadanie tokenu w konkretnym miejscu.
![alt text](pictures/image-1.png)

1. Jakie znakowania są osiągalne?
    - Wszystkie znakowania są osiągalne.
2. Ile wynosi maksymalna liczba znaczników w każdym ze znakowań? Jakie możemy wyciągnąć z tego wnioski n.t. ograniczoności i bezpieczeństwa?
    - Niezależnie od znakowania maksymalna liczba znaczników wynosi 1.
    - Na tej podstawie możemy wywnioskować, że sieć jest 1-ograniczona i przez to także bezpieczna.
3. Czy każde przejście jest przedstawione jako krawędź w grafie? Jaki z tego wniosek n.t. żywotności przejść?
    - Każde przejście jest przedstawione w grafie jako krawędź.
    - Jesteśmy w stanie wykorzystać każde możliwe przejście więc wszystkie przejścia są żywe.
4. Czy wychodząc od dowolnego węzła grafu (znakowania) mona wykonać dowolne przejście? Jaki z tego wniosek n.t. żywotności sieci? Czy są możliwe zakleszczenia?
    - Tak, możliwe jest wykonanie dowolnego przejścia.
    - Sieć jest żywa.
    - Zakleszczenie nie jest możliwe.

**Analiza niezmienników**

![alt text](pictures/image-5.png)

1. Analiza niezmienników przejść (T-invariants)
    - Sieć posiada dwa możliwe cykle przejść, co wskazuje na jej odwracalność. Dzięki temu sieć może powrócić do stanu początkowego z dowolnego osiągalnego stanu.
2. Analiza niezmienników miejsc (P-invariants)
    - Dla każdego znakowania osiągalnego ze znakowania początkowego ilość znaczników w sieci jest stała i wynosi 1. Sieć jest zatem zachowawcza.

#### Zadanie 2 

![alt text](pictures/image-2.png)

Po kilku iteracjach tokeny nagromadziły się w P3:
![alt text](pictures/image-3.png)

**Analiza niezmienników**
Tranzycja T2 będzie ciągle namnażać znaczniki w miejscu P3. Nie da się wrócić do znakowania początkowego zatem sieć nie jest odwracalna.
![alt text](pictures/image-6.png)

**Graf osiągalności**

1. Czy sieć jest żywa?
    - Tak, sieć jest żywa, ponieważ niezależnie od braku ograniczoności miejsca P3, z dowolnego znakowania uzyskanego, ze znakowania początkowego będziemy mogli wykonać ciąg tranzycji umożliwiający odpalenie dowolnej z nich.
2. Czy sieć jest ograniczona?
    - Sieć nie jest ograniczona ponieważ odpalenie tranzycji T2 nieodwracalnie dodaje znacznik do miejsca P1. Zatem pomimo tego, że miejsca P0, P2 oraz P3 są 1-ograniczone to przez brak ograniczoności P3 sieć nie jest ograniczona i przez to także nie jest bezpieczna

![alt text](pictures/image-4.png)

![alt text](pictures/image-7.png)

#### Zadanie 3

Wzajemne wykluczenie:
![alt text](pictures/image-8.png)

**Analiza niezmienników**

1. Wyjaśnić znaczenie równań.
    - W parach (P0, P1) oraz (P3, P4) zawsze będzie po 1 znaczniku - wynika to z pierwszego i trzeciego równania. 
2. Które równanie pokazuje działanie ochrony sekcji krytycznej?
    - Sekcje krytyczną oznacza równanie które mówi że zasób jest wolny wtedy znacznik znajduje się w P2 albo jest zajęty przez jeden ze stanów P1, P3.
![alt text](pictures/image-9.png)

#### Zadania 4

Problem Producent-Konsument:
![alt text](pictures/image-10.png)

**Analiza niezmienników**

1. Które równanie mówi nam o rozmiarze bufora?
    - Równanie 3 mówi o rozmiarze bufora - M(P6) + M(P7) = 3.
2. Czy siec jest zachowawcza?
    - Tak sieć jest zachowawcza, ponieważ liczba tokenów w sieci jest stała i wynosi 5. Każda tranzycja ma tyle samo wejść co wyjść.
    
![alt text](pictures/image-11.png)

#### Zadania 5

Problemu Producent-Konsumet z nieskończonym buforem (po lewej producent, po prawej konsument):
![alt text](pictures/image-14.png)

**Analiza niezmienników**

1. Zaobserwować brak pełnego pokrycia miejsc.
    - Ze względu na miejsce P6 sieć nie będzie ograniczona ani bezpieczna ponieważ to miejsce oznacza nieskończony bufor. Nie będzie zachowawcza bo tranzycja T0 produkuje tokeny.
![alt text](pictures/image-15.png)

#### Zadanie 6

![alt text](pictures/image-16.png)

**Graf osiągalności**
Gdy dojdziemy do stanu S6 i S7 to dochodzi do zakleszczenia.
![alt text](pictures/image-17.png)

**Właściwości sieci**
- Sieć jest ograniczona bo każde z miejsc może mieć maksymalnie 1 token.
- Sieć jest bezpieczna bo jest 1-ograniczona.
- Może dojść do zakleszczenia.

![alt text](pictures/image-18.png)