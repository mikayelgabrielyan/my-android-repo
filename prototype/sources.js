function startGame() {
    this.currentTurnIndex = 0;
    this.deck = new Deck();
}

function Deck() {
    this.cards = [];
    this.cardsDrawn = 0;
    var suits = ["spades", "diamonds", "hearts", "clubs"];
    var names = ["9", "10", "jack", "queen", "king", "ace"];
    for (var suit in suits) {
        for (var name in names) {
            this.cards.push(new Card(names[name], suits[suit]));
        }
    }
}

Deck.prototype.getCard = function () {
    if (this.cards.length == this.cardsDrawn) {
        return null;
    }
    var random = Math.floor(Math.random() * (this.cards.length - this.cardsDrawn));
    var temp = this.cards[random];
    this.cards[random] = this.cards[this.cards.length - this.cardsDrawn - 1];
    this.cards[this.cards.length - this.cardsDrawn - 1] = temp;
    this.cardsDrawn++;
    return temp;
};

function Card(name, suit) {
    this.name = name;
    this.suit = suit;
}

Card.prototype.image = function () {
    return "http://www.jonarnaldo.com/sandbox/deck_images/" + name + "_of_" + suit + ".png";
};

Card.prototype.value = function () {
    if (this.name == "jack") {
        return [2];
    } else if (this.name == "queen") {
        return [3];
    } else if (this.name == "king") {
        return [4];
    } else if (this.name == "ace") {
        return [11];
    } else if (this.name == "10") {
        return parseInt(this.name, 10);
    } else {
        return [0];
    }
};

function Player() {
    this.cards = [];
}

Player.prototype.addCard = function () {
    this.cards.push(deck.getCard());
};

// Player.prototype.score = function () {
//     var score = 0;
//     var aces = [];

//     for (var i = 0; i < this.cards.length; i++) {
//         var value = this.cards[i].value() // value array ex.[10]
//         if (value.length == 1) {
//             score += value[0];
//         } else {
//             aces.push(value);
//         }
//     }

//     for (var j = 0; j < aces.length; j++) {
//         if (score + aces[j].value[1] <= 21) {
//             score + aces[j].value[1];
//         } else {
//             score + aces[j].value[0];
//         }
//     }
//     return score;

// };
function startRound() {
    var deck = new Deck();
    var player1 = new Player();
    var player2 = new Player();
    for (let i = 0; i < 8; ++i) {
        player1.addCard;
    }
    for (let i = 0; i < 8; ++i) {
        player2.addCard;
    }

    var cardName = player1.cards[player1.cards.length - 1].name;
    var cardSuit = player1.cards[player1.cards.length - 1].suit;
    $("#table").append(cardName + cardSuit);




}