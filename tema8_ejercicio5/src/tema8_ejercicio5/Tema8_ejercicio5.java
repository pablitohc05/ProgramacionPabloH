package tema8_ejercicio5;
import tema8_ejercicio5.ConnectionSingleton;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tema8_ejercicio5 {

	private JFrame frame;
	private JTable table;
	private JTextField txtFieldPrecio;
	private JTextField txtFieldInc;
	private JTextField txtFieldVen;
	private JTextField txtCod;
	private JTextField txtPrecio;
	private JTextField txtNom;
	private JTextField txtUnidades;
	int codigo, precio, unidades;
	String nombre;
	Statement stmt;
	ResultSet rs;
	PreparedStatement sel_pstmt;
	ResultSet rsCodigos;
	int ganancias = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema8_ejercicio5 window = new Tema8_ejercicio5();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				//e
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tema8_ejercicio5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
			Connection con=ConnectionSingleton.getConnection();

		frame = new JFrame();
		frame.setBounds(100, 100, 861, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(98, 67, 1, 1);
		frame.getContentPane().add(table);
		JButton btnMostrar = new JButton("Mostrar");
		JLabel lblActualizarPrecio = new JLabel("Actualizar precio:");
		lblActualizarPrecio.setBounds(382, 22, 138, 15);
		frame.getContentPane().add(lblActualizarPrecio);
		DefaultTableModel model = new DefaultTableModel();
		JLabel lblEligeProducto = new JLabel("Elige producto:");
		lblEligeProducto.setBounds(425, 53, 107, 15);
		frame.getContentPane().add(lblEligeProducto);
		
		JComboBox comboBoxAct = new JComboBox();
		comboBoxAct.setBounds(543, 48, 63, 24);
		frame.getContentPane().add(comboBoxAct);
		
		JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
		lblNuevoPrecio.setBounds(425, 80, 107, 15);
		frame.getContentPane().add(lblNuevoPrecio);
		
		txtFieldPrecio = new JTextField();
		txtFieldPrecio.setBounds(543, 84, 114, 19);
		frame.getContentPane().add(txtFieldPrecio);
		txtFieldPrecio.setColumns(10);
		
		JButton btnActualizarPrecio = new JButton("Actualizar Precio");
		btnActualizarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				precio = Integer.parseInt(txtFieldPrecio.getText());
				codigo = (int)comboBoxAct.getSelectedItem();
				sel_pstmt = con.prepareStatement("UPDATE producto SET precio = ? WHERE codigo = ?");
				sel_pstmt.setInt(1, precio); 
				sel_pstmt.setInt(2, codigo);
				int rowsUpdated = sel_pstmt.executeUpdate();
				sel_pstmt.close();
				btnMostrar.doClick();
						}catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnActualizarPrecio.setBounds(688, 62, 161, 25);
		frame.getContentPane().add(btnActualizarPrecio);
		
		JLabel lblIncrementarStock = new JLabel("Incrementar Stock:");
		lblIncrementarStock.setBounds(382, 146, 150, 15);
		frame.getContentPane().add(lblIncrementarStock);
		
		JLabel lblEligeProductoInc = new JLabel("Elige producto:");
		lblEligeProductoInc.setBounds(425, 173, 107, 15);
		frame.getContentPane().add(lblEligeProductoInc);
		
		JLabel lblUnidadesAdquiridas = new JLabel("Unidades adquiridas:");
		lblUnidadesAdquiridas.setBounds(425, 200, 161, 15);
		frame.getContentPane().add(lblUnidadesAdquiridas);
		
		JComboBox comboBoxInc = new JComboBox();
		comboBoxInc.setBounds(543, 168, 63, 24);
		frame.getContentPane().add(comboBoxInc);
		
		txtFieldInc = new JTextField();
		txtFieldInc.setBounds(580, 198, 114, 19);
		frame.getContentPane().add(txtFieldInc);
		txtFieldInc.setColumns(10);
		
		JButton btnActualizarStock = new JButton("Actualizar Stock");
		btnActualizarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				codigo = (int)comboBoxInc.getSelectedItem();
				unidades = Integer.parseInt(txtFieldInc.getText());
				sel_pstmt = con.prepareStatement("UPDATE producto SET unidades = unidades + ? WHERE codigo = ?");
				sel_pstmt.setInt(1, unidades);
				sel_pstmt.setInt(2, codigo);
				int rowsUpdated = sel_pstmt.executeUpdate();
				sel_pstmt.close();
				btnMostrar.doClick();
				}catch (SQLException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		btnActualizarStock.setBounds(690, 168, 159, 25);
		frame.getContentPane().add(btnActualizarStock);
		
		JLabel lblVenta = new JLabel("Venta:");
		lblVenta.setBounds(382, 282, 70, 15);
		frame.getContentPane().add(lblVenta);
		
		JLabel lblProductoAVender = new JLabel("Producto a vender:");
		lblProductoAVender.setBounds(434, 309, 152, 15);
		frame.getContentPane().add(lblProductoAVender);
		
		JLabel lblGanancia = new JLabel("");
		lblGanancia.setBounds(501, 492, 311, 15);
		frame.getContentPane().add(lblGanancia);
		
		JComboBox comboBoxVen = new JComboBox();
		comboBoxVen.setBounds(580, 304, 63, 24);
		frame.getContentPane().add(comboBoxVen);
		
		JLabel lblUnidadesVender = new JLabel("Unidades a vender:");
		lblUnidadesVender.setBounds(425, 336, 161, 15);
		frame.getContentPane().add(lblUnidadesVender);
		
		txtFieldVen = new JTextField();
		txtFieldVen.setColumns(10);
		txtFieldVen.setBounds(580, 334, 114, 19);
		frame.getContentPane().add(txtFieldVen);
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				codigo = (int)comboBoxVen.getSelectedItem();
				unidades = Integer.parseInt(txtFieldVen.getText());
				PreparedStatement pstmt = con.prepareStatement("SELECT unidades FROM producto WHERE codigo = ?");
				pstmt.setInt(1, codigo);
				ResultSet rs = pstmt.executeQuery();
				int unidadesDisponibles = 0;
				if(rs.next()) {
				    unidadesDisponibles = rs.getInt("unidades");
				}
				pstmt.close();
				rs.close();
				PreparedStatement pstmt2 = con.prepareStatement("SELECT precio FROM producto WHERE codigo = ?");
				pstmt2.setInt(1, codigo);
				ResultSet rs2 = pstmt2.executeQuery();
				int precio = 0;
				if(rs2.next()) {
				    precio = rs2.getInt("precio");
				}
				pstmt2.close();
				rs2.close();
				
				if (unidadesDisponibles >= unidades) {
				sel_pstmt = con.prepareStatement("UPDATE producto SET unidades = unidades - ? WHERE codigo = ?");
				sel_pstmt.setInt(1, unidades);
				sel_pstmt.setInt(2, codigo);
				int rowsUpdated = sel_pstmt.executeUpdate();
				sel_pstmt.close();
				ganancias = ganancias + precio*unidades;
				lblGanancia.setText("Ganancias Totales: " + ganancias);
				lblGanancia.setForeground(Color.GREEN);
				btnMostrar.doClick();
				}else {
					JOptionPane.showMessageDialog(null, "No hay tantas unidades", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		btnVender.setBounds(690, 304, 159, 25);
		frame.getContentPane().add(btnVender);
		
		JLabel lblNuevoProducto = new JLabel("Nuevo Producto:");
		lblNuevoProducto.setBounds(45, 284, 159, 15);
		frame.getContentPane().add(lblNuevoProducto);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(26, 311, 70, 15);
		frame.getContentPane().add(lblCodigo);
		JButton btnAadir = new JButton("Añadir");
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(212, 311, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(26, 364, 70, 15);
		frame.getContentPane().add(lblPrecio);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(212, 364, 85, 15);
		frame.getContentPane().add(lblUnidades);
		
		txtCod = new JTextField();
		txtCod.setBounds(90, 309, 114, 19);
		frame.getContentPane().add(txtCod);
		txtCod.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(78, 362, 114, 19);
		frame.getContentPane().add(txtPrecio);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(278, 311, 114, 19);
		frame.getContentPane().add(txtNom);
		
		txtUnidades = new JTextField();
		txtUnidades.setColumns(10);
		txtUnidades.setBounds(293, 362, 114, 19);
		frame.getContentPane().add(txtUnidades);
		
		
		
		
		
		
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codigo = Integer.parseInt(txtCod.getText()); 
				nombre = txtNom.getText();
				precio = Integer.parseInt(txtPrecio.getText()); 
				unidades = Integer.parseInt(txtUnidades.getText());
				try{
				sel_pstmt = con.prepareStatement("INSERT INTO producto (codigo,nombre,precio,unidades) VALUES (?,?,?,?)");
				sel_pstmt.setInt(1, codigo);
				sel_pstmt.setString(2, nombre);
				sel_pstmt.setInt(3, precio);
				sel_pstmt.setInt(4, unidades);
				int rowsInserted = sel_pstmt.executeUpdate();
				sel_pstmt.close();
				JOptionPane.showMessageDialog(null, "Producto añadido"); //Caso OK
				btnMostrar.doClick();
				}catch (SQLException e) {
					 JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAadir.setBounds(151, 391, 117, 25);
		frame.getContentPane().add(btnAadir);
		
		JLabel lblBorrarProducto = new JLabel("Borrar Producto:");
		lblBorrarProducto.setBounds(49, 447, 143, 15);
		frame.getContentPane().add(lblBorrarProducto);
		
		JLabel lblEligeProducto_1 = new JLabel("Elige producto:");
		lblEligeProducto_1.setBounds(48, 492, 107, 15);
		frame.getContentPane().add(lblEligeProducto_1);
		
		JComboBox comboBoxBrr = new JComboBox();
		comboBoxBrr.setBounds(162, 487, 63, 24);
		frame.getContentPane().add(comboBoxBrr);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codigo = (int) comboBoxBrr.getSelectedItem();
				try {
				sel_pstmt = con.prepareStatement("DELETE FROM producto WHERE codigo = ?");
				sel_pstmt.setInt(1, codigo);
				int rowsDeleted =sel_pstmt.executeUpdate();
				sel_pstmt.close();
				btnMostrar.doClick();
				}catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnEliminar.setBounds(249, 487, 117, 25);
		frame.getContentPane().add(btnEliminar);
		
		
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Unidades");
		
		//Creacion de la tabla para que se vea
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM producto");
		while (rs.next()) {
		 Object[] row = new Object[4];
		 row[0] = rs.getInt("Codigo");
		 row[1] = rs.getString("Nombre");
		 row[2] = rs.getInt("Precio");
		 row[3] = rs.getInt("Unidades");
		 model.addRow(row);
		}
		
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 61, 311, 171);
		frame.getContentPane().add(scrollPane);
	
		
	
	btnMostrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
			rsCodigos = stmt.executeQuery("SELECT codigo FROM producto");
			comboBoxAct.removeAllItems();
			comboBoxInc.removeAllItems();
			comboBoxVen.removeAllItems();
			comboBoxBrr.removeAllItems();
		while (rsCodigos.next()) {
		    comboBoxAct.addItem(rsCodigos.getInt("codigo"));
		    comboBoxInc.addItem(rsCodigos.getInt("codigo"));
		    comboBoxVen.addItem(rsCodigos.getInt("codigo"));
		    comboBoxBrr.addItem(rsCodigos.getInt("codigo"));
		    
		    model.setRowCount(0);
		    stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM producto");
			while (rs.next()) {
			 Object[] row = new Object[4];
			 row[0] = rs.getInt("Codigo");
			 row[1] = rs.getString("Nombre");
			 row[2] = rs.getInt("Precio");
			 row[3] = rs.getInt("Unidades");
			 model.addRow(row);
			 
			}
		    
		}}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	
}}
	});
	btnMostrar.setBounds(51, 12, 117, 25);
	frame.getContentPane().add(btnMostrar);
	

	
	rsCodigos = stmt.executeQuery("SELECT codigo FROM producto");
		comboBoxAct.removeAllItems();
		comboBoxInc.removeAllItems();
		comboBoxVen.removeAllItems();
		comboBoxBrr.removeAllItems();
	while (rsCodigos.next()) {
	    comboBoxAct.addItem(rsCodigos.getInt("codigo"));
	    comboBoxInc.addItem(rsCodigos.getInt("codigo"));
	    comboBoxVen.addItem(rsCodigos.getInt("codigo"));
	    comboBoxBrr.addItem(rsCodigos.getInt("codigo"));
	}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
}
