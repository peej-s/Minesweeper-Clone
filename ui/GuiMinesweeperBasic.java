package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.GamePiece;
import backend.Minesweeper;

@SuppressWarnings("serial")

public class GuiMinesweeperBasic extends JPanel {

    private int SIDE_X;
    private int SIDE_Y;
    private int GAP = 3;
    private Color BG = Color.BLACK;
    public int btnX = 50;
    public int btnY = 50;
    private Dimension BTN_PREF_SIZE = new Dimension(btnX, btnY);
    JButton[][] buttons = new JButton[SIDE_Y][SIDE_X];
    boolean gameStatus = true;
    
    private void setInts(Minesweeper game){
    	this.SIDE_X = game.columns;
    	this.SIDE_Y = game.rows;
    }
    
    private void makeMove(Minesweeper game){
    	GamePiece[][] surfaceGrid = game.getSurfaceGrid();
    	
    	removeAll();
    	revalidate();
    	
    	for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
            	if (surfaceGrid[j][i] == null){
            		add(buttons[i][j]);
            	}
            	else {
            		buttons[i][j] = new JButton();
            		buttons[i][j].setEnabled(false);
            		buttons[i][j].setText(surfaceGrid[j][i].toSymbol());
            		add(buttons[i][j]);
            	}
            }
    	}
    	gameStatus = game.getStatus();
    	// When gameStatus is false we should not be allowed to click anymore!
    }
    
    public GuiMinesweeperBasic(Minesweeper game) {
    	setInts(game);    	
        setBackground(BG);
        setLayout(new GridLayout(this.SIDE_Y, this.SIDE_X));
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        JButton[][] smallButtons = new JButton[SIDE_Y][SIDE_X];
        
        for (int i = 0; i < smallButtons.length; i++) {
            for (int j = 0; j < smallButtons[i].length; j++) {
            	int column = j;
            	int row = i;
            	
            	smallButtons[i][j] = new JButton();
            	smallButtons[i][j].setPreferredSize(BTN_PREF_SIZE);
            	smallButtons[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            JButton source = (JButton) e.getSource();
                            source.setEnabled(false);
                            game.makeMove(column, row);
                            makeMove(game);
                            // We want to depend on the backend not the frontend
                            // Should refresh the board on each click
                            // if clicked, should be a clicked button with the number
                    }
            });
                add(smallButtons[i][j]);
            }
        }
        this.buttons = smallButtons;
    }
    
    public static void main(String[] args) {
    	int columns, rows, bombs;
    	columns =  10;
    	rows = 10;
    	bombs = 10;
    	
    	Minesweeper game = new Minesweeper(columns, rows, bombs);
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					GuiMinesweeperBasic window = new GuiMinesweeperBasic(game);
					frame.add(window);
					frame.setBounds(100, 100, 50*columns, 50*rows);

					frame.setVisible(true);
							
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}

