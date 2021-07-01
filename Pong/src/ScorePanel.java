import javax.swing.*;
import java.awt.*;

/**
 * Pong
 * Author: Peter Mitchell (2021)
 *
 * ScorePanel class:
 * Score panel for showing the score for each player.
 */
public class ScorePanel extends JPanel {
    /**
     * Left player's score.
     */
    private JLabel leftPlayerScore;
    /**
     * Right player's score.
     */
    private JLabel rightPlayerScore;

    /**
     * Create labels for the scores and position them within the panel.
     *
     * @param gameWidth Width of the game space/
     */
    public ScorePanel(int gameWidth) {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(gameWidth,40));
        Font scoreFont = new Font("Arial", Font.BOLD, 35);

        leftPlayerScore = createLabel("0", scoreFont, Color.WHITE);
        rightPlayerScore = createLabel("0", scoreFont, Color.WHITE);
        JLabel rightPlayerLabel = createLabel(" : Player 2", scoreFont, Color.WHITE);
        JLabel leftPlayerLabel = createLabel("Player 1 : ", scoreFont, Color.WHITE);

        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.DARK_GRAY);
        leftPanel.add(leftPlayerLabel);
        leftPanel.add(leftPlayerScore);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.add(rightPlayerScore);
        rightPanel.add(rightPlayerLabel);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * Updates the text of the correct player's score based on isLeft.
     *
     * @param newScore The new score value to set the label to.
     * @param isLeft The player to apply the score change to.
     */
    public void setScore(int newScore, boolean isLeft) {
        if(isLeft) leftPlayerScore.setText(""+newScore);
        else rightPlayerScore.setText(""+newScore);
    }

    /**
     * Helper method to create a label with specified text, font, and colour.
     *
     * @param text The text on the label.
     * @param font The font to use for the label.
     * @param colour The colour to set the label to.
     * @return Newly created label with the specified properties.
     */
    private JLabel createLabel(String text, Font font, Color colour) {
        JLabel newLabel = new JLabel(text);
        newLabel.setFont(font);
        newLabel.setForeground(colour);
        return newLabel;
    }
}
