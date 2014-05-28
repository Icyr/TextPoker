package logic;

import entities.combinations.*;
import entities.players.Player;
import entities.Card;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    public static List<Player> getWinners(List<Player> players, List<Card> tableCards)
    {
        List<PlayersCardsAndCombination> pcacs = new ArrayList<PlayersCardsAndCombination>();
        for (Player player : players)
        {
            List<Card> playersCards = Utils.getPlayersCards(player, tableCards);
            pcacs.add(new PlayersCardsAndCombination(player, playersCards, CombinationAnalyzer.analyzeCombination(playersCards)));
        }
        Combination highestCombination = CombinationAnalyzer.getHighestCombination(pcacs);
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            if (pcac.getCombination().equals(highestCombination))
                winnersPcacs.add(pcac);
        }
        if (winnersPcacs.size() > 1)
        {
            winnersPcacs = resolveConflict(winnersPcacs);
        }
        List<Player> winners = new ArrayList<Player>();
        for (PlayersCardsAndCombination pcac : winnersPcacs)
        {
            winners.add(pcac.getPlayer());
        }
        return winners;
    }

    private static List<PlayersCardsAndCombination> resolveConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        Combination conflictCombination = pcacs.get(0).getCombination();
        //todo STRATEGY??
        if (conflictCombination instanceof StraightFlush)
        {
            winnersPcacs = pcacs;
        } else if (conflictCombination instanceof Quads)
        {
            winnersPcacs = resolveQuadsConflict(pcacs);
        } else if (conflictCombination instanceof FullHouse)
        {
            winnersPcacs = resolveFullHouseConflict(pcacs);
        } else if (conflictCombination instanceof Flush)
        {
            winnersPcacs = resolveFlushConflict(pcacs);
        } else if (conflictCombination instanceof Straight)
        {
            winnersPcacs = pcacs;
        } else if (conflictCombination instanceof Set)
        {
            winnersPcacs = resolveSetConflict(pcacs);
        } else if (conflictCombination instanceof TwoPairs)
        {
            winnersPcacs = resolveTwoPairsConflict(pcacs);
        } else if (conflictCombination instanceof Pair)
        {
            winnersPcacs = resolvePairConflict(pcacs);
        } else if (conflictCombination instanceof Kicker)
        {
            winnersPcacs = resolveKickerConflict(pcacs);
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveQuadsConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        int maxKicker = 0;
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            for (Card card : pcac.getCards())
            {
                if (card.getNominal() != ((Quads) pcac.getCombination()).getNominal()
                        && card.getNominal() > maxKicker)
                    maxKicker = card.getNominal();
            }
        }
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            for (Card card : pcac.getCards())
            {
                if (card.getNominal() == maxKicker)
                    winnersPcacs.add(pcac);
            }
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveFullHouseConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        int maxPair = 0;
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int thisPair = ((FullHouse) pcac.getCombination()).getPairNominal();
            if (thisPair > maxPair) maxPair = thisPair;
        }
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int thisPair = ((FullHouse) pcac.getCombination()).getPairNominal();
            if (thisPair == maxPair)
                winnersPcacs.add(pcac);
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveFlushConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            List<Card> flushCards = Utils.getCardsWithPreferredColor(((Flush) pcac.getCombination()).getColor(), pcac.getCards());
            flushCards.remove(0);
            PlayersCardsAndCombination somePcac = new PlayersCardsAndCombination(pcac.getPlayer(), flushCards, pcac.getCombination());
            winnersPcacs.add(somePcac);
        }
        //second card
        winnersPcacs = analyzeCard(0, winnersPcacs);
        //third card
        if (winnersPcacs.size() > 1)
        {
            winnersPcacs = analyzeCard(1, winnersPcacs);
            //forth card
            if (winnersPcacs.size() > 1)
            {
                winnersPcacs = analyzeCard(2, winnersPcacs);
                //fifth card
                if (winnersPcacs.size() > 1)
                    winnersPcacs = analyzeCard(3, winnersPcacs);
            }
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveSetConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            List<Card> cardsWithoutSet = Utils.removeCardsWithPreferredNominal(((Set) pcac.getCombination()).getNominal(), pcac.getCards());
            PlayersCardsAndCombination somePcac = new PlayersCardsAndCombination(pcac.getPlayer(), cardsWithoutSet, pcac.getCombination());
            winnersPcacs.add(somePcac);
        }
        winnersPcacs = analyzeCard(0, winnersPcacs);
        if (winnersPcacs.size() > 0)
        {
            winnersPcacs = analyzeCard(1, winnersPcacs);
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveTwoPairsConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        int maxSecondPair = 0;
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int thisSecondPair = ((TwoPairs) pcac.getCombination()).getLowerNominal();
            if (thisSecondPair > maxSecondPair) maxSecondPair = thisSecondPair;
        }
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int thisSecondPair = ((TwoPairs) pcac.getCombination()).getLowerNominal();
            if (thisSecondPair == maxSecondPair)
                winnersPcacs.add(pcac);
        }
        if (winnersPcacs.size() > 1)
        {
            List<PlayersCardsAndCombination> pcacsWithoutPairs = new ArrayList<PlayersCardsAndCombination>();
            for (PlayersCardsAndCombination pcac : winnersPcacs)
            {
                List<Card> cardsWithoutLowerPair = Utils.removeCardsWithPreferredNominal(((TwoPairs) pcac.getCombination()).getLowerNominal(), pcac.getCards());
                List<Card> cardsWithoutHigherPair = Utils.removeCardsWithPreferredNominal(((TwoPairs) pcac.getCombination()).getHigherNominal(), cardsWithoutLowerPair);
                PlayersCardsAndCombination somePcac = new PlayersCardsAndCombination(pcac.getPlayer(), cardsWithoutHigherPair, pcac.getCombination());
                pcacsWithoutPairs.add(somePcac);
            }
            winnersPcacs = analyzeCard(0, pcacsWithoutPairs);
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolvePairConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            List<Card> cardsWithoutPair = Utils.removeCardsWithPreferredNominal(((Pair) pcac.getCombination()).getNominal(), pcac.getCards());
            PlayersCardsAndCombination somePcac = new PlayersCardsAndCombination(pcac.getPlayer(), cardsWithoutPair, pcac.getCombination());
            winnersPcacs.add(somePcac);
        }
        winnersPcacs = analyzeCard(0, winnersPcacs);
        if (winnersPcacs.size() > 1)
        {
            winnersPcacs = analyzeCard(1, winnersPcacs);
            if (winnersPcacs.size() > 1)
            {
                winnersPcacs = analyzeCard(2, winnersPcacs);
            }
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> resolveKickerConflict(List<PlayersCardsAndCombination> pcacs)
    {
        List<PlayersCardsAndCombination> winnersPcacs = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            List<Card> cardsWithoutKicker = Utils.removeCardsWithPreferredNominal(((Kicker) pcac.getCombination()).getNominal(), pcac.getCards());
            PlayersCardsAndCombination somePcac = new PlayersCardsAndCombination(pcac.getPlayer(), cardsWithoutKicker, pcac.getCombination());
            winnersPcacs.add(somePcac);
        }
        winnersPcacs = analyzeCard(0, winnersPcacs);
        if (winnersPcacs.size() > 1)
        {
            winnersPcacs = analyzeCard(1, winnersPcacs);
            if (winnersPcacs.size() > 1)
            {
                winnersPcacs = analyzeCard(2, winnersPcacs);
                if (winnersPcacs.size() > 1)
                {
                    winnersPcacs = analyzeCard(3, winnersPcacs);
                }
            }
        }
        return winnersPcacs;
    }

    private static List<PlayersCardsAndCombination> analyzeCard(int index, List<PlayersCardsAndCombination> pcacs)
    {
        int maxNominal = 0;
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int currentNominal = pcac.getCards().get(index).getNominal();
            if (currentNominal > maxNominal) maxNominal = currentNominal;
        }
        List<PlayersCardsAndCombination> afterCardAnalyze = new ArrayList<PlayersCardsAndCombination>();
        for (PlayersCardsAndCombination pcac : pcacs)
        {
            int currentNominal = pcac.getCards().get(index).getNominal();
            if (currentNominal == maxNominal) afterCardAnalyze.add(pcac);
        }
        return afterCardAnalyze;
    }
}
