// package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * StatsScreen – displays per-user statistics with the player's avatar.
 *
 * Layout:
 *   - Purple background matching the app theme
 *   - Left half: JTabbedPane with one tab per level (Level 1, Level 2, Level 3)
 *     Each tab shows labelled stat rows (label + rounded grey value box)
 *   - Right half: player name heading + avatar image
 *   - Back button (curved arrow) in the top-right corner
 */
public class StatsScreen implements Screen {

    // Colours
    private static final Color BG_COLOR      = new Color(106, 69, 156);
    private static final Color FIELD_BG      = new Color(215, 215, 215);
    private static final Color TAB_SELECTED  = new Color(80, 50, 120);
    private static final Color TAB_BG        = new Color(90, 60, 130);

    // Data
    private final String playerName;
    private Image avatarImage;
    private JFrame statsFrame;

    private static final int LEVELS = 3;

    // Per-level stats (index 0 = Level 1, 1 = Level 2, 2 = Level 3)
    private int[]    highScore   = new int[LEVELS];
    private int[]    avgWpm      = new int[LEVELS];
    private int[]    peakWpm     = new int[LEVELS];
    private double[] accuracy    = new double[LEVELS];
    private int[]    errorCount  = new int[LEVELS];
    private String[] timePlayed  = {"0 minutes", "0 minutes", "0 minutes"};
    private int[]    wordsTyped  = new int[LEVELS];

    // Constructor
    public StatsScreen(String playerName) {
        this.playerName = playerName;
        loadAvatar("global/download.png");
    }

    // Per-level setters (level is 1-based)
    public void setHighScore(int level, int v)    { highScore[level - 1]  = v; }
    public void setAvgWpm(int level, int v)       { avgWpm[level - 1]     = v; }
    public void setPeakWpm(int level, int v)      { peakWpm[level - 1]    = v; }
    public void setAccuracy(int level, double v)  { accuracy[level - 1]   = v; }
    public void setErrorCount(int level, int v)   { errorCount[level - 1] = v; }
    public void setTimePlayed(int level, String v){ timePlayed[level - 1] = v; }
    public void setWordsTyped(int level, int v)   { wordsTyped[level - 1] = v; }

    // UI construction
    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.add(buildTopBar(), BorderLayout.NORTH);
        mainPanel.add(buildBody(),   BorderLayout.CENTER);
        statsFrame.setContentPane(mainPanel);
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setOpaque(false);
        bar.setBorder(BorderFactory.createEmptyBorder(4, 10, 0, 14));
        bar.add(buildBackButton(), BorderLayout.EAST);
        return bar;
    }

    /** Back button using the back.png image from the global folder. */
    private JButton buildBackButton() {
        ImageIcon icon = null;
        File imgFile = new File("global/back.png");
        if (imgFile.exists()) {
            Image img = new ImageIcon(imgFile.getAbsolutePath()).getImage()
                            .getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }
        JButton btn = new JButton(icon);
        btn.setPreferredSize(new Dimension(34, 34));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setToolTipText("Back");
        btn.setActionCommand("Back");
        btn.addActionListener(this);
        return btn;
    }

    /** Splits the screen: left = tabbed stats, right = avatar. */
    private JPanel buildBody() {
        JPanel body = new JPanel(new GridLayout(1, 2));
        body.setOpaque(false);
        body.add(buildTabbedStats());
        body.add(buildAvatarPanel());
        return body;
    }

    /** JTabbedPane with one tab per level. */
    private JTabbedPane buildTabbedStats() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(TAB_BG);
        tabs.setForeground(Color.WHITE);
        tabs.setFont(new Font("SansSerif", Font.BOLD, 13));
        tabs.setOpaque(true);

        for (int i = 0; i < LEVELS; i++) {
            tabs.addTab("Level " + (i + 1), buildStatsPanel(i));
        }
        return tabs;
    }

    /** Stat rows for one level (levelIndex is 0-based). */
    private JPanel buildStatsPanel(int levelIndex) {
        JPanel panel = new JPanel(null);
        panel.setBackground(TAB_SELECTED);

        String[][] rows = {
            { "High Score:",              String.valueOf(highScore[levelIndex])  },
            { "Average WPM:",             avgWpm[levelIndex] + " wpm"           },
            { "Peak WPM:",                peakWpm[levelIndex] + " wpm"          },
            { "Overall Accuracy:",        accuracy[levelIndex] + "%"            },
            { "Error Count:",             String.valueOf(errorCount[levelIndex]) },
            { "Total Time Played:",       timePlayed[levelIndex]                },
            { "Words Correctly Typed:",   String.valueOf(wordsTyped[levelIndex]) },
        };

        int startY = 14;
        int rowH   = 44;
        int lblW   = 155;
        int boxX   = lblW + 14;
        int boxW   = 150;
        int rowHt  = 28;

        for (int i = 0; i < rows.length; i++) {
            int y = startY + i * rowH;

            JLabel lbl = new JLabel(rows[i][0]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
            lbl.setHorizontalAlignment(SwingConstants.RIGHT);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            lbl.setBounds(4, y, lblW, rowHt);
            panel.add(lbl);

            JLabel val = makeValueBox(rows[i][1]);
            val.setBounds(boxX, y, boxW, rowHt);
            panel.add(val);
        }

        return panel;
    }

    /** A label that paints itself as a rounded grey box. */
    private JLabel makeValueBox(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(FIELD_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lbl.setForeground(Color.BLACK);
        lbl.setOpaque(false);
        return lbl;
    }

    // Right panel: avatar
    private JPanel buildAvatarPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (avatarImage != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    int targetH = (int) (getHeight() * 0.52);
                    int targetW = avatarImage.getWidth(null) * targetH
                                  / Math.max(avatarImage.getHeight(null), 1);
                    int x = (getWidth()  - targetW) / 2;
                    int y = (getHeight() - targetH) / 2 + 18;
                    g2.drawImage(avatarImage, x, y, targetW, targetH, null);
                    g2.dispose();
                }
            }
        };
        panel.setOpaque(false);

        JLabel nameLabel = new JLabel(playerName + "'s Statistics", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(28, 0, 0, 0));
        panel.add(nameLabel, BorderLayout.NORTH);

        return panel;
    }

    // Avatar loading
    private void loadAvatar(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            avatarImage = new ImageIcon(file.getAbsolutePath()).getImage();
        }
    }

// SCREEN INTERFACE METHODS -----------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            System.out.println("→ " + e.getActionCommand());
            this.moveToNextScreen(e.getActionCommand());
        }
    }

    @Override
    public void showScreen() {
        if (statsFrame == null) {
            statsFrame = new JFrame("Staying Alive - Stats");
            statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        statsFrame.setSize(NavigationControl.screenW, NavigationControl.screenH);
        statsFrame.getContentPane().removeAll();
        loadAvatar("global/download.png");
        buildUI();
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setVisible(true);
        NavigationControl.attachFontScaler(statsFrame);
    }

    @Override
    public void moveToNextScreen(String screenToMoveTo) {
        if (screenToMoveTo.equals("Back")) {
            NavigationControl.goBack();
        }
    }

    @Override
    public JFrame getFrame() { return this.statsFrame; }
}
