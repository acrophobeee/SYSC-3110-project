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
	private JButton skip, sun, pea;
	private JTextField point;
	private JButton[][] gg;
	private JLabel sp;
	private int rows, columns;
	private ImageIcon peacho = new ImageIcon("pea.jpg");
	private ImageIcon sunn = new ImageIcon("images.jpg");
	private ImageIcon zome = new ImageIcon("zom.jpg");

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
		//Create a text field with size row*column.
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				gg[row][column] = new JButton();
				gg[row][column].setEnabled(true);
				gg[row][column].setBackground(new Color(124, 252, 0));
				pvz.add(gg[row][column]);
			}
		}
		skip = new JButton("skip");
		skip.addActionListener(al);
		skip.setFont(f);
		sun = new JButton("sun", sunn);
		sun.addActionListener(al);
		sun.setFont(f);
		pea = new JButton("pea", peacho);
		pea.setFont(f);
		pea.addActionListener(al);
		sp = new JLabel();
		sp.setText("Sun points: ");
		sp.setFont(f);
		info.add(sp);
		info.add(point);
		info.add(skip);
		info.add(sun);
		info.add(pea);
		this.add(b);
		this.add(pvz);
		this.add(info);
		this.getContentPane().add(b, BorderLayout.NORTH);
		this.getContentPane().add(pvz, BorderLayout.CENTER);
		this.getContentPane().add(info, BorderLayout.AFTER_LAST_LINE);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 800);
	}

	//Show the model type in Text field.
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
				}

			}
		}
	}

	//Change the sun point while use or generate
	public void renderSunPoints(int sp) {
		this.point.setText("" + sp);
	}

	public void display() {
		this.setVisible(true);
	}
}
