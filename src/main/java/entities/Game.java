package entities;

import entities.players.Player;
import gui.TextualInterface;
import logic.GameManager;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Dealer dealer;
    private Table table;
    private int button;

    private List<Player> players;

    private int bank;
    private int maxBet;
    private int blindSize;

    private TextualInterface gui;

    public Game(int blindSize, TextualInterface gameGUI)
    {
        this.gui = gameGUI;
        players = new ArrayList<Player>();
        dealer = new Dealer();
        this.blindSize = blindSize;
        button = 0;
        gui.setBankLabel(0);
        gui.setBetLabel(0);
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void addPlayers(List<Player> newPlayers)
    {
        players.addAll(newPlayers);
    }

    public void play()
    {
        while (players.size() > 1)
        {
            prepareForRound();
            //first circle
            int underTheGun = calculateUnderTheGun(players.size(), button);
            doTurns(players, table, underTheGun);
            if (getNotFoldedPlayersCount() > 1)
            {
                table.flop();
                gui.printlnText("Flop:");
                gui.printlnText(table.tableCardsToString());
                //second circle
                doTurns(players, table, underTheGun);
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.turn();
                gui.printlnText("Turn:");
                gui.printlnText(table.tableCardsToString());
                //third circle
                doTurns(players, table, underTheGun);
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.river();
                gui.printlnText("River:");
                gui.printlnText(table.tableCardsToString());
                //forth circle
                doTurns(players, table, underTheGun);
            }
            //get winners
            distributeWonMoney(GameManager.getWinners(players, table));
            endRound();
        }
        gui.printlnText("We have got a winner! " + players.get(0).getId() + " player won!");
    }

    private void endRound()
    {
        removeBankruptPlayers();
        discardHands();
        gui.printlnText("Players' money:");
        for (Player player : players)
        {
            gui.printlnText(player.getCash() + " ");
        }
    }

    private void prepareForRound()
    {
        dealer = new Dealer();
        table = new Table(dealer);
        moveButton();
        betBlinds();
        deal();
    }

    public void distributeWonMoney(List<Player> winners)
    {
        for (Player winner : winners)
        {
            if (!winner.isAllIn())
            {
                winner.addToCash(bank / winners.size());
                gui.printlnText("Player " + players.indexOf(winner) + " won " + bank / winners.size());
            } else
            {
                int wonAmount = calculateAllInWinAmount(winner, players) / winners.size();
                if (bank > wonAmount)
                {
                    winner.addToCash(wonAmount);
                    gui.printlnText("Player " + players.indexOf(winner) + " won " + wonAmount);
                    bank -= wonAmount;
                    gui.setBankLabel(bank);
                } else
                {
                    winner.addToCash(bank);
                    gui.printlnText("Player " + players.indexOf(winner) + " won " + bank);
                    bank = 0;
                    gui.setBankLabel(0);
                }
                if (bank > 0)
                {
                    List<Player> newWinners = new ArrayList<Player>();
                    newWinners.addAll(winners);
                    newWinners.remove(winner);
                    if (newWinners.size() > 0) distributeWonMoney(newWinners);
                    if (newWinners.size() == 0)
                    {
                        List<Player> playersWithoutWinner = new ArrayList<Player>();
                        playersWithoutWinner.addAll(players);
                        playersWithoutWinner.remove(winner);
                        List<Player> nextWinners = GameManager.getWinners(playersWithoutWinner, table);
                        distributeWonMoney(nextWinners);
                    }
                }
            }
            if (!didOtherPlayersFold(winner))
            {
                gui.printlnText("Combination: " + winner.getCurrentCombination(table).toString());
            }
        }
        zeroBets();
    }

    public void removeBankruptPlayers()
    {
        List<Player> playersInGame = new ArrayList<Player>();
        for (int i = 0; i < players.size(); i++)
        {
            Player curPlayer = players.get(i);
            if (curPlayer.getCash() > 0)
            {
                playersInGame.add(curPlayer);

            } else
            {
                gui.printlnText("Player " + i + " has lost all of his money!");
                if (button == players.size() - 1) button--;
            }
        }
        players = playersInGame;
    }

    public int calculateAllInWinAmount(Player winner, List<Player> players)
    {
        int wonMoney = 0;
        for (Player player : players)
        {
            if (!player.equals(winner) && player.getCurrentBet() <= winner.getCurrentBet())
            {
                wonMoney += player.getCurrentBet();
            } else
            {
                wonMoney += winner.getCurrentBet();
            }
        }
        winner.setCurrentBet(0);
        return wonMoney;
    }

    public void deal()
    {
        gui.printlnText("Dealing cards..");
        for (Player player : players)
        {
            player.setHand(new Hand(dealer.getCards(2)));
            //gui.printlnText(player.playersCardsToString());
        }
    }

    public void moveButton()
    {
        button++;
        if (button == players.size())
        {
            button = 0;
        }
        gui.printlnText("Moved button to " + button);
    }

    private void revertBlinds()
    {
        for (Player player : players)
        {
            if (player.getCurrentBet() > 0)
            {
                player.addToCash(player.getCurrentBet());
                player.setCurrentBet(0);
            }
        }
    }

    public void betBlinds()
    {
        maxBet = blindSize * 2;
        bank = blindSize * 3;
        gui.setBankLabel(blindSize * 3);
        int playerCount = players.size();
        if (button < playerCount - 2)
        {
            try
            {
                players.get(button + 1).addToCurrentBet(blindSize);
                players.get(button + 2).addToCurrentBet(blindSize * 2);
                gui.printlnText((button + 1) + " player bet small blind: " + blindSize);
                gui.printlnText((button + 2) + " player bet big blind: " + blindSize * 2);
            } catch (BankruptException e)
            {

                removeBankruptPlayers();
                revertBlinds();
                betBlinds();
            }
        }
        if (button == playerCount - 2)
        {
            try
            {
                players.get(button + 1).addToCurrentBet(blindSize);
                players.get(0).addToCurrentBet(blindSize * 2);
                gui.printlnText((button + 1) + " player bet small blind: " + blindSize);
                gui.printlnText("0 player bet big blind: " + blindSize * 2);
            } catch (BankruptException e)
            {
                removeBankruptPlayers();
                betBlinds();
            }
        }
        if (button == playerCount - 1)
        {
            try
            {
                players.get(0).addToCurrentBet(blindSize);
                players.get(1).addToCurrentBet(blindSize * 2);
                gui.printlnText("0 player bet small blind: " + blindSize);
                gui.printlnText("1 player bet big blind: " + blindSize * 2);
            } catch (BankruptException e)
            {
                removeBankruptPlayers();
                betBlinds();
            }
        }

    }

    public void zeroBets()
    {
        maxBet = 0;
        bank = 0;
        gui.setBankLabel(0);
        for (Player player : players)
        {
            player.setCurrentBet(0);
        }
    }

    public void discardHands()
    {
        for (Player player : players)
        {
            player.setHand(null);
            player.setFolded(false);
            player.setAllIn(false);
        }
    }

    public void setBank(int amount)
    {
        this.bank = amount;
        gui.setBankLabel(amount);
    }


    public boolean circleEnded()
    {
        for (Player player : players)
        {
            if (!player.isAllIn() && player.getCurrentBet() != maxBet && !player.isFolded()) return false;
        }
        return true;
    }

    public void doTurns(List<Player> players, Table table, int underTheGun)
    {
        doOneCircle(players, table, underTheGun, false);
        while (!circleEnded())
        {
            doOneCircle(players, table, underTheGun, false);
        }
    }

    private void doOneCircle(List<Player> players, Table table, int underTheGun, boolean raiseCircle)
    {
        int turnsCount = players.size();
        if (raiseCircle)
        {
            turnsCount--;
        }
        boolean raiseBoolean = false;
        int nextPlayerPosition = 0;
        for (int i = 0; i < turnsCount; i++)
        {
            Player curPlayer = players.get(underTheGun);
            if (didOtherPlayersFold(curPlayer)) break;
            else
            {
                if (!curPlayer.isFolded() && !curPlayer.isAllIn())
                {
                    raiseBoolean = playersTurn(curPlayer, table);
                }
            }
            if (raiseBoolean)
            {
                nextPlayerPosition = players.indexOf(curPlayer) + 1;
                if (nextPlayerPosition == players.size()) nextPlayerPosition = 0;
                break;
            } else
            {
                underTheGun++;
                if (underTheGun == players.size()) underTheGun = 0;
            }
        }
        if (raiseBoolean)
        {
            doOneCircle(players, table, nextPlayerPosition, true);
        }

    }

    public int calculateUnderTheGun(int playersCount, int button)
    {
        int underTheGun = 0;
        if (playersCount > 2)
        {
            underTheGun = button + 3;
            if (underTheGun >= playersCount)
            {
                underTheGun = underTheGun - playersCount;
            }

        } else if (playersCount == 2)
        {
            underTheGun = button + 1;
            if (underTheGun >= playersCount)
            {
                underTheGun = underTheGun - playersCount;
            }
        }
        return underTheGun;
    }

    public boolean didOtherPlayersFold(Player curPlayer)
    {
        int foldedCount = 0;
        for (Player player : players)
        {
            if (!player.equals(curPlayer))
            {
                if (player.isFolded()) foldedCount++;
            }
        }
        return foldedCount == players.size() - 1;
    }

    //it works wrong but when it works right everything else is working wrong lol
    public int getActivePlayersCount()
    {
        int activeCount = 0;
        for (Player player : players)
        {
            if (!player.isFolded() && !player.isAllIn()) activeCount++;
        }
        return activeCount;
    }

    public int getNotFoldedPlayersCount()
    {
        int activeCount = 0;
        for (Player player : players)
        {
            if (!player.isFolded()) activeCount++;
        }
        return activeCount;
    }

    public boolean playersTurn(Player player, Table table)
    {
        boolean wasRaised = false;
        int callValue = maxBet - player.getCurrentBet();
        gui.setCallAmount(callValue);
        String decision = player.makeDecision(player.getHand(), table, bank, callValue, blindSize * 2, players.size());
        if (decision.equals("fold"))
        {
            if (callValue == 0)
            {
                gui.printlnText(players.indexOf(player) + " player checked");
            } else
            {
                player.setFolded(true);
                gui.printlnText(players.indexOf(player) + " player folded");
            }
        }
        if (decision.equals("call"))
        {
            if (callValue > 0)
            {
                if (callValue < player.getCash())
                {
                    player.unsafeAddToCurrentBet(callValue);
                    bank += callValue;
                    gui.setBankLabel(bank);
                    gui.printlnText(players.indexOf(player) + " player called " + callValue);
                } else
                {
                    bank = bank + player.getCash();
                    gui.setBankLabel(bank);
                    gui.printlnText(players.indexOf(player) + " player called " + player.getCash() + ". ALL IN!");
                    player.unsafeAddToCurrentBet(player.getCash());
                    player.setAllIn(true);
                }
            } else
            {
                gui.printlnText(players.indexOf(player) + " player checked");
            }
        }
        if (decision.contains("raise"))
        {
            int raiseAmount = Integer.parseInt(decision.substring(decision.indexOf(" ") + 1, decision.length()));
            if (raiseAmount + callValue < player.getCash())
            {
                player.unsafeAddToCurrentBet(callValue);
                player.unsafeAddToCurrentBet(raiseAmount);
                maxBet = player.getCurrentBet();
                bank = bank + raiseAmount + callValue;
                gui.setBankLabel(bank);
                gui.printlnText(players.indexOf(player) + " player raised for " + raiseAmount);
                wasRaised = true;
            } else
            {
                bank = bank + player.getCash();
                gui.setBankLabel(bank);
                gui.printlnText(players.indexOf(player) + " player raised for " + player.getCash() + ". ALL IN!");
                player.unsafeAddToCurrentBet(player.getCash());
                maxBet = player.getCurrentBet();
                player.setAllIn(true);
                wasRaised = true;
            }
        }
        return wasRaised;
    }
}
