package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

import model.ObservableModelBill;

@SuppressWarnings("serial")
public class ObserverTextArea extends JTextArea implements Observer  {
	
	public ObserverTextArea(int width, int height, ObservableModelBill model) {
		super(width,height);
		model.addObserver(this);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		setText(arg0.toString());
		repaint();
	}

}
