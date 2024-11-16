
function printAsync(s) {
    return new Promise((resolve) => {
        var delay = Math.floor((Math.random()*1000)+500);
        setTimeout(function() {
            console.log(s);
            resolve();
        }, delay);
    });
}

async function task1(cb) {
    await printAsync("1");
}

async function task2(cb) {
    await printAsync("2");
}

async function task3(cb) {
    await printAsync("3");
}


/* 
** Zadanie:
** Napisz funkcje loop(n), ktora powoduje wykonanie powyzszej 
** sekwencji zadan n razy. Czyli: 1 2 3 1 2 3 1 2 3 ... done
** 
 */
async function sequence() {
    await task1(); 
    await task2();
    await task3();
}

async function loop(n) {
    for (let i = 0; i < n; i++) {
        await sequence(); 
    }
    console.log("done!");
}
 
loop(4);