import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper extends JFrame {
    private JMenuBar mb;
    private JMenu m1;
    private JMenuItem mi1, mi2;
    
    private JPanel northPanel, centerPanel;
    private JButton btnSmiley;
    
    private JButton[][] b = new JButton[10][10];
    
    private Icon sIcon;
    private Icon mIcon;
    
    public MineSweeper(){
        super("MineSweeper Game");
        
        createResources();
        initComponents();        
        assambleMenu();       
        assambleNorthPanel();        
        assambleCenterPanel();        
        createButtons();
        Game.getInstance().startGame();
        configureFrame();
        
        
        
        /*
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                int v = Game.getInstance().getCellValue(i,j);
                b[i][j].setText(String.valueOf(v));
                if (v < 0) {
                    b[i][j].setBackground(Color.RED);
                }
            }
        }
        **/
    }
    
    private void createResources(){         
        sIcon = new ImageIcon("s.png");
        mIcon = new ImageIcon("m.png");
    }
    
    private void initComponents(){
        mb = new JMenuBar();
        m1 = new JMenu("Option"); //resource bundle - vezi lectiile mai vechi
        mi1 = new JMenuItem("New");
        mi2 = new JMenuItem("Exit");
        northPanel = new JPanel();
        btnSmiley = new JButton(sIcon);
        
        centerPanel = new JPanel();
    }
    
    private void assambleMenu(){
        setJMenuBar(mb);
        mb.add(m1);
        m1.add(mi1);
        m1.add(mi2);
        mi1.addActionListener(e -> newGame());
        mi2.addActionListener(e -> System.exit(0));
    }
    
    private void assambleNorthPanel(){        
        northPanel.add(btnSmiley);
        add(northPanel, BorderLayout.NORTH);
        btnSmiley.addActionListener(e -> newGame());        
    }
    
    private void assambleCenterPanel(){
        centerPanel.setLayout(new GridLayout(10,10));
        add(centerPanel);
    }
    
    private void createButtons(){
        for(int i = 0; i < b.length; i++){
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = new JButton();
                b[i][j].addActionListener(this:: buttonPressed);
                centerPanel.add(b[i][j]);
            }
        }
    }
    
    private void configureFrame(){
        setSize(420, 520);
        setResizable(false);
        setLocationRelativeTo(null);        
    }
    
    private void enableAllButtons(boolean enable){
        for(int i = 0; i < b.length; i++){
            for (int j = 0; j < b[i].length; j++) {
                b[i][j].setEnabled(enable);
            }
        }
    }
    
    private void clearAllButtons(){
        for(int i = 0; i < b.length; i++){
            for (int j = 0; j < b[i].length; j++) {
                b[i][j].setIcon(null);
                b[i][j].setText(null);
            }
        }
    }
    
    private void showMineOnButtons(){
        for(int i = 0; i < b.length; i++){
            for (int j = 0; j < b[i].length; j++) {
                int v = Game.getInstance().getCellValue(i,j);
                if (v < 0) {
                    b[i][j].setIcon(mIcon);
                }
            }
        }
    }
    
    private void newGame(){
        enableAllButtons(true);
        clearAllButtons();
        Game.getInstance().startGame();
    }
    
    private void gameOver(){
        enableAllButtons(false);
        showMineOnButtons();
        JOptionPane.showMessageDialog(this, "Ooops! Game Over!");        
    }
    
    private void buttonPressed(ActionEvent ev){
        for(int i = 0; i < b.length; i++){
            for (int j = 0; j < b[i].length; j++) {
                if (ev.getSource() == b[i][j]) {
                    int v = Game.getInstance().getCellValue(i,j);
                    if (v < 0) {
                        gameOver();
                    }else{
                        b[i][j].setText(String.valueOf(v));
                        b[i][j].setEnabled(false); //dezactivam butonul
                    }
                }
            }
        }
    }
}