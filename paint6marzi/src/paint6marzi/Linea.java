package paint6marzi;

import java.awt.Color;
import java.awt.Graphics;

public class Linea extends Figura {

	static final Color[] Colores = { Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN };

	public Linea(int inCordX, int inCordY, int finCordX, int finCordY, int color) {
		super(inCordX, inCordY, finCordX, finCordY, color, false);
	}

	@Override
	public void pintar(Graphics g) {
		g.setColor(obtenerColor());
		g.drawLine(inCordX, inCordY, finCordX, finCordY);
	}

	private Color obtenerColor() {
		return Colores[color];
	}

}
