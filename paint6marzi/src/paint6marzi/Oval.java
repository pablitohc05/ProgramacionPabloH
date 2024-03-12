package paint6marzi;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Figura {

	static final Color[] Colores = { Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN };

	public Oval(int inCordX, int inCordY, int finCordX, int finCordY, int color, boolean filled) {
		super(inCordX, inCordY, finCordX, finCordY, color, filled);
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(obtenerColor());
		if (filled) {
			g.fillOval(Math.min(inCordX, finCordX), Math.min(inCordY, finCordY), Math.abs(finCordX - inCordX),
					Math.abs(finCordY - inCordY));
		} else {
			g.drawOval(Math.min(inCordX, finCordX), Math.min(inCordY, finCordY), Math.abs(finCordX - inCordX),
					Math.abs(finCordY - inCordY));
		}
	}

	private Color obtenerColor() {
		return Colores[color];
	}

}
