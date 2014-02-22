package main;

import gameoflife.BlockPoint;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;

public class DrawGameOfLife extends JComponent{
	Set<BlockPoint> points;
	private static final long serialVersionUID = 1L;
	Graphics2D g2;
	
	public DrawGameOfLife() {
		super();
		points = new HashSet<BlockPoint>();
	}
	
	public void addPoint(BlockPoint b) {
		points.add(b);
	}
	
	
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		Rectangle rect = new Rectangle (0, 0, 600, 600);
		for (BlockPoint next: points) {
			int x1 = next.getX() * 10;
			int y1 = next.getY() * 10;
			Rectangle block = new Rectangle(x1, y1, 10, 10);
			block.setSize(new Dimension(10, 10));
			g2.draw(block);
			g2.fill(block);
		}
		
		g2.draw(rect);
	}
	
	public void clearPoints() {
		points.clear();
	}
	
	public void setPoints(Set<BlockPoint> points) {
		this.points = points;
	}
	
	public boolean contains(BlockPoint b) {
		return points.contains(b);
		
	}
	
	public void remove(BlockPoint b) {
		points.remove(b);
	}
	
}
