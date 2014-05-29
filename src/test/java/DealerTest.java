import entities.Dealer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DealerTest
{
    @Test
    public void testFullDeal()
    {
        Dealer dealer = new Dealer();
        dealer.getCards(52);
        try
        {
            dealer.getCard();
        } catch (Exception e)
        {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
}
