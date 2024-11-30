const fs = require('fs');

var N = 5;
var results = [];

var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb, time=1) { 
    // zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
    // (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
    // 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
    //    i ponawia probe itd.
    setTimeout(() => {
        if (this.state === 0) { 
            this.state = 1;    
            if (cb) cb();      
        } else {              
            console.log("Czekam " + time + " ms");
            this.acquire(cb, time * 2);
        }
    }, time);
}

Fork.prototype.release = function() { 
    this.state = 0; 
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

Philosopher.prototype.startAsym = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    
    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
    const first = id % 2 === 0 ? this.f1 : this.f2;
    const second = id % 2 === 0 ? this.f2 : this.f1;

    const iteration = (cnt) => {
        startDate = new Date()
        forks[first].acquire(() => {
            forks[second].acquire(() => {
                endDate = new Date()
                const timeSpent = endDate - startDate;
                results.push({ N: N, time: timeSpent, mode: "asymmetric" });

                setTimeout(() => {
                    console.log(`Philosopher ${id} is eating!`);
                    forks[second].release();
                    forks[first].release();
                    if (cnt < count) 
                        setTimeout(() => iteration(cnt + 1), 100);
                }, 100);
            });
        });
    };
    iteration(1);
};

function saveCSV(data) {
    const csvRows = [];
    data.forEach(row => {
        csvRows.push(`${row.N},${row.time},${row.mode}`);
    });

    const csvContent = csvRows.join('\n') + '\n'; 
    fs.appendFileSync('results.csv', csvContent); 
}


var forks = [];
var philosophers = []
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startAsym(10);
}

// setTimeout(() => saveCSV(results), 30000); 