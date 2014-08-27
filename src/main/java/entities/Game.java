package entities;

import entities.players.HumanPlayer;
import entities.players.Player;
import gui.EndPoint;
import logic.ConflictResolver;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Dealer dealer;
    private Table table;
    private int button;
    private int underTheGun;

    private List<Player> players;

    private int bank;
    private int maxBet;
    private int blindSize;

    private EndPoint endPoint;

    public Game(int blindSize, EndPoint gameGUI, int buttonPosition)
    {
        this.endPoint = gameGUI;
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
        while (players.size() > 1)
        {
            prepareForRound();
            //first circle
            makeBets(players, table, underTheGun);
            if (getNotFoldedPlayersCount() > 1)
            {
                table.flop();
                endPoint.updateTable(table);
                //second circle
                if (getActivePlayerCount() > 1)
                {
                    makeBets(players, table, underTheGun);
                }
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.turn();
                endPoint.updateTable(table);
                //third circle
                if (getActivePlayerCount() > 1)
                {
                    makeBets(players, table, underTheGun);
                }
            }
            if (getNotFoldedPlayersCount() > 1)
            {
                table.river();
                endPoint.updateTable(table);
                //forth circle
                if (getActivePlayerCount() > 1)
                {
                    makeBets(players, table, underTheGun);
                }
            }
            //get winners
            List<Player> winners = ConflictResolver.getWinners(getNotFoldedPlayers(), table.getCardsOnTable());
            showWinnersCardsAndCombinations(winners);
            distributeWonMoney(winners);
            endRound();
        }
    }

    private void prepareForRound()
    {
        dealer = new Dealer();
        table = new Table(dealer);
        moveButton();
        deal();
        underTheGun = calculateUnderTheGun(players.size(), button);
        endPoint.prepareForRound(button, players);
        betBlinds();
    }

    private void moveButton()
    {
        button++;
        if (button == players.size())
        {
            button = 0;
        }
    }

    private void betBlinds()
    {
        maxBet = blindSize * 2;
        bank = blindSize * 3;
        int playerCount = players.size();
        if (button < playerCount - 2)
        {
            try
            {
                players.get(button + 1).addToCurrentBet(blindSize);
                players.get(button + 2).addToCurrentBet(blindSize * 2);
                endPoint.betBlinds((button + 1), (button + 2), blindSize);
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
                endPoint.betBlinds((button + 1), 0, blindSize);
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
                endPoint.betBlinds(0, 1, blindSize);
            } catch (BankruptException e)
            {
                removeBankruptPlayers();
                betBlinds();
            }
        }
    }

    private void deal()
    {
        for (Player player : players)
        {
            player.setHand(new Hand(dealer.getCards(2)));
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

    private void showWinnersCardsAndCombinations(List<Player> winners)
    {
        for (Player winner : winners)
        {
            if (!didOtherPlayersFold(winner))
            {
                endPoint.showWinnersCombination(winner.getCurrentCombination(table));
                for (Player player : getNotFoldedPlayers())
                {
                    endPoint.showPlayersHand(players.indexOf(player), player.getHand());
                }
            }
        }
    }

    private List<Player> getNotFoldedPlayers()
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
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        zeroBets();
        removeBankruptPlayers();
        discardHands();
        endPoint.updatePlayersCash(players);
    }

    private void distributeWonMoney(List<Player> winners)
    {
        for (Player winner : winners)
        {
            if (!winner.isAllIn())
            {
                winner.addToCash(bank / winners.size());
                endPoint.showWinnerAndHisPrize(winner, players.indexOf(winner), bank / winners.size());
            } else
            {
                int wonAmount = calculateAllInWinAmount(winner, players) / winners.size();
                if (bank > wonAmount)
                {
                    winner.addToCash(wonAmount);
                    endPoint.showWinnerAndHisPrize(winner, players.indexOf(winner), wonAmount);
                    bank -= wonAmount;
                    endPoint.setBank(bank);
                } else
                {
                    winner.addToCash(bank);
                    endPoint.showWinnerAndHisPrize(winner, players.indexOf(winner), bank);
                    bank = 0;
                    endPoint.setBank(0);
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
                        playersWithoutWinner.addAll(getNotFoldedPlayers());
                        playersWithoutWinner.remove(winner);
                        List<Player> nextWinners = ConflictResolver.getWinners(playersWithoutWinner, table.getCardsOnTable());
                        distributeWonMoney(nextWinners);
                    }
                }
            }
        }
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
                endPoint.removeBankruptPlayer(i);
                if (button == players.size() - 1) button--;
                if (curPlayer instanceof HumanPlayer) endPoint.pause();
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

    private void zeroBets()
    {
        maxBet = 0;
        bank = 0;
        endPoint.setBank(0);
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

    private void makeBets(List<Player> players, Table table, int underTheGun)
    {
        playOneCircle(players, table, underTheGun, false);
        while (!circleEnded())
        {
            playOneCircle(players, table, underTheGun, false);
        }
        endPoint.zeroBets();
        endPoint.setBank(bank);
    }

    private void playOneCircle(List<Player> players, Table table, int underTheGun, boolean raiseCircle)
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
            playOneCircle(players, table, nextPlayerPosition, true);
        }

    }

    private int getActivePlayerCount()
    {
        int activeCount = 0;
        for (Player player : players)
        {
            if (!player.isFolded() && !player.isAllIn()) activeCount++;
        }
        return activeCount;
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
                endPoint.check(players.indexOf(player));
            } else
            {
                player.setFolded(true);
                endPoint.fold(players.indexOf(player));
            }
        }
        if (decision.equals("call"))
        {
            makeCall(player, callValue);
        }
        if (decision.contains("raise"))
        {
            int raiseAmount = Utils.safeParseInt(decision.substring(decision.indexOf(" ") + 1, decision.length()));
            if (raiseAmount < 2 * callValue)
            {
                makeCall(player, callValue);
            } else if (raiseAmount < player.getCash())
            {
                //player.unsafeAddToCurrentBet(callValue);
                player.unsafeAddToCurrentBet(raiseAmount);
                maxBet = player.getCurrentBet();
                bank = bank + raiseAmount;
                endPoint.raise(players.indexOf(player), raiseAmount, false);
                wasRaised = true;
            } else
            {
                bank = bank + player.getCash();
                endPoint.raise(players.indexOf(player), player.getCash(), true);
                player.unsafeAddToCurrentBet(player.getCash());
                maxBet = player.getCurrentBet();
                player.setAllIn(true);
                wasRaised = true;
            }
        }
        endPoint.updatePlayersCash(players);
        return wasRaised;
    }

    private void makeCall(Player player, int callValue)
    {
        if (callValue > 0)
        {
            if (callValue < player.getCash())
            {
                player.unsafeAddToCurrentBet(callValue);
                bank += callValue;
                endPoint.call(players.indexOf(player), callValue, false);
            } else
            {
                bank = bank + player.getCash();
                endPoint.call(players.indexOf(player), player.getCash(), true);
                player.unsafeAddToCurrentBet(player.getCash());
                player.setAllIn(true);
            }
        } else
        {
            endPoint.check(players.indexOf(player));
        }
    }
}
