package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;

import controller.BillController;
import model.Analcolico;
import model.Bill;
import model.Coffe;
import model.CoffeArabica;
import model.CoffeRobusta;
import model.CoffeWithCreamDec;
import model.CoffeWithMilkDec;
import model.CoffeWithSanbucaDec;
import model.Drink;
import model.DrinkWithAppetizerDec;
import model.DrinkWithSodaDec;
import model.Martini;
import model.ObservableModelBill;
import model.PaymentCash;
import model.PaymentCreditCard;
import model.PaymentDebitCard;
import model.PaymentStrategy;
import model.Product;
import model.SendOrderException;

@SuppressWarnings("serial")
public class FormOrdine extends JFrame {
	private static BillController controller;
	private static ObservableModelBill model;

	private String productName = "Coffee";
	private int typeIndex,addIndex,qtaIndex, psIndex;
	private Coffe c;
	private Drink d;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					model = new ObservableModelBill(new Bill());
					controller = new BillController(model);
					FormOrdine frame = new FormOrdine(controller, model);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					//USE LOG HERE
//					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormOrdine(BillController controller, ObservableModelBill model) {
		FormOrdine.controller = controller;
		FormOrdine.model = model;
		initGUI();
	}
	
	public void initGUI() {
		 
		setTitle("Form Ordine");
 
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(950,600));
		this.getContentPane().add(panel);
		this.setResizable(false);
 
		Object rowDataCoffee[][] = { { "Arabica: 1,00", "Latte: 0,30"}, //argomenti colonne
                					 { "Robusta: 1,10", "Panna: 0,50"},
                					 { "", "Sambuca: 1,10"}};
		Object columnNamesCoffee[] = { "Miscele caffe'", "Aggiunte"}; //nome colonne
		JTable tCoffee = new JTable(rowDataCoffee,columnNamesCoffee);	
		modifyTable(tCoffee);
		
		Object rowDataDrink[][] = { { "Martini: 5,00", "Soda: 1,50"},
                					{ "Analcolico: 3.,0", "Aperitivo: 0,50"}};
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
		addOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Product p;
				if(productName.equals("Coffee")) {
					Coffe[] coffeArray = {new CoffeArabica(), new CoffeRobusta()};
					c =  coffeArray[typeIndex];
					Coffe[] coffeDec = {c, new CoffeWithMilkDec(c), new CoffeWithCreamDec(c), new CoffeWithSanbucaDec(c)};
					c = coffeDec[addIndex];
					p = c;
				} else {
					Drink[] drinkArray = {new Martini(), new Analcolico()};
					d = drinkArray[typeIndex];
					Drink[] drinkDec = {d,new DrinkWithSodaDec(d), new DrinkWithAppetizerDec(d)}; 
					d = drinkDec[addIndex];
					p = d;
				}
				for (int i=0; i <= qtaIndex; i++) {
					controller.addProduct(p);
				}
				
			}
			
		});  
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

		deleteOrderButton.addActionListener(e -> {
			if(controller.getProduct()!=null && !controller.getProduct().isEmpty()) {
				FormDeleteProduct formDelete = new FormDeleteProduct(controller);
				formDelete.pack();
				formDelete.setLocationRelativeTo(null);
				formDelete.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Ordine vuoto!");
			}
		});
		
		JButton sendOrderButton = new JButton("Ordina");
		gbc.insets = new Insets(20,50,20,0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.CENTER;
		sendOrderButton.addActionListener(e -> {
            try {
				controller.sendOrder();
				 JOptionPane.showMessageDialog(this, "Ordine preso in carico. Ritira i prodotti al banco numero: "+(int)(1+Math.random()*10));
			} catch (SendOrderException e1) {
				 JOptionPane.showMessageDialog(this, e1.getMessage());
			}
        });
		
		panel.add(sendOrderButton, gbc);
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
	
	private ItemListener createItemListener(JComboBox<String> product,JComboBox<String> type, JComboBox<String> add) {
		String[][] productString = {
		    { "Martini", "Analcolico" },   //typeStringDrink
		    { " - " ,"Soda", "Aperitivo" }, //addStringDrink
		    { "Arabica", "Robusta" },      //typeStringCoffee
		    { " - " ,"Latte", "Panna", "Sambuca" } }; //addStringCoffee
		ItemListener itemListener = new ItemListener() {
		@Override
	          public void itemStateChanged(ItemEvent itemEvent) {
	            DefaultComboBoxModel<String> modelType = (DefaultComboBoxModel<String>) type.getModel();
		    DefaultComboBoxModel<String> modelAdd = (DefaultComboBoxModel<String>) add.getModel();
		    int i = 0;
        	    modelType.removeAllElements();
        	    modelAdd.removeAllElements();
        	    String productSelected = (String)product.getSelectedItem();
                    switch(productSelected)
                    {
                        case "Drink":
                            i = 0;
                            break;
                        case "Coffee":
                            i = 2;
                            break;
                        default:
//                            System.out.println("no match");
                   }
        	
                  for (int j = 0;j < productString[i].length; j++) {
            	      modelType.addElement(productString[i][j]);
                   }
            
                  for (int j = 0;j < productString[i+1].length; j++) {
           	     modelAdd.addElement(productString[i+1][j]);
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
		String[] addStringCoffee = {"-" , "Latte", "Panna", "Sambuca"};
		String[] qtaString = {"1","2","3","4","5"};
		JComboBox<String> product = new JComboBox<String>(productString);
		JComboBox<String> type = new JComboBox<String>(typeStringCoffee);
		JComboBox<String> add = new JComboBox<String>(addStringCoffee);
		JComboBox<String> qta = new JComboBox<String>(qtaString);
		
		
		ActionListener acProduct = (e) -> productName = productString[product.getSelectedIndex()];
		product.addActionListener(acProduct);
		
		ActionListener acType = (e) -> typeIndex = type.getSelectedIndex();
		type.addActionListener(acType);
		
		ActionListener acAdd = (e) -> addIndex = add.getSelectedIndex();
		add.addActionListener(acAdd);

		ActionListener acQta = (e) -> qtaIndex = qta.getSelectedIndex();
		qta.addActionListener(acQta);
		    
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
		JTextArea textArea = new ObserverTextArea(20,25,model);
		textArea.setEditable(false);
		
		JLabel payLabel = new JLabel("Paga con: ");
		String[] payStrings = { "Contanti", "Carta di credito","Carta di debito" };
		PaymentStrategy ps[] = {new PaymentCash(), new PaymentCreditCard(), new PaymentDebitCard()};
		
		//Create the combo box
		JComboBox<String> payComboBox = new JComboBox<String>(payStrings);
		payComboBox.addActionListener(e -> psIndex = payComboBox.getSelectedIndex());
		JButton payButton = new JButton("Paga");
		payButton.setPreferredSize(new Dimension(200, 50));
		payButton.addActionListener(e ->  {
			if(controller.canPay()) {
				controller.setPaymentStrategy(ps[psIndex]);
				JOptionPane.showMessageDialog(this, "Ritira lo scontrino alla cassa..."+(int)((Math.random()+1)*10));
				controller.clear();
			}
			
		});
		
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

