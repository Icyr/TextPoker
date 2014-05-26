package logic;

import entities.combinations.*;
import entities.players.Player;
import entities.Card;
import entities.Table;
import util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager
{
    public static List<Player> getWinners(List<Player> players, Table table)
    {
        List<Player> notFoldedPlayers = new ArrayList<Player>();
        for (Player player : players)
        {
            if (!player.isFolded()) notFoldedPlayers.add(player);
        }
        Map<List<Card>, Combination> playersCardsAndCombinations = new HashMap<List<Card>, Combination>();
        for (Player player : notFoldedPlayers)
        {
            List<Card> playersCards = Utils.getPlayersCards(player, table);
            playersCardsAndCombinations.put(playersCards, CombinationAnalyzer.analyzeCombination(playersCards));
        }
        Combination highestCombination = CombinationAnalyzer.getHighestCombination(playersCardsAndCombinations.values());
        List<Player> challengers = new ArrayList<Player>();
        for (List<Card> cards : playersCardsAndCombinations.keySet())
        {
            if (playersCardsAndCombinations.get(cards).equals(highestCombination))
                challengers.add(Utils.getPlayerByCards(players, table, cards));
        }
        return resolveConflict(challengers, table.getCardsOnTable());
    }

    public static List<Player> resolveConflict(List<Player> challengers, List<Card> cardsOnTable)
    {
        List<Player> winners = new ArrayList<Player>();
        List<List<Card>> challengersCards = Utils.unitePlayersAndTableCards(challengers, cardsOnTable);
        Combination conflictCombination = CombinationAnalyzer.analyzeCombination(challengersCards.get(0));
        if (conflictCombination instanceof StraightFlush)
        {
            winners = challengers;
        } else if (conflictCombination instanceof Quads)
        {
            winners = resolveQuadsConflict(challengers, challengersCards, conflictCombination);
        } else if (conflictCombination instanceof FullHouse)
        {
            winners = resolveFullHouseConflict(challengers, challengersCards, conflictCombination);
        } else if (conflictCombination instanceof Flush)
        {
            winners = resolveFlushConflict(challengers, challengersCards);
        } else if (conflictCombination instanceof Straight)
        {
            winners = challengers;
        } else if (conflictCombination instanceof Set)
        {
            winners = resolveSetConflict(challengers, challengersCards, conflictCombination.getPower());
        } else if (conflictCombination instanceof TwoPairs)
        {
            winners = resolvePairsConflict(challengers, challengersCards, conflictCombination.getPower());
        } else if (conflictCombination instanceof Pair)
        {
            winners = resolvePairConflict(challengers, challengersCards, conflictCombination.getPower());
        } else if (conflictCombination instanceof Kicker)
        {
            winners = resolveKickerConflict(challengers, challengersCards, conflictCombination.getPower());
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

    private static List<Player> resolveFullHouseConflict(List<Player> challengers, List<List<Card>> challengersCards, Combination conflictCombination)
    {
        List<Player> winners = new ArrayList<Player>();
        int[] pairNominals = new int[challengersCards.size()];
        List<Card> withoutSet;
        for (List<Card> cards : challengersCards)
        {
            withoutSet = Utils.removeCardsWithPreferredNominal(((FullHouse) conflictCombination).getSetNominal(), cards);
            pairNominals[challengersCards.indexOf(cards)] = CombinationAnalyzer.getAllPairs(withoutSet)[0];
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

    private static List<Player> resolveQuadsConflict(List<Player> challengers, List<List<Card>> challengersCards, Combination conflictCombination)
    {
        List<Player> winners = new ArrayList<Player>();
        List<Card> withoutQuads = new ArrayList<Card>();
        int maxKicker = 0;
        //removing quads
        for (List<Card> cards : challengersCards)
        {
            withoutQuads.add(Utils.removeCardsWithPreferredNominal(((Quads) conflictCombination).getNominal(), cards).get(0));
        }
        //searching for max kicker
        for (Card card : withoutQuads)
        {
            if (card.getNominal() > maxKicker) maxKicker = card.getNominal();
        }
        //searching for winners
        for (Card card : withoutQuads)
        {
            if (card.getNominal() == maxKicker)
            {
                if (Utils.getPlayerByCard(card, challengers) != null)
                    winners.add(Utils.getPlayerByCard(card, challengers));
            }
        }
        if (winners.size() == 0) return challengers;
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
