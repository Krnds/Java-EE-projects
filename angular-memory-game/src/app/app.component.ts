import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CardData } from './card-data.model';
import { RestartDialogComponent } from './restart-dialog/restart-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'angular-memory-game';
  cardImages = [
    'G8cB8hY3yvU', /** dog */
    '3isjMlIlj4A', /** elephant */
    'dAYGcP1fc9E', /** swan */
    'l4A5E42LCDw', /** leopard */
    'V7SKRhXskv8', /** goldfish */
    'Ws6Tb1cI0co' /** snake */
  ];

  cards: CardData[] = [];
  flippedCards: CardData[] = [];
  matchedCount = 0;

  shuffleArray(anArray: any[]): any[] {
    return anArray.map(a => [Math.random(), a])
      .sort((a, b) => a[0] - b[0])
      .map(a => a[1]);
  }

  constructor(private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.setupCards();
  }

  setupCards(): void {
    this.cards = [];
    this.cardImages.forEach((image) => {
      const cardData: CardData = {
        imageId: image,
        state: 'default'
      };

      this.cards.push({ ...cardData });
      this.cards.push({ ...cardData });

    });

    this.cards = this.shuffleArray(this.cards);
  }

  cardClicked(index: number): void {
    const cardInfo = this.cards[index]; /** current card */

    if (cardInfo.state === 'default' && this.flippedCards.length < 2) { /** when 0 or 1 card is flipped, check for match */
      cardInfo.state = 'flipped';
      this.flippedCards.push(cardInfo);

      if (this.flippedCards.length > 1) { /** when 2 cards are flipped, check for match */
        this.checkForCardMatch();
      }

    } else if (cardInfo.state === 'flipped') {/** if currentCard is flipped but no match, return to default and empty flippedCards */
      cardInfo.state = 'default';
      this.flippedCards.pop(); /** remove last card from flippedCards array */

    }
  }

  checkForCardMatch(): void {
    setTimeout(() => {
      const cardOne = this.flippedCards[0];
      const cardTwo = this.flippedCards[1];
      const nextState = cardOne.imageId === cardTwo.imageId ? 'matched' : 'default'; /** if card 1 == card 2, match, else return to default */
      cardOne.state = cardTwo.state = nextState;

      this.flippedCards = [];

      if (nextState === 'matched') {
        this.matchedCount++;
        if (this.matchedCount === this.cardImages.length) {
          const dialogRef = this.dialog.open(RestartDialogComponent, {
            disableClose: true
          });
          dialogRef.afterClosed().subscribe(() => {
            this.restart();
          });
        }
      }

    }, 800);
  }

  restart(): void {
    this.matchedCount = 0;
    this.setupCards();
  }
}
