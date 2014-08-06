package gui.presenter;

import entities.Hand;
import gui.view.PlayerView;

public class PlayerPresenter
{
    private PlayerView view;

    public PlayerPresenter(PlayerView playerView)
    {
        this.view = playerView;
    }


    public void discardCards()
    {
        view.setPlayersFirstCard(null);
        view.setPlayersSecondCard(null);
    }

    public void setHandCards(Hand hand)
    {
        view.setPlayersFirstCard(hand.getCards().get(0).getIcon());
        view.setPlayersSecondCard(hand.getCards().get(1).getIcon());
    }

    public void setCash(int value)
    {
        view.setCash(Integer.toString(value));
    }
}
