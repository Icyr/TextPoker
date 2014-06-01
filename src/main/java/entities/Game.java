package entities;

import entities.players.HumanPlayer;
import entities.players.Player;
import gui.Interface;
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

    private Interface gui;

    public Game(int blindSize, Interface gameGUI, int buttonPosition)
    {
        this.gui = gameGUI;
        players = new ArrayList<Player>();
        this.blindSize = blindSize;
        button = buttonPosition;
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
        gui.prepareForGame();
        while (players.size() > 1)
        {
            prepareForRound();
            //first circle
            int underTheGun = calculateUnderTheGun(players.size(), button);
            doTurns(players, table, underTheGun);
            if (getNotFoldedPlayersCount() > 1)
            {
                table.flop();
                gui.updateTable(table);
                //second circle
                doTurns(players, table, underTheGun);
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.turn();
                gui.updateTable(table);
                //third circle
                doTurns(players, table, underTheGun);
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.river();
                gui.updateTable(table);
                //forth circle
                doTurns(players, table, underTheGun);
            }
            //get winners
            distributeWonMoney(GameManager.getWinners(getUnfoldedPlayers(), table.getCardsOnTable()));
            endRound();
        }
        //gui.printlnText("We have got a winner! " + players.get(0).getId() + " player won!");
    }

    private List<Player> getUnfoldedPlayers()
    {
        List<Player> notFoldedPlayers = new ArrayList<Player>();
        for (Player player : players)
        {
            if (!player.isFolded()) notFoldedPlayers.add(player);
        }
        return notFoldedPlayers;
    }

    private void endRound()
    {
        removeBankruptPlayers();
        discardHands();
        gui.updatePlayersCash(players);
    }

    private void prepareForRound()
    {
        gui.prepareForRound();
        dealer = new Dealer();
        table = new Table(dealer);
        moveButton();
        betBlinds();
        deal();
    }

    private void distributeWonMoney(List<Player> winners)
    {
        for (Player winner : winners)
        {
            if (!winner.isAllIn())
            {
                winner.addToCash(bank / winners.size());
                gui.showWinnerAndHisPrize(players.indexOf(winner), bank / winners.size());
            } else
            {
                int wonAmount = calculateAllInWinAmount(winner, players) / winners.size();
                if (bank > wonAmount)
                {
                    winner.addToCash(wonAmount);
                    gui.showWinnerAndHisPrize(players.indexOf(winner), wonAmount);
                    bank -= wonAmount;
                    gui.setBank(bank);
                } else
                {
                    winner.addToCash(bank);
                    gui.showWinnerAndHisPrize(players.indexOf(winner), bank);
                    bank = 0;
                    gui.setBank(0);
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
                        playersWithoutWinner.addAll(getUnfoldedPlayers());
                        playersWithoutWinner.remove(winner);
                        List<Player> nextWinners = GameManager.getWinners(playersWithoutWinner, table.getCardsOnTable());
                        distributeWonMoney(nextWinners);
                    }
                }
            }
            if (!didOtherPlayersFold(winner))
            {
                gui.showWinnersCombination(winner.getCurrentCombination(table));
            }
        }
        zeroBets();
    }

    private void removeBankruptPlayers()
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
                gui.removeBankruptPlayer(i);
                if (button == players.size() - 1) button--;
                if (curPlayer instanceof HumanPlayer) gui.pause();
            }
        }
        players = playersInGame;
    }

    private int calculateAllInWinAmount(Player winner, List<Player> players)
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

    private void deal()
    {
        for (Player player : players)
        {
            player.setHand(new Hand(dealer.getCards(2)));
            //gui.printlnText(player.playersCardsToString());
        }
        gui.deal();
    }

    private void moveButton()
    {
        button++;
        if (button == players.size())
        {
            button = 0;
        }
        gui.moveButton(button);
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

    private void betBlinds()
    {
        maxBet = blindSize * 2;
        bank = blindSize * 3;
        gui.setBank(blindSize * 3);
        int playerCount = players.size();
        if (button < playerCount - 2)
        {
            try
            {
                players.get(button + 1).addToCurrentBet(blindSize);
                players.get(button + 2).addToCurrentBet(blindSize * 2);
                gui.betBlinds((button + 1), (button + 2), blindSize);
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
                gui.betBlinds((button + 1), 0, blindSize);
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
                gui.betBlinds(0, 1, blindSize);
            } catch (BankruptException e)
            {
                removeBankruptPlayers();
                betBlinds();
            }
        }
    }

    private void zeroBets()
    {
        maxBet = 0;
        bank = 0;
        gui.setBank(0);
        for (Player player : players)
        {
            player.setCurrentBet(0);
        }
    }

    private void discardHands()
    {
        for (Player player : players)
        {
            player.setHand(null);
            player.setFolded(false);
            player.setAllIn(false);
        }
    }

    private boolean circleEnded()
    {
        for (Player player : players)
        {
            if (!player.isAllIn() && player.getCurrentBet() != maxBet && !player.isFolded()) return false;
        }
        return true;
    }

    private void doTurns(List<Player> players, Table table, int underTheGun)
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

    private int calculateUnderTheGun(int playersCount, int button)
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

    private boolean didOtherPlayersFold(Player curPlayer)
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

    private int getNotFoldedPlayersCount()
    {
        int activeCount = 0;
        for (Player player : players)
        {
            if (!player.isFolded()) activeCount++;
        }
        return activeCount;
    }

    private boolean playersTurn(Player player, Table table)
    {
        boolean wasRaised = false;
        int callValue = maxBet - player.getCurrentBet();
        String decision = player.makeDecision(player.getHand(), table, bank, callValue, blindSize * 2, players.size());
        if (decision.equals("fold"))
        {
            if (callValue == 0)
            {
                gui.check(players.indexOf(player));
            } else
            {
                player.setFolded(true);
                gui.fold(players.indexOf(player));
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
                    gui.setBank(bank);
                    gui.call(players.indexOf(player), callValue, false);
                } else
                {
                    bank = bank + player.getCash();
                    gui.setBank(bank);
                    gui.call(players.indexOf(player), player.getCash(), true);
                    player.unsafeAddToCurrentBet(player.getCash());
                    player.setAllIn(true);
                }
            } else
            {
                gui.check(players.indexOf(player));
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
                gui.setBank(bank);
                gui.raise(players.indexOf(player), raiseAmount, false);
                wasRaised = true;
            } else
            {
                bank = bank + player.getCash();
                gui.setBank(bank);
                gui.raise(players.indexOf(player), player.getCash(), true);
                player.unsafeAddToCurrentBet(player.getCash());
                maxBet = player.getCurrentBet();
                player.setAllIn(true);
                wasRaised = true;
            }
        }
        return wasRaised;
    }
}
