package gui.presenter;

import gui.view.DecisionModuleView;

public class DecisionModulePresenter
{
    private DecisionModuleView view;

    public DecisionModulePresenter(DecisionModuleView view)
    {
        this.view = view;
    }

    public String getDecision(int callValue, int cash, int tableCardCount, int bankSize)
    {
        view.enableDecisionButtons();
        view.setCallButtonValue(callValue);
        if (callValue * 2 <= cash)
        {
            view.setRaiseFieldValue(callValue * 2);
            view.configureRaiseSlider(callValue, cash);
            view.configureRaiseButtons(tableCardCount, bankSize);
        }
        else
        {
            view.disableRaiseButton();
        }
        String curDecision = view.readDecision();
        view.disableDecisionButtons();
        return curDecision;
    }
}
