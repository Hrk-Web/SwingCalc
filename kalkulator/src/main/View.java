package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Kalkulator.CalculationType;
import main.Kalkulator.ListenForButton;
import main.Kalkulator.ListenForText;

public class View extends JFrame {
	
	public static void main(String[] args) {
		new View();
	}
	/*
	 * When there is no decimal places during calculations, the .0 still 
	 * appears, but it shoudn`t. Work it out. 
	 */
	
	private JButton[] butNum = new JButton[10];
	private JButton	butPlus, butMinus, clearAll, butDivide, butMultiply, butEquals,
			butPeriod, butDeco1, butDeco2;
	
	private JTextField resultField;
	ListenForButton lfButton;
	int num1, num2;
	int computePick;  // computePick: 1=multiply, 2=divide, 3=plus, 4=minus
	String int_curCompute, temp;
	double currentlyComputed;
	// @param bool When true it clears the textfield and places a number
	//             When false it adds a new number at the end of the line
	boolean bool = true; 
	// @param endOfEquation True when "=" is pressed, it clears the textfield and places a number
	//                      False when no "=" was pressed, it adds a new number at the end of the line
	boolean endOfEquation = true;
	boolean isMinus, isPlus, isMultiply, isDivide, isPeriod = false;
	
	private JPanel thePanel;
	private GridBagConstraints gridConstraints;
	
public View() {
		
		this.setSize(250, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator");

		thePanel = new JPanel();
		thePanel.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();

		resultField = new JTextField("0", 15);
		resultField.setEditable(false); // user shouldn`t change it
		resultField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		// Creates JButtons 
		initCalcButtons(); 
		

//		butEquals.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				resultField.setText(Double.toString(currentlyComputed)); 
//			}
//		});		
		
		// Adds actions listener to buttons
		prepareButtonsListener();
		
		// Sets grid and overall appearance of calculator
		setCalcAppearance();	

		this.add(thePanel);
		this.setVisible(true);	

	} // END OF CONSTRUCTOR

/**
 * Adds action listener to buttons
 */
private void prepareButtonsListener() {
	ListenForText lfText = new ListenForText();
	ListenForButton lfButton = new ListenForButton();
	
	List<JButton> buttons = new ArrayList<JButton>();
	
	for (int i=0; i<butNum.length; i++){
		buttons.add(butNum[i]);
	}
	buttons.add(clearAll);
	buttons.add(butMultiply);
	buttons.add(butDivide);
	buttons.add(butEquals);
	buttons.add(butPlus);
	buttons.add(butMinus);
	buttons.add(butPeriod);
	
	for (JButton button : buttons){
		button.addActionListener(lfButton);
	}
	resultField.addActionListener(lfText);
}
	
/*
 * Sets grid and overall appearance of calculator
 */
private void setCalcAppearance() {
	gridConstraints.insets = new Insets(5, 5, 5, 5);
	gridConstraints.anchor = GridBagConstraints.CENTER;
	gridConstraints.fill = GridBagConstraints.BOTH;	
	
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 1;
	gridConstraints.gridwidth = 1;
	gridConstraints.gridheight = 1;
	gridConstraints.weightx = 10;
	gridConstraints.weighty = 50;
	thePanel.add(clearAll, gridConstraints);
	gridConstraints.gridwidth = 5;
	gridConstraints.gridx = 5;
	thePanel.add(resultField, gridConstraints);
	gridConstraints.gridwidth = 1;
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 2;
	thePanel.add(butNum[1], gridConstraints);
	gridConstraints.gridx = 5;
	thePanel.add(butNum[2], gridConstraints);
	gridConstraints.gridx = 9;
	thePanel.add(butNum[3], gridConstraints);
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 3;
	thePanel.add(butNum[4], gridConstraints);
	gridConstraints.gridx = 5;
	thePanel.add(butNum[5], gridConstraints);
	gridConstraints.gridx = 9;
	thePanel.add(butNum[6], gridConstraints);
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 4;
	thePanel.add(butNum[7], gridConstraints);
	gridConstraints.gridx = 5;
	thePanel.add(butNum[8], gridConstraints);
	gridConstraints.gridx = 9;
	thePanel.add(butNum[9], gridConstraints);
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 5;
	thePanel.add(butPlus, gridConstraints);
	gridConstraints.gridx = 5;
	thePanel.add(butNum[0], gridConstraints);
	gridConstraints.gridx = 9;
	thePanel.add(butMinus, gridConstraints);
	gridConstraints.gridy = 6;
	gridConstraints.gridx = 1;
	thePanel.add(butMultiply, gridConstraints);
	gridConstraints.gridx = 5;
	thePanel.add(butDivide, gridConstraints);
	gridConstraints.gridx = 9;
	thePanel.add(butPeriod, gridConstraints);
	gridConstraints.gridx = 13;
	gridConstraints.gridy = 4;
	gridConstraints.gridheight = 3;
	thePanel.add(butEquals, gridConstraints);
	gridConstraints.gridy = 1;
	gridConstraints.gridheight = 3;
	thePanel.add(butDeco1, gridConstraints);
}

/*
 * Creates JButtons
 */
private void initCalcButtons() {
	
	for(int i=0; i<butNum.length; i++){
		butNum[i] = new JButton(Integer.toString(i));
	}
	
	butPlus = new JButton("+"); 
	setButtonMiscs(butPlus);
	
	butMinus = new JButton("-");
	setButtonMiscs(butMinus);
	
	clearAll = new JButton("C");
	setButtonMiscs(clearAll);
	
	butDivide = new JButton("/");
	setButtonMiscs(butDivide);
	
	butMultiply = new JButton("*");
	setButtonMiscs(butMultiply);
	
	butEquals = new JButton("=");
	setButtonMiscs(butEquals);
	
	butPeriod = new JButton(".");
	setButtonMiscs(butPeriod);
	
	butDeco1 = new JButton();
	butDeco1.setEnabled(false);
}

/*
 * Changes color, fonts etc. of buttons
 */
private JButton setButtonMiscs(JButton b){
	b.setForeground(Color.WHITE); 
	b.setFont(new Font("Tahoma", Font.BOLD, 16));
	b.setBackground(new Color(59, 89, 182));
	return b;
}

private class ListenForButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if( event.getSource() == butNum[1]){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("1");
				endOfEquation = false; // When true, it indicates that next number pressed will clear the textfield and place that numer
				 if (calcType!=null) bool = false; // bool is true after "=", +*-/, to determin when to clear textfield when number is pressed 
			} else {resultField.replaceSelection("1");} // Number "1" is placed at the end of the line
		}
		
		if( event.getSource() == butNum[2] ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("2");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("2");}
		}
		
		if( event.getSource() == but3 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("3");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("3");}
		}
		
		if( event.getSource() == but4 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("4");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("4");}
		}
		
		if( event.getSource() == but5 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("5");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("5");} 
		}
		
		if( event.getSource() == but6 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("6");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("6");}
		}
		
		if( event.getSource() == but7 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("7");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("7");}
		}
		
		if( event.getSource() == but8 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("8");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("8");}
		}
		
		if( event.getSource() == but9 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("9");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("9");}
		}
		
		if( event.getSource() == but0 ){
			if( resultField.getText().equals("0") || bool == true && (calcType!=null || endOfEquation == true) ){
				resultField.setText("0");
				endOfEquation = false;
				 if (calcType!=null) bool = false;
			} else {resultField.replaceSelection("0");}
		}
		
		if( event.getSource() == clearAll ){
			resultField.setText("0");
			currentlyComputed = 0.00;
			isPeriod = false;
			calcType = null;
			bool = true; // ????
			endOfEquation = true;
		}
		
		if( event.getSource() == butMultiply){
			currentlyComputed = Double.parseDouble(resultField.getText());
			System.out.println(resultField.getText());
			System.out.println(currentlyComputed);
			calcType = CalculationType.MULTIPLY;
			isPeriod = false;
			bool = true;
		}
		
		if( event.getSource() == butDivide){
			currentlyComputed = Double.parseDouble(resultField.getText());
			System.out.println(resultField.getText());
			System.out.println(currentlyComputed);
			calcType = CalculationType.DIVIDE;
			isPeriod = false;
			bool = true;
		}
		
		if( event.getSource() == butPlus){
			currentlyComputed = Double.parseDouble(resultField.getText());
			System.out.println(resultField.getText());
			System.out.println(currentlyComputed);
			calcType = CalculationType.PLUS;
			isPeriod = false;
			bool = true;
		}
		
		if( event.getSource() == butMinus){
			currentlyComputed = Double.parseDouble(resultField.getText());
			System.out.println(resultField.getText());
			System.out.println(currentlyComputed);
			calcType = CalculationType.MINUS;
			isPeriod = false;
			bool = true;
		}
		
		if( event.getSource() == butPeriod){
			if (isPeriod == false) {
				resultField.replaceSelection(".");
				System.out.println("Period");
			} else {
				System.out.println("Period jest juz obecny");
			}
			
			isPeriod = true;
		}			
		
		
		if(event.getSource() == butEquals){
			
			if (calcType != null){ // Error occured when "=" was pressed at the beginning of the program
			
				switch (calcType) {
			case MULTIPLY:
				System.out.println("Multiplying");
				currentlyComputed = multiply(currentlyComputed,
						Double.parseDouble(resultField.getText()));
				break;
			case DIVIDE:
				System.out.println("Division");
				currentlyComputed = divide(currentlyComputed,
						Double.parseDouble(resultField.getText()));
				break;
			case PLUS:
				System.out.println("Addition");
				currentlyComputed = addition(currentlyComputed,
						Double.parseDouble(resultField.getText()));
				break;
			case MINUS:
				System.out.println("Subtraction");
				currentlyComputed = subtraction(currentlyComputed,
						Double.parseDouble(resultField.getText()));
				break;
			}
			
			if ( (currentlyComputed % 1) == 0 ){ // Checks if value has decimal
				
				temp = Integer.toString((int)currentlyComputed); // should get rid of .0
				
				//DecimalFormat df = new DecimalFormat("0"); //"0.##########"
				//textResult.setText(df.format(curCompute));
				//int_curCompute = Double.toString(curCompute);
				//textResult.setText(int_curCompute);
						//firstDouble=(Double.parseDouble(String.valueOf(display.getText())));
				//DecimalFormat df = new DecimalFormat("#,###,##0"); //"0.##########"
				//textResult.setText(df.format(curCompute));
				//textResult.setText(String.format("%.2f", curCompute));
				//textResult.setText(String.valueOf(Double.valueOf(df.format(curCompute))));
				//textResult.setText(curCompute.intValue()+"");
				//textResult.setText(Double.toString(curCompute));
					//setText(num.intValue()+"")
				//textResult.setText(String.format( "Value of curCompute: %.000f", curCompute ));
				//textResult.setText(Integer.toString((int)curCompute));
					//.setText( String.format( "Value of a: %.2f", a ) );
				System.out.println("No decimal places");
				
			} else {
				temp = Double.toString(currentlyComputed);
				
				System.out.println("Has decimal places");
				isPeriod = true;
			}
			
			resultField.setText( temp );
			bool = true;
			isPeriod = false;
			endOfEquation = true; // When number is pressed, the result of previous equation is cleared
								  // When +-*/ is pressed, the calculation proceeds 
			} // End of "if (calcType != null)"
		}
		
	} // End of action listener - button listener

	private double multiply(double double1, double double2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(double1));
		BigDecimal bd2 = new BigDecimal(Double.toString(double2));
		return bd1.multiply(bd2).doubleValue();
	}
	
		//ROUND_HALF_EVEN: 
		//Round half even will round as normal. However, when the rounding digit is 5, 
		//it will round down if the digit to the left of the 5 is even and up otherwise.
	private double divide(double double1, double double2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(double1));
		BigDecimal bd2 = new BigDecimal(Double.toString(double2));
		return bd1.divide(bd2, 8 , BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
	
	private double addition(double double1, double double2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(double1));
		BigDecimal bd2 = new BigDecimal(Double.toString(double2));
		return bd1.add(bd2).doubleValue();
	}		
	
	private double subtraction(double double1, double double2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(double1));
		BigDecimal bd2 = new BigDecimal(Double.toString(double2));
		return bd1.subtract(bd2).doubleValue();
	}	
}

private class ListenForText implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		double valueJText = Double.parseDouble(resultField.getText());
		System.out.println("Listen for text: " + valueJText);
	}
}

}
