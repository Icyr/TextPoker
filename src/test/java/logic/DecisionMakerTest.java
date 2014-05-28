package logic;

import logic.DecisionMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class DecisionMakerTest
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                {new double[]{0.1, 0.0}, new double[]{0.5, 100.0, 50.0, 10.0, 200.0}, "raise 50"},
                {new double[]{0.1, 0.0}, new double[]{0.1, 100.0, 50.0, 10.0, 200.0}, "fold"},
                {new double[]{0.1, 0.0}, new double[]{0.4, 100.0, 50.0, 10.0, 200.0}, "call"},
                //bluff
                {new double[]{0.01, 1.0}, new double[]{0.5, 100.0, 50.0, 10.0, 200.0}, "raise 160"}};
        return Arrays.asList(data);
    }

    private double[] decisionMakerParameters;
    private double[] makeDecisionParameters;
    private String expectedDecision;

    public DecisionMakerTest(double[] decisionMakerParameters, double[] makeDecisionParameters, String expectedDecision)
    {
        this.decisionMakerParameters = decisionMakerParameters;
        this.makeDecisionParameters = makeDecisionParameters;
        this.expectedDecision = expectedDecision;
    }

    @Test
    public void testDecision()
    {
        DecisionMaker decisionMaker = new DecisionMaker(decisionMakerParameters[0], decisionMakerParameters[1]);
        String actualDecision = decisionMaker.makeDecision(makeDecisionParameters[0], (int) makeDecisionParameters[1], (int) makeDecisionParameters[2], (int) makeDecisionParameters[3], (int) makeDecisionParameters[4]);
        assertThat(actualDecision, equalTo(expectedDecision));
    }
}
