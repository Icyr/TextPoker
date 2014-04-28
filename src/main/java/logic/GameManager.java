package logic;

import entities.Combination;
import entities.Player;
import entities.Card;
import entities.Table;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    public static List<Player> getWinners(List<Player> players, Table table)
    {
        List<List<Card>> playersCards = Utils.unitePlayersAndTableCards(players, table.getCardsOnTable());
        int[] playersPowers = new int[players.size()];
        int maxPower = 0;
        for (int i = 0; i < players.size(); i++)
        {
            if (!players.get(i).isFolded())
            {
                playersPowers[i] = CombinationAnalyzer.analyzePower(playersCards.get(i));
                if (playersPowers[i] > maxPower) maxPower = playersPowers[i];
            } else
            {
                playersPowers[i] = 0;
            }
        }

        List<Player> challengers = new ArrayList<Player>();
        for (int i = 0; i < players.size(); i++)
        {
            if (maxPower == playersPowers[i])
            {
                challengers.add(players.get(i));
            }
        }
        List<Player> winners = new ArrayList<Player>();
        if (challengers.size() > 1)
        {
            winners = resolveConflict(challengers, table.getCardsOnTable());
        } else if (challengers.size() == 1)
        {
            winners = challengers;
        }
        return winners;
    }

    public static List<Player> resolveConflict(List<Player> challengers, List<Card> cardsOnTable)
    {
        List<Player> winners = new ArrayList<Player>();
        List<List<Card>> winnersCards = Utils.unitePlayersAndTableCards(challengers, cardsOnTable);
        int conflictPower = CombinationAnalyzer.analyzePower(winnersCards.get(0));
        Combination conflictCombination = Combination.getCombinationByPower(CombinationAnalyzer.analyzePower(winnersCards.get(0)));
        switch (conflictCombination)
        {
            case ROYAL_FLUSH:
                winners = challengers;
                break;
            case STRAIGHT_FLUSH:
                winners = challengers;
                break;
            case QUADS:
                winners = resolveQuadsConflict(challengers, winnersCards, conflictPower);
                break;
            case FULL_HOUSE:
                winners = resolveFullHouseConflict(challengers, winnersCards, conflictPower);
                break;
            case FLUSH:
                winners = resolveFlushConflict(challengers, winnersCards);
                break;
            case STRAIGHT:
                winners = challengers;
                break;
            case SET:
                winners = resolveSetConflict(challengers, winnersCards, conflictPower);
                break;
            case TWO_PAIRS:
                winners = resolvePairsConflict(challengers, winnersCards, conflictPower);
                break;
            case PAIR:
                winners = resolvePairConflict(challengers, winnersCards, conflictPower);
                break;
            case KICKER:
                winners = resolveKickerConflict(challengers, winnersCards, conflictPower);
                break;
        }
        return winners;
    }

    private static List<Player> resolveKickerConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners;
        List<List<Card>> cardsWithoutKicker = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            cardsWithoutKicker.add(Utils.removeCardsWithPreferredNominal(conflictPower, cards));
        }
        cardsWithoutKicker = analyzeCard(0, cardsWithoutKicker);
        if (cardsWithoutKicker.size() > 1)
        {
            cardsWithoutKicker = analyzeCard(1, cardsWithoutKicker);
            if (cardsWithoutKicker.size() > 1)
            {
                cardsWithoutKicker = analyzeCard(2, cardsWithoutKicker);
                if (cardsWithoutKicker.size() > 1)
                {
                    cardsWithoutKicker = analyzeCard(3, cardsWithoutKicker);
                }
            }
        }
        winners = Utils.getPlayersByCards(cardsWithoutKicker, challengers);
        return winners;
    }

    private static List<Player> resolvePairConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners;
        List<List<Card>> withoutPair = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            withoutPair.add(Utils.removeCardsWithPreferredNominal(conflictPower - 13, cards));
        }
        withoutPair = analyzeCard(0, withoutPair);
        if (withoutPair.size() > 1)
        {
            withoutPair = analyzeCard(1, withoutPair);
            if (withoutPair.size() > 1)
            {
                withoutPair = analyzeCard(2, withoutPair);
            }
        }
        winners = Utils.getPlayersByCards(withoutPair, challengers);
        return winners;
    }

    private static List<Player> resolvePairsConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners;
        int[] secondPairs = new int[winnersCards.size()];
        List<List<Card>> withoutMaxPair = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            withoutMaxPair.add(Utils.removeCardsWithPreferredNominal(conflictPower - 26, cards));
        }
        int maxPairNominal = 0;
        for (int i = 0; i < withoutMaxPair.size(); i++)
        {
            List<Card> curCards = withoutMaxPair.get(i);
            secondPairs[i] = CombinationAnalyzer.getAllPairs(curCards)[0];
            if (secondPairs[i] > maxPairNominal)
                maxPairNominal = secondPairs[i];
        }
        List<List<Card>> cardsWithMaxSecondPair = new ArrayList<List<Card>>();
        for (int i = 0; i < secondPairs.length; i++)
        {
            if (secondPairs[i] == maxPairNominal) cardsWithMaxSecondPair.add(withoutMaxPair.get(i));
        }
        if (cardsWithMaxSecondPair.size() > 1)
        {
            List<List<Card>> withoutPairs = new ArrayList<List<Card>>();
            for (List<Card> cards : cardsWithMaxSecondPair)
            {
                withoutPairs.add(Utils.removeCardsWithPreferredNominal(maxPairNominal, cards));
            }
            winners = Utils.getPlayersByCards(analyzeCard(0, withoutPairs), challengers);
        } else
        {
            winners = Utils.getPlayersByCards(cardsWithMaxSecondPair, challengers);
        }
        return winners;
    }

    private static List<Player> resolveSetConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners;
        List<List<Card>> withoutSet = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            withoutSet.add(Utils.removeCardsWithPreferredNominal(conflictPower - 39, cards));
        }
        withoutSet = analyzeCard(0, withoutSet);
        if (withoutSet.size() > 0)
        {
            withoutSet = analyzeCard(1, withoutSet);
        }
        winners = Utils.getPlayersByCards(withoutSet, challengers);
        return winners;
    }

    private static List<Player> resolveFlushConflict(List<Player> challengers, List<List<Card>> winnersCards)
    {
        List<Player> winners;
        List<List<Card>> winnersCardsWithoutExtra = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            if (Utils.getSameColorCount("H", cards) > 4)
            {
                List<Card> temp = Utils.getCardsWithPreferredColor("H", cards);
                temp.remove(0);
                winnersCardsWithoutExtra.add(temp);
            }
            if (Utils.getSameColorCount("D", cards) > 4)
            {
                List<Card> temp = Utils.getCardsWithPreferredColor("D", cards);
                temp.remove(0);
                winnersCardsWithoutExtra.add(temp);
            }
            if (Utils.getSameColorCount("S", cards) > 4)
            {
                List<Card> temp = Utils.getCardsWithPreferredColor("S", cards);
                temp.remove(0);
                winnersCardsWithoutExtra.add(temp);
            }
            if (Utils.getSameColorCount("C", cards) > 4)
            {
                List<Card> temp = Utils.getCardsWithPreferredColor("C", cards);
                temp.remove(0);
                winnersCardsWithoutExtra.add(temp);
            }
        }
        //second card
        winnersCardsWithoutExtra = analyzeCard(0, winnersCardsWithoutExtra);
        //third card
        if (winnersCardsWithoutExtra.size() > 1)
        {
            winnersCardsWithoutExtra = analyzeCard(1, winnersCardsWithoutExtra);
            //forth card
            if (winnersCardsWithoutExtra.size() > 1)
            {
                winnersCardsWithoutExtra = analyzeCard(2, winnersCardsWithoutExtra);
                //fifth card
                if (winnersCardsWithoutExtra.size() > 1)
                    winnersCardsWithoutExtra = analyzeCard(3, winnersCardsWithoutExtra);
            }
        }
        winners = Utils.getPlayersByCards(winnersCardsWithoutExtra, challengers);
        return winners;
    }

    private static List<Player> resolveFullHouseConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners = new ArrayList<Player>();
        int[] pairNominals = new int[winnersCards.size()];
        List<Card> withoutSet;
        for (List<Card> cards : winnersCards)
        {
            withoutSet = Utils.removeCardsWithPreferredNominal(conflictPower - 79, cards);
            pairNominals[winnersCards.indexOf(cards)] = CombinationAnalyzer.getAllPairs(withoutSet)[0];
        }
        int maxPair = 0;
        for (int pair : pairNominals)
        {
            if (pair > maxPair) maxPair = pair;
        }
        for (int i = 0; i < pairNominals.length; i++)
        {
            if (pairNominals[i] == maxPair) winners.add(challengers.get(i));
        }
        return winners;
    }

    private static List<Player> resolveQuadsConflict(List<Player> challengers, List<List<Card>> winnersCards, int conflictPower)
    {
        List<Player> winners = new ArrayList<Player>();
        int maxKicker = 0;
        List<Card> withoutQuads;
        List<List<Card>> winnersCardsWithoutQuads = new ArrayList<List<Card>>();
        for (List<Card> cards : winnersCards)
        {
            withoutQuads = Utils.removeCardsWithPreferredNominal(conflictPower - 92, cards);
            if (withoutQuads.get(0).getNominal() > maxKicker) maxKicker = withoutQuads.get(0).getNominal();
            winnersCardsWithoutQuads.add(withoutQuads);
        }
        for (List<Card> cards : winnersCardsWithoutQuads)
        {
            if (cards.get(0).getNominal() == maxKicker)
            {
                List<List<Card>> temp = new ArrayList<List<Card>>();
                temp.add(cards);
                winners.add(Utils.getPlayersByCards(temp, challengers).get(0));
            }
        }
        return winners;
    }


    private static List<List<Card>> analyzeCard(int index, List<List<Card>> players)
    {
        int maxNominal = 0;
        for (List<Card> cards : players)
        {
            if (cards.get(index).getNominal() > maxNominal) maxNominal = cards.get(index).getNominal();
        }
        List<List<Card>> afterCardAnalyze = new ArrayList<List<Card>>();
        for (List<Card> cards : players)
        {
            if (cards.get(index).getNominal() == maxNominal) afterCardAnalyze.add(cards);
        }
        return afterCardAnalyze;
    }
}
