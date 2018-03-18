package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.*;

@SuppressWarnings("serial")
public class FormOrdine extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormOrdine frame = new FormOrdine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormOrdine() {
		initGUI();
	}
	
	public void initGUI() {
		 
		setTitle("Form Ordine");
 
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(900,600));
		this.getContentPane().add(panel);
		this.setResizable(false);
 
		Object rowDataCoffee[][] = { { "Arabica: 1.00", "Latte: 0.30"}, //argomenti colonne
                					 { "Robusta: 1.10", "Panna: 0.50"},
                					 { "", "Sanbuca: 1.10"}};
		Object columnNamesCoffee[] = { "Miscelle caffe'", "Aggiunte"}; //nome colonne
		JTable tCoffee = new JTable(rowDataCoffee,columnNamesCoffee);	
		modifyTable(tCoffee);
		
		Object rowDataDrink[][] = { { "Martini: 5.00", "Soda: 1.50"},
                					{ "Analcolico alla frutta: 3.50", "Appetizier: 0.50"}};
		Object columnNamesDrink[] = { "Drink", "Aggiunte"};
		JTable tDrink = new JTable(rowDataDrink,columnNamesDrink);
		tDrink.setEnabled(false);
		modifyTable(tDrink);
		
		JScrollPane tableScrollPane = new JScrollPane(tCoffee);
		tableScrollPane.setPreferredSize(new Dimension(400, 150));
		
		JScrollPane tableScrollPane2 = new JScrollPane(tDrink);
		tableScrollPane2.setPreferredSize(new Dimension(400, 150));
 
		JLabel labelTitle = new JLabel("-- Menu --");
		labelTitle.setFont(new Font("Tahome", Font.BOLD, 20));
 
		GridBagConstraints gbc = new GridBagConstraints();
 
		gbc.insets = new Insets(10,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
 
		panel.add(labelTitle, gbc);
 
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;		
		//gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(tableScrollPane, gbc);
		
		
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(25,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;		
		//gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(tableScrollPane2, gbc);
		
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(createComboPanel(), gbc);
		
		JButton addOrderButton = new JButton("Aggiungi prodotto");
		addOrderButton.setPreferredSize(new Dimension(150, 50));
		//gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(20,2,2,200);
		panel.add(addOrderButton, gbc);
		
		JButton deleteOrderButton = new JButton("Rimuovi prodotto");
		deleteOrderButton.setPreferredSize(new Dimension(150, 50));
		gbc.insets = new Insets(20,250,2,2);
		gbc.gridx = 0;
		gbc.gridy = 4;
	//	gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(deleteOrderButton, gbc);
		
		JButton SendOrderButton = new JButton("Ordina");
		gbc.insets = new Insets(20,50,20,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(SendOrderButton, gbc);
		
		JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		sep.setPreferredSize(new Dimension(10,10));
		gbc.insets = new Insets(0,0,0,0);
		
		gbc.weighty = 1;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridheight = 6;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    panel.add(sep, gbc);
		
 
		gbc.insets = new Insets(0,0,0,20);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.anchor = GridBagConstraints.NORTH;
		panel.add(createRightPanel(), gbc);
		
		
		this.pack();
		
		this.setVisible(true);
	}
	
	private void modifyTable(JTable jt) {
		jt.setEnabled(false);
		jt.getTableHeader().setReorderingAllowed(false); //permette di non spostare le colonne
		jt.setFont(new Font("Tahome", Font.BOLD, 15));
		jt.setRowHeight(41);
		JTableHeader tHeader = jt.getTableHeader();
		tHeader.setBackground(new Color(5, 198, 252));
		tHeader.setFont(new Font("Tahome", Font.BOLD, 15));
	}
	
	private ItemListener createItemListener(JComboBox product,JComboBox type, JComboBox add) {
		String[] typeStringDrink = { "Martini", "Analcolico" };
		String[] addStringDrink = { "Soda", "Apetizer"};
		String[] typeStringCoffee = { "Arabica", "Robusta" };
		String[] addStringCoffee = { "Latte", "Panna", "Sanbuca"};
		ItemListener itemListener = new ItemListener() {
		@Override
	      public void itemStateChanged(ItemEvent itemEvent) {
			DefaultComboBoxModel modelType = (DefaultComboBoxModel) type.getModel();
			DefaultComboBoxModel modelAdd = (DefaultComboBoxModel) add.getModel();
        	modelType.removeAllElements();
        	modelAdd.removeAllElements();
	        if(product.getSelectedItem().equals("Drink")) {
		            for (String item : typeStringDrink) {
		                 modelType.addElement(item);
		             }
		            for (String item : addStringDrink) {
		                 modelAdd.addElement(item);
		             }
		     
	        	} else {
	        		for (String item : typeStringCoffee) {
		                 modelType.addElement(item);
		             }
	        		 for (String item : addStringCoffee) {
		                 modelAdd.addElement(item);
		             }
	        	}
	        }
	    };
	    return itemListener;
	}
	
	private JPanel createComboPanel() {
		JPanel panel = new JPanel();
		JLabel productLabel = new JLabel("Prodotto");
		JLabel typeLabel = new JLabel("Tipo");
		JLabel addLabel = new JLabel("Aggiunte");
		JLabel qtaLabel = new JLabel("Qta.");
		
		String[] productString = { "Coffee", "Drink" };
		String[] typeStringCoffee = { "Arabica", "Robusta" };
		String[] addStringCoffee = { "Latte", "Panna", "Sanbuca"};
		String[] qtaString = {"1","2","3","4","5"};
		JComboBox product = new JComboBox(productString);
		JComboBox type = new JComboBox(typeStringCoffee);
		JComboBox add = new JComboBox(addStringCoffee);
		JComboBox qta = new JComboBox(qtaString);
		    
		ItemListener itemListener = createItemListener(product,type,add);
		product.addItemListener(itemListener);

		panel.add(productLabel);	
		panel.add(product);	
		panel.add(typeLabel);			
		panel.add(type);
		panel.add(addLabel);			
		panel.add(add);
		panel.add(qtaLabel);			
		panel.add(qta);
		
		return panel;
		
	}
	
	private JPanel createRightPanel() {
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea(20,20);
		textArea.setEditable(false);
		
		JLabel payLabel = new JLabel("Paga con: ");
		String[] payStrings = { "Contanti", "Carta di credito","Carta di debito" };
		
		//Create the combo box
		JComboBox payComboBox = new JComboBox(payStrings);
		JButton payButton = new JButton("Paga");
		payButton.setPreferredSize(new Dimension(200, 50));
		
		panel.setLayout(new GridBagLayout());
	
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(0,2,2,2);
		gbc.ipady = 60;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JScrollPane sc = new JScrollPane(textArea);
		panel.add(sc,  gbc);
		
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(10,15,2,2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(payLabel,  gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,120,0,0);
		panel.add(payComboBox,  gbc);	
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(15,0,0,0);
		gbc.fill = GridBagConstraints.NONE;
		panel.add(payButton,  gbc);
		
		
		return panel;
	}


}
