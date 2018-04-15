package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.BillController;
import model.Product;

@SuppressWarnings("serial")
public class FormDeleteProduct extends JFrame{
	private BillController controller;
	private JList<Product> list;
	private List<Product> removeProduct = new Stack<Product>();
	
	public FormDeleteProduct(BillController controller) {
		this.controller = controller;
		list = new JList<Product>(controller.getProduct());
		initGui();
	}
	
	private void initGui() {
		 
		 Container c = getContentPane(); 	
		 c.setLayout(new FlowLayout());
		 list.setVisibleRowCount(5);
		    
		
	    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setPreferredSize(new Dimension(200,200));
	    list.addListSelectionListener((e) -> removeProduct = list.getSelectedValuesList());
		    
	    c.add(new JScrollPane(list));
		 
	    setTitle("Delete Product");
	    setSize(300,300);
		    
	    JPanel controls = new JPanel();
	    JButton applyButton = new JButton("Elimina prodotti selezionati");
	    //this action close automatically the frame after the iteration over the list.
	    //To remove this behavior delete closeFrame().
	    applyButton.addActionListener((e) -> {
		    removeProduct.forEach((p) -> {controller.removeProduct(p);});
			closeFrame();
		    });
		applyButton.setPreferredSize(new Dimension(200, 50));
	    controls.add(applyButton);
	    c.add(controls);
	}
	
	//close frame
	private void closeFrame() {
		dispose();
	}

}
