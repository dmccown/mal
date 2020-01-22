import * as readline from 'readline';

function read(printline: string): string {
    return printline;
}

function evalMal(printline: string): string {
    return printline;
}

function print(printline: string): string {
    return printline;
}

function rep(input: string): string {
    return print(evalMal(read(input)));
}

let rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let readInput = function() {
    rl.question('user> ', function(input) {
        console.log(rep(input));
        readInput();
    });
}

readInput();