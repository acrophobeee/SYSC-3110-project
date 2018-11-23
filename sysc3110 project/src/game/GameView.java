/*
 * This class is the build of gui. It has a 5*10 button to represent backyard and and three
 * buttons skip, sunflower and peashooter for user to place or direct skip the turn.
 * The design is based on milestone 1. In milstone 1, we make a grid class to show the what is
 * the game going on. In the class, method rendergrid is make connection with gui and grid.
 * So that gui and show what is grid going on.
 */
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import model.*;

import javax.swing.*;

public class GameView extends JFrame {
	private JMenu opt;
	private JMenuItem quit, res;
	private JPanel pvz, info;
	private JButton undo, redo, skip, sun, pea, nut, rep, tnt;
	private JTextField point;
	private JButton[][] gg;
	private JLabel sp;
	private int rows, columns;
	private ImageIcon peacho = new ImageIcon("pea.jpg"); // three images for zombie and plants
	private ImageIcon sunn = new ImageIcon("images.jpg");
	private ImageIcon zome = new ImageIcon("zom.jpg");
	private ImageIcon nutp = new ImageIcon("nut.png");
	private ImageIcon chz = new ImageIcon("chz.jpg");
	private ImageIcon repp = new ImageIcon("repeater.png");
	private ImageIcon pot = new ImageIcon("pot.jpg");
	
	public GameView(ActionListener al, int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.setTitle("Plant vs Zombie");
		this.setLayout(new BorderLayout());
		Font f = new Font("Trial", Font.BOLD, 25);
		//Create menu and menu item with restart and quit.
		JMenuBar b = new JMenuBar();
		opt = new JMenu("option");
		opt.setFont(f);
		b.add(opt);
		b.setPreferredSize(new Dimension(100, 50));
		res = new JMenuItem("restart");
		res.addActionListener(al);
		res.setFont(f);
		opt.add(res);
		quit = new JMenuItem("quit");
		quit.addActionListener(al);
		quit.setFont(f);
		opt.add(quit);
		//pvz panel for showing the square with model.
		pvz = new JPanel(new GridLayout(rows, columns));
		//info panel for showing sun point and contain three button for skipping, placing sun flower and pea shooter.
		info = new JPanel(new GridLayout(1, 3));
		info.setPreferredSize(new Dimension(300, 100));
		gg = new JButton[rows][columns];
		point = new JTextField();
		point.setEditable(false);
		point.setFont(f);
		//Create JButton with size row*column.
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				gg[row][column] = new JButton();
				gg[row][column].setEnabled(true);
				gg[row][column].setBackground(new Color(124, 252, 0));
				pvz.add(gg[row][column]);
			}
		}
		undo = new JButton("undo");
		undo.addActionListener(al);
		undo.setFont(f);
		redo = new JButton("redo");
		redo.addActionListener(al);
		redo.setFont(f);
		skip = new JButton("skip");
		skip.addActionListener(al);
		skip.setFont(f);
		sun = new JButton("sun", sunn);
		sun.addActionListener(al);
		sun.setFont(f);
		pea = new JButton("pea", peacho);
		pea.setFont(f);
		pea.addActionListener(al);
		nut = new JButton("nut", nutp);
		nut.setFont(f);
		nut.addActionListener(al);
		rep = new JButton("rep", repp);
		rep.setFont(f);
		rep.addActionListener(al);
		
		tnt = new JButton("tnt", pot);
		tnt.setFont(f);
		tnt.addActionListener(al);
		
		sp = new JLabel();
		sp.setText("Sun points: ");
		sp.setFont(f);
		info.add(sp);
		info.add(point);
		info.add(undo);
		info.add(redo);
		info.add(skip);
		info.add(sun);
		info.add(pea);
		info.add(nut);
		info.add(rep);
		info.add(tnt);
		this.add(b);
		this.add(pvz);
		this.add(info);
		this.getContentPane().add(b, BorderLayout.NORTH);
		this.getContentPane().add(pvz, BorderLayout.CENTER);
		this.getContentPane().add(info, BorderLayout.AFTER_LAST_LINE);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 800);
	}

	/*
	 * if the grid has the model at a specific location render it to the gui with the model
	 * image
	 */
	public void renderGrid(Grid grid) {
		if (grid.getHeight() != this.rows) {
			throw new IllegalArgumentException("can't render grid due to row length");
		}
		if (grid.getLength() != this.columns) {
			throw new IllegalArgumentException("can't render grid due to columns length");
		}

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Model m = grid.getModel(row, column);
				if (m == null) {
					this.gg[row][column].setIcon(null);
				} else if (m instanceof PeaShooter) {
					this.gg[row][column].setIcon(peacho);
				} else if (m instanceof SunFlower) {
					this.gg[row][column].setIcon(sunn);
				} else if (m instanceof FastZombie) {
					this.gg[row][column].setIcon(zome);
				} else if (m instanceof Nut) {
					this.gg[row][column].setIcon(nutp);
				} else if (m instanceof ConeHeadZombie) {
					this.gg[row][column].setIcon(chz);
				} else if (m instanceof RePeater) {
					this.gg[row][column].setIcon(repp);
				}else if (m instanceof Bomb) {
					this.gg[row][column].setIcon(pot);
				}

			}
		}
	}

	//Set the sun point textfield while use or generate
	public void renderSunPoints(int sp) {
		this.point.setText("" + sp);
	}

	public void display() {
		this.setVisible(true);
	}
}
