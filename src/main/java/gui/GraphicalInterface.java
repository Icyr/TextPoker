package gui;

import entities.Hand;
import entities.Table;
import entities.players.Player;
import gui.modules.OpponentModule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends TextualInterface implements Interface
{
    List<OpponentModule> opponents = new ArrayList<OpponentModule>();
    JLabel firstTableCard;
    JLabel secondTableCard;
    JLabel thirdTableCard;
    JLabel forthTableCard;
    JLabel fifthTableCard;
    JLabel playersFirstCard;
    JLabel playersSecondCard;
    JLabel combinationText;
    JLabel raiseErrorLabel;

    public GraphicalInterface()
    {
    }

    public void initialize()
    {
        initializeComponents();
        createGraphicalUI();
        addElementsToPanel();
    }

    private void createGraphicalUI()
    {
        frame.setTitle("Poker Graphical Interface");
        panel.setPreferredSize(new Dimension(640, 480));

        textModule.setBounds(480, 10, 150, 350);
        decisionModule.setBounds(new int[]{10, 400, 150, 30},
                new int[]{165, 400, 150, 30},
                new int[]{320, 400, 150, 30},
                new int[]{475, 400, 150, 30});
        OpponentModule firstOpponent = new OpponentModule();
        firstOpponent.setBound(20, 100);
        opponents.add(firstOpponent);
        OpponentModule secondOpponent = new OpponentModule();
        secondOpponent.setBound(100, 20);
        opponents.add(secondOpponent);
        OpponentModule thirdOpponent = new OpponentModule();
        thirdOpponent.setBound(250, 20);
        opponents.add(thirdOpponent);
        OpponentModule forthOpponent = new OpponentModule();
        forthOpponent.setBound(350, 100);
        opponents.add(forthOpponent);
        combinationText.setBounds(10, 450, 100, 30);
        combinationLabel.setBounds(120, 450, 200, 30);
        cashText.setBounds(500, 450, 100, 30);
        cashLabel.setBounds(600, 450, 30, 30);
        betLabel.setBounds(170, 260, 100, 30);
        betLabel.setHorizontalAlignment(SwingConstants.CENTER);
        betLabel.setFont(betLabel.getFont().deriveFont(32.0f));
        bankLabel.setBounds(170, 150, 100, 30);
        bankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bankLabel.setFont(betLabel.getFont().deriveFont(32.0f));
        raiseErrorLabel.setBounds(475, 425, 150, 30);
        raiseErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        raiseErrorLabel.setForeground(Color.RED);
        raiseErrorLabel.setVisible(false);

        playersFirstCard.setBounds(165, 300, 47, 66);
        playersSecondCard.setBounds(223, 300, 47, 66);
        firstTableCard.setBounds(88, 190, 48, 65);
        secondTableCard.setBounds(141, 190, 48, 65);
        thirdTableCard.setBounds(194, 190, 48, 65);
        forthTableCard.setBounds(247, 190, 48, 65);
        fifthTableCard.setBounds(300, 190, 48, 65);
    }

    protected void initializeComponents()
    {
        super.initializeComponents();
        combinationText = new JLabel("Your combination:");
        playersFirstCard = new JLabel();
        playersSecondCard = new JLabel();
        firstTableCard = new JLabel();
        secondTableCard = new JLabel();
        thirdTableCard = new JLabel();
        forthTableCard = new JLabel();
        fifthTableCard = new JLabel();
        raiseErrorLabel = new JLabel();
    }

    protected void addElementsToPanel()
    {
        super.addElementsToPanel();
        for (OpponentModule opponent : opponents)
        {
            opponent.addToPanel(panel);
        }
        panel.add(combinationText);
        panel.add(playersFirstCard);
        panel.add(playersSecondCard);
        panel.add(firstTableCard);
        panel.add(secondTableCard);
        panel.add(thirdTableCard);
        panel.add(forthTableCard);
        panel.add(fifthTableCard);
        panel.add(raiseErrorLabel);
    }

    @Override
    public void updateTable(Table table)
    {
        textModule.printlnText(table.tableCardsToString());
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                break;
            case 4:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                forthTableCard.setIcon(table.getCardsOnTable().get(3).getIcon());
                break;
            case 5:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                forthTableCard.setIcon(table.getCardsOnTable().get(3).getIcon());
                fifthTableCard.setIcon(table.getCardsOnTable().get(4).getIcon());
                break;
        }
    }

    @Override
    public void prepareForRound()
    {
        setBetAmount(0);
        for (OpponentModule opponentModule : opponents)
        {
            opponentModule.setBet(0);
        }
        playersFirstCard.setIcon(null);
        playersSecondCard.setIcon(null);
        firstTableCard.setIcon(null);
        secondTableCard.setIcon(null);
        thirdTableCard.setIcon(null);
        forthTableCard.setIcon(null);
        fifthTableCard.setIcon(null);
    }

    @Override
    public void deal(List<Player> players)
    {
        for (OpponentModule opponent : opponents)
        {
            opponent.showHiddenHand();
        }
        playersFirstCard.setIcon(players.get(0).getHand().getCards().get(0).getIcon());
        playersSecondCard.setIcon(players.get(0).getHand().getCards().get(1).getIcon());
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        raiseErrorLabel.setVisible(false);
        super.fold(indexOfPlayer);
        if (indexOfPlayer != 0)
        {
            opponents.get(indexOfPlayer - 1).fold();
        }
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        raiseErrorLabel.setVisible(false);
        super.call(indexOfPlayer, callValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponents.get(indexOfPlayer - 1).addToBet(callValue);
        }
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        raiseErrorLabel.setVisible(false);
        super.raise(indexOfPlayer, raiseValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponents.get(indexOfPlayer - 1).addToBet(raiseValue);
        } else
        {
            betLabel.setText(raiseValue + "");
        }
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        super.betBlinds(firstPlayerNumber, secondPlayerNumber, blindSize);
        if (firstPlayerNumber == 0)
        {
            betLabel.setText(blindSize + "");
            opponents.get(0).setBet(blindSize * 2);
            setBank(blindSize * 3);
        } else if (secondPlayerNumber == 0)
        {
            betLabel.setText(blindSize * 2 + "");
            opponents.get(opponents.size() - 1).setBet(blindSize);
            setBank(blindSize * 3);
        } else
        {
            opponents.get(firstPlayerNumber - 1).setBet(blindSize);
            opponents.get(secondPlayerNumber - 1).setBet(blindSize * 2);
            setBank(blindSize * 3);
        }
    }

    @Override
    public void showPlayersHand(int index, Hand hand)
    {
        super.showPlayersHand(index, hand);
        if (index != 0)
        {
            opponents.get(index - 1).showHand(hand);
        }
    }

    @Override
    public void zeroBets()
    {
        betLabel.setText("0");
        for (OpponentModule opponentModule : opponents)
        {
            opponentModule.setBet(0);
        }
    }

    @Override
    public void displayRaiseError()
    {
        super.displayRaiseError();
        raiseErrorLabel.setText("Invalid raise");
        raiseErrorLabel.setVisible(true);
    }

    @Override
    public void removeBankruptPlayer(int index)
    {
        super.removeBankruptPlayer(index);
        if (index != 0)
        {
            opponents.get(index - 1).removeFromPanel(panel);
            opponents.remove(index - 1);
        } else
        {
            JOptionPane.showMessageDialog(null, "Sorry, but you lost all your money! Good luck next time!");
        }
    }

    @Override
    public void updatePlayersCash(List<Player> players)
    {
        for (OpponentModule opponentModule : opponents)
        {
            opponentModule.setCash(players.get(opponents.indexOf(opponentModule) + 1).getCash());
        }
    }

    @Override
    public void showWinnerAndHisPrize(int playersNumber, int wonAmount)
    {
        super.showWinnerAndHisPrize(playersNumber, wonAmount);
        if (playersNumber != 0)
        {
            opponents.get(playersNumber - 1).showWin();
        } else
        {
            betLabel.setText("WIN!");
        }
    }

    @Override
    public void prepareForGame(List<Player> players)
    {
        super.prepareForGame(players);
        updatePlayersCash(players);
    }
}
