package gui.view;

import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameView
{
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    private JFrame frame;
    private JPanel panel;
    private JPanel gameFieldPanel;

    private LogView logView;
    private DecisionModuleView decisionModuleView;
    private List<OpponentView> opponentViews;
    private TableView tableView;
    private PlayerView playerView;

    public GameView(LogView logView, DecisionModuleView decisionModuleView, List<OpponentView> opponentViews, TableView tableView, PlayerView playerView)
    {
        this.logView = logView;
        this.decisionModuleView = decisionModuleView;
        this.opponentViews = opponentViews;
        this.tableView = tableView;
        this.playerView = playerView;
    }

    public void initialize()
    {
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

    private void initializeComponents()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(null);
    }

    private void addElementsToPanel()
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

    public void removePlayer(int index)
    {
        opponentViews.get(index - 1).dispose();
        opponentViews.remove(index - 1);
        panel.repaint();
        layoutOpponents();
    }
}
