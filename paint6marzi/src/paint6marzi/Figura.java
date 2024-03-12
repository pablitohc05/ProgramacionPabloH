package paint6marzi;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {

	protected int inCordX;
	protected int inCordY;
	protected int finCordX;
	protected int finCordY;
	protected int color;
	protected boolean filled;

	public Figura(int inCordX, int inCordY, int finCordX, int finCordY, int color, boolean filled) {
		this.inCordX = inCordX;
		this.inCordY = inCordY;
		this.finCordX = finCordX;
		this.finCordY = finCordY;
		this.color = color;
		this.filled = filled;
	}

	public abstract void pintar(Graphics g);

	public int getInCordX() {
		return inCordX;
	}

	public void setInCordX(int inCordX) {
		this.inCordX = inCordX;
	}

	public int getInCordY() {
		return inCordY;
	}

	public void setInCordY(int inCordY) {
		this.inCordY = inCordY;
	}

	public int getFinCordX() {
		return finCordX;
	}

	public void setFinCordX(int finCordX) {
		this.finCordX = finCordX;
	}

	public int getFinCordY() {
		return finCordY;
	}

	public void setFinCordY(int finCordY) {
		this.finCordY = finCordY;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
