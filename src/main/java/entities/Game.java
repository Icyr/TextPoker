package entities;

import logic.GameManager;

import java.util.ArrayList;
import java.util.List;

//todo if player has 10 money he will bid 20 blind. This causes little money losses.
public class Game
{
    private Dealer dealer;
    private Table table;

    private List<Player> players;

    private int button;

    private int bank;
    private int maxBet;
    private int blindSize;

    public Game(int blindSize)
    {
        players = new ArrayList<Player>();
        dealer = new Dealer();
        this.blindSize = blindSize;
        button = 0;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void addPlayers(List<Player> newPlayers)
    {
        players.addAll(newPlayers);
    }

    //test method
    public void setTable(Table talbe)
    {
        this.table = talbe;
    }

    public void play()
    {
        while (players.size() > 1)
        {
            prepareForRound();
            //first circle
            int underTheGun = calculateUnderTheGun(players.size(), button);
            doTurns(players, table, underTheGun);
            table.flop();
            System.out.println("Flop:");
            System.out.println(table.tableCardsToString());
            //second circle
            doTurns(players, table, underTheGun);
            table.turn();
            System.out.println("Turn:");
            System.out.println(table.tableCardsToString());
            //third circle
            doTurns(players, table, underTheGun);
            table.river();
            System.out.println("River:");
            System.out.println(table.tableCardsToString());
            //forth circle
            doTurns(players, table, underTheGun);
            //get winners
            distributeWonMoney(GameManager.getWinners(players, table));
            endRound();
        }
        System.out.println("We have got a winner! " + players.get(0).getId() + " player won!");
    }

    private void endRound()
    {
        removeBankruptPlayers();
        discardHands();
        System.out.println("Players' money:");
        for (Player player : players)
        {
            System.out.print(player.getCash() + " ");
        }
        System.out.println();
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
                System.out.println("Player " + players.indexOf(winner) + " won " + bank / winners.size());
            } else
            {
                int wonAmount = calculateAllInWinAmount(winner, players) / winners.size();
                if (bank > wonAmount)
                {
                    winner.addToCash(wonAmount);
                    System.out.println("Player " + players.indexOf(winner) + " won " + wonAmount);
                    bank -= wonAmount;
                } else
                {
                    winner.addToCash(bank);
                    System.out.println("Player " + players.indexOf(winner) + " won " + bank);
                    bank = 0;
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
        }
        zeroBets();
    }

    public void removeBankruptPlayers()
    {
        List<Player> playersInGame = new ArrayList<Player>();
        for (Player player : players)
        {
            if (player.getCash() > 0)
            {
                playersInGame.add(player);

            } else
            {
                System.out.println("Player " + players.indexOf(player) + " has lost all of his money!");
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
        System.out.println("Dealing cards..");
        for (Player player : players)
        {
            player.setHand(new Hand(dealer.getCards(2)));
            //System.out.println(player.playersCardsToString());
        }
    }

    public void moveButton()
    {
        button++;
        if (button == players.size())
        {
            button = 0;
        }
        System.out.println("Moved button to " + button);
    }

    public void betBlinds()
    {
        maxBet = blindSize * 2;
        bank = blindSize * 3;
        int playerCount = players.size();
        if (button < playerCount - 2)
        {
            players.get(button + 1).setCurrentBet(blindSize);
            System.out.println((button + 1) + " player bet small blind: " + blindSize);
            players.get(button + 2).setCurrentBet(blindSize * 2);
            System.out.println((button + 2) + " player bet big blind: " + blindSize * 2);
        }
        if (button == playerCount - 2)
        {
            players.get(button + 1).setCurrentBet(blindSize);
            System.out.println((button + 1) + " player bet small blind: " + blindSize);
            players.get(0).setCurrentBet(blindSize * 2);
            System.out.println("0 player bet big blind: " + blindSize * 2);
        }
        if (button == playerCount - 1)
        {
            players.get(0).setCurrentBet(blindSize);
            System.out.println("0 player bet small blind: " + blindSize);
            players.get(1).setCurrentBet(blindSize * 2);
            System.out.println("1 player bet big blind: " + blindSize * 2);
        }

    }

    public void zeroBets()
    {
        maxBet = 0;
        bank = 0;
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
        if (getActivePlayersCount() > 1)
        {
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

    public int getActivePlayersCount()
    {
        int activeCount = 0;
        for (Player player : players)
        {
            if (!player.isFolded() || !player.isAllIn()) activeCount++;
        }
        return activeCount;
    }

    //todo all in is not fully implemented and sometimes works incorrectly
    public boolean playersTurn(Player player, Table table)
    {
        boolean wasRaised = false;
        int callValue = maxBet - player.getCurrentBet();
        String decision = player.makeDecision(player.getHand(), table, bank, callValue, blindSize * 2, players.size());
        if (decision.equals("fold"))
        {
            if (callValue == 0)
            {
                System.out.println(players.indexOf(player) + " player checked");
            } else
            {
                player.setFolded(true);
                System.out.println(players.indexOf(player) + " player folded");
            }
        }
        if (decision.equals("call"))
        {
            if (callValue > 0)
            {
                if (callValue < player.getCash())
                {
                    player.addToCurrentBet(callValue);
                    bank += callValue;
                    System.out.println(players.indexOf(player) + " player called " + callValue);
                } else
                {
                    bank = bank + player.getCash();
                    System.out.println(players.indexOf(player) + " player called " + player.getCash() + ". ALL IN!");
                    player.addToCurrentBet(player.getCash());
                    player.setAllIn(true);
                }
            } else
            {
                System.out.println(players.indexOf(player) + " player checked");
            }
        }
        if (decision.contains("raise"))
        {
            int raiseAmount = Integer.parseInt(decision.substring(decision.indexOf(" ") + 1, decision.length()));
            if (raiseAmount + callValue < player.getCash())
            {
                player.addToCurrentBet(callValue);
                player.addToCurrentBet(raiseAmount);
                maxBet = player.getCurrentBet();
                bank = bank + raiseAmount + callValue;
                System.out.println(players.indexOf(player) + " player raised for " + (raiseAmount + callValue));
                wasRaised = true;
            } else
            {
                bank = bank + player.getCash();
                System.out.println(players.indexOf(player) + " player raised for " + player.getCash() + ". ALL IN!");
                player.addToCurrentBet(player.getCash());
                maxBet = player.getCurrentBet();
                player.setAllIn(true);
                wasRaised = true;
            }
        }
        return wasRaised;
    }
}
