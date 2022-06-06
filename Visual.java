package EJERCICIOEXAMEN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Visual extends JFrame implements ActionListener 
{
	GestorBD GBD;
	ButtonGroup BG;
	JRadioButton boton;
	String botonseleccionado;
	public Visual()
	{
		this.setTitle("EJERCICIO-EXAMEN");
		this.setSize(700, 500);
		this.setLayout(new BorderLayout());		
		
		JPanel panel_oeste = new JPanel();
		JPanel panel_centro = new JPanel();
		JPanel panel_este = new JPanel();
		
		//PANEL OESTE
		anadircomponentesoeste(panel_oeste);
		this.add(panel_oeste,BorderLayout.WEST);
		
		//PANEL CENTRO
		anadircomponentescentro(panel_centro);
		this.add(panel_centro,BorderLayout.CENTER);
		
		
		//PANEL ESTE
		anadircomponenteseste(panel_este);
		this.add(panel_este,BorderLayout.EAST);
		
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void anadircomponenteseste(Container panel_este) 
	{
		JPanel panel_norte = new JPanel();
		JPanel panel_sur = new JPanel();
		
		panel_este.setLayout(new BorderLayout());
		
		//PANEL NORTE
		JTextArea text = new JTextArea();
		JScrollPane sp = new JScrollPane(text);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		panel_norte.add(sp);
		panel_este.add(panel_norte,BorderLayout.CENTER);
		//PANEL SUR
		panel_sur.setLayout(new FlowLayout());
		JLabel label_DNI = new JLabel ("DNI:");
		JTextField texto_DNI = new JTextField ();
		texto_DNI.setColumns(15);
		JButton botoncomprar = new JButton ("Comprar");
		panel_sur.add(label_DNI);
		panel_sur.add(texto_DNI);
		panel_sur.add(botoncomprar);
		panel_este.add(panel_sur,BorderLayout.SOUTH);
		
	}
	private void anadircomponentescentro(Container panel_centro) 
	{
		GBD = new GestorBD();
		panel_centro.setLayout(new BorderLayout());
		JPanel panel_arriba= new JPanel();
		JPanel panel_abajo = new JPanel();
		//PANEL NORTE
		panel_arriba.setLayout(new FlowLayout());
		JLabel label_ruta = new JLabel ("RUTA");
		ArrayList<String>destino = GBD.destinosDesde(botonseleccionado);
		JComboBox combo = new JComboBox();
		for (int i=0;i<destino.size();i++)
		{
			combo.addItem(destino.get(i));
		}
		JButton buttonanadir = new JButton ("Añadir");
		panel_arriba.add(label_ruta);
		panel_arriba.add(combo);
		panel_arriba.add(buttonanadir);
		panel_centro.add(panel_arriba,BorderLayout.NORTH);
		
		//PANEL SUR
		
		
		JLabel label_texto = new JLabel();
		panel_abajo.setBackground(Color.GRAY);
		panel_abajo.add(label_texto);
		panel_centro.add(panel_abajo,BorderLayout.CENTER);
	}
	private void anadircomponentesoeste(Container panel_oeste) 
	{
		panel_oeste.setLayout(new BoxLayout(panel_oeste, BoxLayout.Y_AXIS));
		JLabel label_origen = new JLabel ("Seleccionar origen");
		panel_oeste.add(label_origen);
		GBD = new GestorBD();
		ArrayList<String> a = GBD.obtenerOrigen();
		BG = new ButtonGroup();
		JRadioButton[] b = new JRadioButton[a.size()];
		for (int i=0;i<b.length;i++)
		{
			b[i] = new JRadioButton(a.get(i));
			boton = b[i];
			botonseleccionado = boton.getText();
			BG.add(b[i]);
			panel_oeste.add(b[i]);
		}
	}
	
	
	public static void main (String[]args)
	{
		Visual V1 = new Visual();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

}