package view.Rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RateFriendView class.
 */
public class RateFriendViewTest {

    private RateFriendView rateFriendView;

    @BeforeEach
    public void setUp() {
        rateFriendView = new RateFriendView();
    }

    @Test
    public void testBackButtonExists() {
        // Arrange & Act
        JButton backButton = (JButton) TestUtils.getChildComponent(rateFriendView, JButton.class, 0);

        // Assert
        assertNotNull(backButton);
        assertEquals("Back to Dashboard", backButton.getText());
    }

    @Test
    public void testHeadingLabelExists() {
        // Arrange & Act
        JLabel headingLabel = (JLabel) TestUtils.getChildComponent(rateFriendView, JLabel.class, 0);

        // Assert
        assertNotNull(headingLabel);
        assertEquals("Rate a Friend", headingLabel.getText());
    }

    @Test
    public void testAddBackButtonListener() {
        // Arrange
        MockActionListener mockListener = new MockActionListener();

        // Act
        rateFriendView.addBackButtonListener(mockListener);
        JButton backButton = (JButton) TestUtils.getChildComponent(rateFriendView, JButton.class, 0);
        backButton.doClick();

        // Assert
        assertTrue(mockListener.isInvoked(), "Back button listener was not invoked.");
    }

    // Helper class for mocking an ActionListener
    private static class MockActionListener implements ActionListener {
        private boolean invoked = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            invoked = true;
        }

        public boolean isInvoked() {
            return invoked;
        }
    }

    // Utility class to retrieve child components
    private static class TestUtils {
        public static JComponent getChildComponent(Container parent, Class<?> clazz, int index) {
            int count = 0;
            for (Component component : parent.getComponents()) {
                if (clazz.isInstance(component)) {
                    if (count == index) {
                        return (JComponent) component;
                    }
                    count++;
                }
                if (component instanceof Container) {
                    JComponent child = getChildComponent((Container) component, clazz, index - count);
                    if (child != null) {
                        return child;
                    }
                }
            }
            return null;
        }
    }
}