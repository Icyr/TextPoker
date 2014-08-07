package gui.view;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;
import gui.Interface;
import gui.model.GameModel;
import gui.model.PlayerModel;
import gui.presenter.LogPresenter;
import gui.presenter.OpponentPresenter;
import gui.presenter.PlayerPresenter;
import gui.presenter.TablePresenter;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameView implements Interface
{
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    private JFrame frame;
    private JPanel panel;
    private LogView logView;
    private DecisionModuleView decisionModuleView;
    private JPanel gameFieldPanel;

    private GameModel gameModel;

    private List<OpponentView> opponentViews;
    private TableView tableView;
    private PlayerView playerView;

    private TablePresenter tablePresenter;
    private List<OpponentPresenter> opponentPresenters;
    private PlayerPresenter playerPresenter;
    private LogPresenter logPresenter;

    @SuppressWarnings("unused")
    public GameView()
    {
        createOpponents(4);
    }

    public GameView(int numberOfOpponents)
    {
        createOpponents(numberOfOpponents);
    }

    private void createOpponents(int count)
    {
        opponentViews = new ArrayList<OpponentView>(count);
        opponentPresenters = new ArrayList<OpponentPresenter>(count);
        for (int i = 0; i < count; i++)
        {
            OpponentView view = OpponentViewFactory.createOpponentView();
            opponentViews.add(view);
            opponentPresenters.add(new OpponentPresenter(view));
        }
    }

    public void initialize()
    {
        this.gameModel = new GameModel();
        initializeComponents();
        layout();
        addElementsToPanel();
    }

    private void layout()
    {
        frame.setTitle("Poker Graphical Interface");
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocation(screenSize.width / 2 - FRAME_WIDTH / 2, screenSize.height / 2 - FRAME_HEIGHT / 2);
        frame.setResizable(false);
        panel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameFieldPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT * 3 / 4 - 10);
        gameFieldPanel.setBackground(Constants.BACKGROUND_COLOR);
        logView.setBounds(5, FRAME_HEIGHT * 3 / 4 - 5, FRAME_WIDTH / 2, FRAME_HEIGHT / 4);
        decisionModuleView.setBounds(10 + FRAME_WIDTH / 2, FRAME_HEIGHT * 3 / 4 - 5, FRAME_WIDTH, FRAME_HEIGHT);
        layoutOpponents();

        playerView.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT / 4 * 3 - 50);
        tableView.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT * 5 / 16);
    }

    private void layoutOpponents()
    {
        switch (opponentViews.size())
        {
            case 1:
            {
                opponentViews.get(0).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                break;
            }
            case 2:
            {
                opponentViews.get(0).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(1).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                break;
            }
            case 3:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 4:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 5:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 6:
            {
                opponentViews.get(0).setBound(40, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(40, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(2).setBound(3 * FRAME_WIDTH / 8 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(5 * FRAME_WIDTH / 8 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(5).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 7:
            {
                opponentViews.get(0).setBound(40, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(40, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(2).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(5).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(6).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }

        }
    }

    protected void initializeComponents()
    {
        gameModel = new GameModel();
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);

        gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(null);

        playerView = new PlayerView();
        playerPresenter = new PlayerPresenter(playerView);

        tableView = new TableView();
        tablePresenter = new TablePresenter(tableView);

        logView = new LogView();
        logPresenter = new LogPresenter(logView);

        decisionModuleView = new DecisionModuleView();
    }

    protected void addElementsToPanel()
    {
        for (OpponentView opponent : opponentViews)
        {
            opponent.addToPanel(gameFieldPanel);
        }
        tableView.addToPanel(gameFieldPanel);
        playerView.addToPanel(gameFieldPanel);

        logView.addToPanel(panel);
        decisionModuleView.addToPanel(panel);
        panel.add(gameFieldPanel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                logView.printlnText("Flop: " + table.tableCardsToString());
                tablePresenter.flop(table);
                break;
            case 4:
                logView.printlnText("Turn: " + table.tableCardsToString());
                tablePresenter.turn(table);
                break;
            case 5:
                logView.printlnText("River: " + table.tableCardsToString());
                tablePresenter.river(table);
                break;
        }
    }

    @Override
    public void prepareForRound()
    {
        setBetAmount(0);
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setBet(0);
        }
        playerPresenter.discardCards();
        tablePresenter.discardTableCards();

    }

    @Override
    public void moveButton(int button)
    {
        logPresenter.moveButton(button);
    }

    @Override
    public void deal(List<Player> players)
    {
        logPresenter.deal();
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.showCardBacks();
        }
        playerPresenter.setHandCards(players.get(0).getHand());
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        logView.printlnText(indexOfPlayer + " player folded");
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).fold();
        }
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player called " + callValue;
        if (isAllIn) message += " . ALL IN!";
        logView.printlnText(message);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(callValue);
        }
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player raised " + raiseValue;
        if (isAllIn) message += " . ALL IN!";
        logView.printlnText(message);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(raiseValue);
        } else
        {
            playerPresenter.setBet(raiseValue);
        }
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        logView.printlnText(firstPlayerNumber + " player bet small blind: " + blindSize);
        logView.printlnText(secondPlayerNumber + " player bet big blind: " + blindSize * 2);
        if (firstPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize);
            opponentPresenters.get(0).setBet(blindSize * 2);
        } else if (secondPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize * 2);
            opponentPresenters.get(opponentPresenters.size() - 1).setBet(blindSize);
        } else
        {
            opponentPresenters.get(firstPlayerNumber - 1).setBet(blindSize);
            opponentPresenters.get(secondPlayerNumber - 1).setBet(blindSize * 2);
        }
    }

    @Override
    public void check(int indexOfPlayer)
    {
        logView.printlnText(indexOfPlayer + " player checked");
    }

    @Override
    public void showPlayersHand(int index, Hand hand)
    {
        logView.printlnText("Player's " + index + " hand: " + hand.toString());
        if (index != 0)
        {
            opponentPresenters.get(index - 1).showCards(hand);
        }
    }

    @Override
    public void zeroBets()
    {
        playerPresenter.setBet(0);
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setBet(0);
        }
    }

    @Override
    public void displayRaiseError()
    {
        logView.printlnText("Invalid raise");
    }

    @Override
    public void removeBankruptPlayer(int index)
    {
        logView.printlnText("Player " + index + " has lost all of his money!");
        if (index != 0)
        {
            opponentPresenters.get(index - 1).bankrupt();
            opponentPresenters.remove(index - 1);
            opponentViews.get(index - 1).dispose();
            opponentViews.remove(index - 1);
            panel.repaint();
            layoutOpponents();
        } else
        {
            JOptionPane.showMessageDialog(null, "Sorry, but you lost all your money! Good luck next time!");
        }
    }

    @Override
    public void updatePlayersCash(List<Player> players)
    {
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setCash(players.get(opponentPresenters.indexOf(opponentPresenter) + 1).getCash());
        }
    }

    //todo: there should be a game model with players models which should updateModel as the game goes on. Now it is just a wrap.
    @Override
    public void showWinnerAndHisPrize(Player player, int playerIndex, int wonAmount)
    {
        PlayerModel model = new PlayerModel(player, playerIndex);
        logView.printlnText(model.getDescription() + " won " + wonAmount);
        if (playerIndex != 0)
        {
            opponentPresenters.get(playerIndex - 1).showWin();
        } else
        {
            playerPresenter.showWin();
        }
    }

    @Override
    public void prepareForGame(List<Player> players)
    {
        setBank(0);
        updatePlayersCash(players);
    }

    @Override
    public void setCash(int value)
    {
        playerPresenter.setCash(value);
    }

    @Override
    public void pause()
    {
        while (true) ;
    }

    @Override
    public void setBank(int value)
    {
        gameModel.setBankValue(value);
        tablePresenter.setBank(value);
    }

    //todo:this is something strange:refactor!!!
    @Override
    public void setBetAmount(int currentBet)
    {
        playerPresenter.setBet(currentBet);
    }

    @Override
    public void showWinnersCombination(Combination combination)
    {
        logView.printlnText("Winning combination: " + combination.toString());
    }

    //todo: bank value is temp resolution and hack. Game model will solve this problem.
    @Override
    public String getDecision(int callValue, int cash, int tableCardCount)
    {
        logView.printlnText("Your turn...");
        return decisionModuleView.getDecision(callValue, cash, tableCardCount, gameModel.getBankValue());
    }
}
