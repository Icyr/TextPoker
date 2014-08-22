package entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TableTest
{
    @Test
    public void testTableCardsToString()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card(Color.CLUBS, 10));
        tableCards.add(new Card(Color.DIAMONDS, 11));
        tableCards.add(new Card(Color.SPADES, 12));
        tableCards.add(new Card(Color.HEARTS, 13));
        tableCards.add(new Card(Color.HEARTS, 14));
        Table table = new Table();
        table.setCards(tableCards);
        String ethalonString = "10C JD QS KH AH ";
        String actualString = table.tableCardsToString();
        assertThat(actualString, equalTo(ethalonString));
    }
}
