package main;

import gameoflife.BlockMath;
import gameoflife.BlockPoint;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Instantiation extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static JPanel buttons;
	private static Instantiation inst;
	private JPanel gameOfLife;
	private boolean runnable;
	private static Set<BlockPoint> points;
	private static BlockMath math;
	private Timer timer;
	private int time;
	private JMenuBar jmb;
	private static boolean bool50 = false;
	private static boolean bool100 = true;
	private static boolean bool200 = false;
	private static boolean bool500 = false;
	private static boolean bool1000 = false;
	private static boolean bool5000 = false;
	private static boolean ranOnce = false;
	

	public static void main(String args[]) {
		inst = new Instantiation();
		points = new HashSet<BlockPoint>();
		math = new BlockMath();
		inst.setTitle("Conway's Game of life");
		inst.setVisible(true);
		inst.setDefaultCloseOperation(EXIT_ON_CLOSE);
		inst.setSize(600, 700);
		System.out.println();
		
		
	}

	
	public Instantiation() {

		time = 100;
		
		gameOfLife = new JPanel();
		gameOfLife.setLayout(new BoxLayout(gameOfLife, BoxLayout.PAGE_AXIS));
		
		
		
		
		buttons = new JPanel();
		buttons.setOpaque(true);
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		
		
		
		
		
		final JPanel drawings = new JPanel();
		drawings.setSize(600, 600);
		final DrawGameOfLife drawing = new DrawGameOfLife();
		
		drawing.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (runnable) {
					BlockPoint b = new BlockPoint(arg0.getX() / 10, arg0.getY() / 10);
					if (points.contains(b)) {
						points.remove(b);
					} else {
						points.add(b);
					}
					
					if (b.getX() < 60 && b.getY() < 60) {
						if (drawing.contains(b)) {
							drawing.remove(b);
						} else {
							drawing.addPoint(b);
						}
						drawing.repaint();
					}
					
					
				}
			}
			
		});
		
		final JButton run = new JButton("Run");
		runnable = true;
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (run.getText().equals("Run")) {
					timer = new Timer(time, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							drawing.clearPoints();
							Set<BlockPoint> block1 = points;
							
							points = math.moveBlocks(points);
							for (BlockPoint each : points) {
								drawing.addPoint(each);
							}
							if (block1.containsAll(points)) {
								run.setText("Run");
								timer.stop();
								runnable = true;
							}
							drawing.repaint();
							
						}
						
					});
					runnable = false;
					run.setText("Stop");
					timer.start();
				} else {
					run.setText("Run");
					timer.stop();
					runnable = true;
				}
				
			}
			
		});
		run.setSize(300, 100);
		run.setMaximumSize(run.getSize());
		
		JButton reset = new JButton("Reset");
		reset.setSize(300, 100);
		reset.setMaximumSize(reset.getSize());
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawing.clearPoints();
				drawing.repaint();
				points.clear();
				runnable = true;
				run.setText("Run");
				try {
					timer.stop();
				} catch (Exception e1) {
					
				}
				
			}
			
		});
		
		drawings.add(drawing);
		

		
		buttons.add(run);
		buttons.add(reset);

		gameOfLife.add(drawing);
		gameOfLife.add(buttons);
		add(gameOfLife);

		jmb = new JMenuBar();
		setJMenuBar(jmb);
		JMenu conway = new JMenu("Conway");
		JMenuItem info = new JMenuItem("Info");
		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
final JDialog dialogue = new JDialog();
				
				dialogue.setAlwaysOnTop(true);
				dialogue.setVisible(true);
				dialogue.setResizable(false);
				
				JPanel panel = new JPanel();
				panel.setAlignmentX(CENTER_ALIGNMENT);
				panel.setOpaque(true);
				panel.setBackground(Color.WHITE);
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				
				JLabel information = new JLabel("Created by Connor O'Brien. For more information,");
				information.setBorder(new EmptyBorder(0, 10, 0, 10));
				information.setAlignmentX(CENTER_ALIGNMENT);
				
				JLabel information1 = new JLabel("search for Conway's Game of Life on Google.");
				information1.setAlignmentX(CENTER_ALIGNMENT);
				
				JLabel contact = new JLabel("Contact: connorpadraigobrien@gmail.com");
				contact.setBorder(new EmptyBorder(10, 10, 5, 10));
				contact.setAlignmentX(CENTER_ALIGNMENT);
				
				
				
				JButton button = new JButton("Close");
				button.setAlignmentX(CENTER_ALIGNMENT);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialogue.dispose();
					}
				});
				
				
				panel.add(information);
				panel.add(information1);
				panel.add(contact);
				panel.add(button);
				
				dialogue.add(panel);
				
				
				
				dialogue.pack();
				
			}
			
		});
		conway.add(info);
		JMenuItem preferences = new JMenuItem("Preferences");
		preferences.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (timer.isRunning()) {
						timer.stop();
						ranOnce = true;
						
					}
				} catch (Exception e1) {
					
				}
				final JDialog dialogue = new JDialog();
				
				dialogue.setAlwaysOnTop(true);
				dialogue.setVisible(true);
				dialogue.setResizable(false);
				
				JPanel panel = new JPanel();
				panel.setAlignmentX(CENTER_ALIGNMENT);
				panel.setOpaque(true);
				panel.setBackground(Color.WHITE);
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				
				JLabel information = new JLabel("Speed");
				information.setAlignmentX(CENTER_ALIGNMENT);
				information.setBorder(new EmptyBorder(0, 10, 0, 10));
				
				CheckboxGroup speed = new CheckboxGroup();
				final Checkbox ms50 = new Checkbox("50 ms", speed, bool50);
				final Checkbox ms100 = new Checkbox("100 ms", speed, bool100);
				final Checkbox ms200 = new Checkbox("200 ms", speed, bool200);
				final Checkbox ms500 = new Checkbox("500 ms", speed, bool500);
				final Checkbox ms1000 = new Checkbox("1000 ms", speed, bool1000);
				final Checkbox ms5000 = new Checkbox("5000 ms", speed, bool5000);
				
				panel.add(information);
				panel.add(ms50);
				panel.add(ms100);
				panel.add(ms200);
				panel.add(ms500);
				panel.add(ms1000);
				panel.add(ms5000);
				
				JButton button = new JButton("Okay");
				button.setAlignmentX(CENTER_ALIGNMENT);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						bool50 = false;
						bool100 = false;
						bool200 = false;
						bool500 = false;
						bool1000 = false;
						bool5000 = false;
						if (ms50.getState()) {

							bool50 = true;
							time = 50;
						}
						if (ms100.getState()) {
							bool100 = true;
							time = 100;
						}
						if (ms200.getState()) {
							bool200 = true;
							time = 200;
						}
						if (ms500.getState()) {
							bool500 = true;
							time = 500;
						}
						if (ms1000.getState()) {
							bool1000 = true;
							time = 1000;
						}
						if (ms5000.getState()) {
							bool5000 = true;
							time = 5000;
						}
						if (ranOnce) {
							timer.setDelay(time);
							timer.start();
							runnable = false;
							
						}
						ranOnce = false;
						dialogue.dispose();
					}
				});
				
				panel.add(button);
				
				dialogue.add(panel);
				dialogue.pack();
			}
			
		});
		
		conway.add(preferences);
		
		jmb.add(conway);
		
		
	}
	
	public void setPoints(Set<BlockPoint> points) {
		Instantiation.points = points;
	}

}
