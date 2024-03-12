package paint6marzi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Paint extends JPanel {

	private Figura figura;	
	boolean borrado = false;
	private JCheckBox chckbxFilled;
	private int inCordX;
	private int inCordY;
	private int finCordX;
	private int finCordY;
	int selColor;
	private int selFig;
	private String[] Colores = { "ROJO", "AMARILLO", "AZUL", "VERDE", "ROSA", "CYAN" };
	private String[] Figuras = { "LINEA", "RECTANGULO", "OVAL" };
	private JFrame frame;
	private JLabel lblCordX;
	private int contador = 0;
	private List<Figura> figuras = new ArrayList<>();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paint window = new Paint();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Paint() {
		initialize();
		paint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < contador; i++) {
			figuras.get(i).pintar(g);
			
		}

////		for (Figura figura : figuras) {
//			figura.pintar(g);
//		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Paint");
		frame.getContentPane().add(this);
		setLayout(null);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JButton btnUndo = new JButton("Deshacer");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contador > 0) {
					contador--;
					borrado = true;
					repaint();
				}
			}
		});
		menuBar.add(btnUndo);

		JButton btnRedo = new JButton("Rehacer");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contador < figuras.size()) {
					contador++;
					repaint();
				}
			}
		});
		menuBar.add(btnRedo);

		JButton btnClear = new JButton("Limpiar");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Graphics g = getGraphics();
				figuras.clear();
				contador = 0;
				update(g);
				
			}
		});
		menuBar.add(btnClear);

		JComboBox cmbColor = new JComboBox(Colores);
		menuBar.add(cmbColor);

		JComboBox cmbFiguras = new JComboBox(Figuras);
		menuBar.add(cmbFiguras);

		chckbxFilled = new JCheckBox("Rellenar");
		menuBar.add(chckbxFilled);

		lblCordX = new JLabel("");
		lblCordX.setBounds(0, 622, 246, 15);
		add(lblCordX);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inCordX = e.getX();
				inCordY = e.getY();
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				inCordX = e.getX();
				inCordY = e.getY();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				finCordX = e.getX();
				finCordY = e.getY();
				if (borrado) {
					for (int i = figuras.size()-1; i >= contador; i--) {
						figuras.remove(i);
					}	
					borrado = false;
				}
				paint();
				contador++;
				figuras.add(figura);
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // Actualizar coordenadas cada vez q se mueva el raton
				int x = e.getX();
				int y = e.getY();
				lblCordX.setText("Las coordenadas de X: " + x + " Y: " + y);
				selColor = cmbColor.getSelectedIndex();
				selFig = cmbFiguras.getSelectedIndex();
			}
		});
		
//		frame.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			    finCordX = e.getX();
//			    finCordY = e.getY(); 
//			    Graphics g = getGraphics();
//			    switch (selFig) {
//			        case 0:
//			            // Línea
//			            figura = new Linea(inCordX, inCordY, finCordX, finCordY, selColor);
//			            break;
//			        case 1:
//			            // Rectángulo
//			            figura = new Rectangulo(inCordX, inCordY, finCordX, finCordY, selColor, chckbxFilled.isSelected());
//			            break;
//			        case 2:
//			            // Óvalo
//			            figura = new Oval(inCordX, inCordY, finCordX, finCordY, selColor, chckbxFilled.isSelected());
//			            break;
//			    }		
//			    // Dibuja la figura actual
//			    figura.pintar(g);
//			}
//		});
	}
	private void paint() {

		Graphics g = getGraphics();
		switch (selFig) {
		case 0:
			// Línea
			figura = new Linea(inCordX, inCordY, finCordX, finCordY, selColor);

			break;

		case 1:
			// Rectángulo
			figura = new Rectangulo(inCordX, inCordY, finCordX, finCordY, selColor, chckbxFilled.isSelected());
			break;

		case 2:
			// Óvalo
			figura = new Oval(inCordX, inCordY, finCordX, finCordY, selColor, chckbxFilled.isSelected());
			break;

		}
		figura.pintar(g); // pintamos la figura

	}
}
