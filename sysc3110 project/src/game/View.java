package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

import javax.swing.*;

public class View extends JFrame implements ActionListener {

	private JMenu opt;
	private JMenuItem quit, res;
	private JPanel pvz,info;
	private JButton skip, sun, pea;
	private JTextField point;
	private JTextField[][] gg;
	private JLabel sp = new JLabel("Sun points: ");
	private Game g;
	
	public View() {
		super();
		this.setTitle("Plant vs Zombie");
		this.setLayout(new BorderLayout());
		Font f = new Font("Trial",Font.BOLD,25);
		g = new Game();
		sp.setFont(f);
		JMenuBar b = new JMenuBar();
		opt = new JMenu("option");
		opt.setFont(f);
		b.add(opt);
		b.setPreferredSize(new Dimension(100,50));
		quit = new JMenuItem("quit");
		quit.addActionListener(this);
		quit.setFont(f);
		opt.add(quit);
		res= new JMenuItem("restart");
		res.addActionListener(this);
		res.setFont(f);
		opt.add(res);
		pvz = new JPanel(new GridLayout(5,10));
		info = new JPanel(new GridLayout(1,3));
		info.setPreferredSize(new Dimension(300,100));
		gg = new JTextField[5][10];
		point = new JTextField();
		point.setEditable(false);
		point.setFont(f);
		for (int row=0; row<5; row++) {
			for(int column=0; column<10; column++) {
				gg[row][column]= new JTextField("");
				gg[row][column].setEditable(false);
				gg[row][column].addActionListener(this);
				gg[row][column].setFont(f);
				pvz.add(gg[row][column]);
			}
		}
		skip = new JButton("skip");
		skip.addActionListener(this);
		skip.setFont(f);
		sun = new JButton("sun");
		sun.addActionListener(this);
		sun.setFont(f);
		pea = new JButton("pea");
		pea.addActionListener(this);
		pea.setFont(f);
		info.add(sp);
		info.add(point);
		info.add(skip);
		info.add(sun);
		info.add(pea);
		this.add(b);
		this.add(pvz);
		this.add(info);
		this.getContentPane().add(b,BorderLayout.NORTH);
		this.getContentPane().add(pvz,BorderLayout.CENTER);
		this.getContentPane().add(info,BorderLayout.AFTER_LAST_LINE);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			if(e.getActionCommand()=="skip") {
				
			}
			if(e.getActionCommand()=="sun") {
				String rows = JOptionPane.showInputDialog(null, "Input place row", "Input Sun Flower", JOptionPane.QUESTION_MESSAGE);
				int row =Integer.parseInt(rows);
				String columns = JOptionPane.showInputDialog(null, "Input place column","Input Sun Flower", JOptionPane.QUESTION_MESSAGE);
				int col = Integer.parseInt(columns);
				this.setModel(row,col,ModelType.SUN_FLOWER);
			}
			if(e.getSource()==pea) {
				String rows = JOptionPane.showInputDialog(null, "Input place row","Input Pea Shooter", JOptionPane.QUESTION_MESSAGE);
				int row =Integer.parseInt(rows);
				String columns = JOptionPane.showInputDialog(null, "Input place column","Input Pea Shooter", JOptionPane.QUESTION_MESSAGE);
				int col = Integer.parseInt(columns);
				this.setModel(row,col,ModelType.PEA_SHOOTER);
			}
			
	}

	public JMenu getOpt() {
		return opt;
	}

	public void setOpt(JMenu opt) {
		this.opt = opt;
	}

	public JMenuItem getQuit() {
		return quit;
	}

	public void setQuit(JMenuItem quit) {
		this.quit = quit;
	}

	public JMenuItem getRes() {
		return res;
	}

	public void setRes(JMenuItem res) {
		this.res = res;
	}

	public JPanel getPvz() {
		return pvz;
	}

	public void setPvz(JPanel pvz) {
		this.pvz = pvz;
	}

	public JPanel getInfo() {
		return info;
	}

	public void setInfo(JPanel info) {
		this.info = info;
	}

	public JButton getSkip() {
		return skip;
	}

	public void setSkip(JButton skip) {
		this.skip = skip;
	}

	public JButton getSun() {
		return sun;
	}

	public void setSun(JButton sun) {
		this.sun = sun;
	}

	public JButton getPea() {
		return pea;
	}

	public void setPea(JButton pea) {
		this.pea = pea;
	}

	public JTextField getPoint() {
		return point;
	}

	public void setPoint(JTextField point) {
		this.point = point;
	}

	public JTextField[][] getGg() {
		return gg;
	}

	public void setModel(int i, int j, ModelType model) {
		gg[i][j].setText(model.toString());
	}

	public JLabel getSp() {
		return sp;
	}

	public void setSp(JLabel sp) {
		this.sp = sp;
	}

	public Game getG() {
		return g;
	}

	public void setG(Game g) {
		this.g = g;
	}
	
	}
		
