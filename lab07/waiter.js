const fs = require('fs');

var N = 5;
var acquiredForks = 0;
var results = [];

var Fork = function () {
    this.state = 0; 
    return this;
};

Fork.prototype.acquire = function (cb, time = 1) {
    setTimeout(() => {
        if (this.state === 0) {
            this.state = 1;
            cb();
        } else {
            console.log("Czekam " + time + " ms");
            this.acquire(cb, time * 2); 
        }
    }, time);
};

Fork.prototype.release = function () {
    this.state = 0;
};

var Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    return this;
};

Philosopher.prototype.startConductor = function (count) {
    const forks = this.forks;
    const f1 = this.f1;
    const f2 = this.f2;
    const id = this.id;

    const iteration = (cnt) => {
        const tryToEat = () => {
            if (acquiredForks + 2 <= N) { 
                acquiredForks += 2; 
                
                startDate = new Date()
                forks[f1].acquire(() => {
                    forks[f2].acquire(() => {
                        endDate = new Date()
                        const timeSpent = endDate - startDate;
                        results.push({ N: N, time: timeSpent, mode: "waiter" });

                        setTimeout(() => {
                            console.log(`Philosopher ${id} is eating!`);

                            forks[f2].release();
                            forks[f1].release();
                            acquiredForks -= 2;

                            if (cnt < count) iteration(cnt + 1);
                        }, 100); 
                    });
                });
            } else {
                setTimeout(tryToEat, 1); 
            }
        };

        setTimeout(tryToEat, 1);  
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
var philosophers = [];
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startConductor(10);
}


// setTimeout(() => saveCSV(results), 50000); 
