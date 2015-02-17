

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Kalkulator extends JFrame {
	
	//https://www.newthinktank.com/2012/03/java-video-tutorial-22/
	//https://www.java.net/forum/topic/jdk/java-se/java-calculator-problem
	//http://gobruen.com/progs/j/Calculator.java
	
	//https://www.cs.purdue.edu/homes/apm/listen/JListenDemos/Calculator.java
	//http://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html

	// Heads first Java str 231
	// 
	// Nie dziala, ze jak nie ma decimal places, to nie ucina tego .0
	// zrobic tak zeby setText(Integer.toString robilo inta bez .0
	
	// http://www.opentaps.org/docs/index.php/How_to_Use_Java_BigDecimal:_A_Tutorial  <== !!!!!!
	// http://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html
	// http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java

	//ROUND_HALF_EVEN: 
	//Round half even will round as normal. However, when the rounding digit is 5, 
	//it will round down if the digit to the left of the 5 is even and up otherwise.
	
	JButton but1, but2, but3, but4, but5, but6, but7, but8, but9, but0,
			butPlus, butMinus, clearAll, butDivide, butMultiply, butEquals,
			butPeriod;

	JTextField textResult;
	ListenForButton lfButton;
	int num1, num2, computePick;  // computePick: 1=multiply, 2=divide, 3=plus, 4=minus
	String int_curCompute, temp;
	double curCompute;
	boolean bool = true, isMinus, isPlus, isMultiply, isDivide, isPeriod = false;
	
	enum CalculationType {
		MULTIPLY (1) , DIVIDE (2), PLUS (3), MINUS (4);
			int val;
			CalculationType(int val){
				this.val = val;
				}
	};
	CalculationType calcType;

	public static void main(String[] args) {

		new Kalkulator();

	}

	public Kalkulator() {

		// Create the frame, position it and handle closing it

		System.out.println("Witam. Pozdrawiam.");
		
		this.setSize(250, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator");

		JPanel thePanel = new JPanel();

		// Create a Grid Layout with as many rows as needed
		// and 3 columns. The last 2 parameters create a
		// horizontal gap of 2 pixels and a vertical gap of
		// 2 pixels.

		// thePanel.setLayout(new GridLayout(0,3,2,2));

		thePanel.setLayout(new GridBagLayout());

		// You create a GridBagContraints object that defines
		// defaults for your components

		GridBagConstraints gridConstraints = new GridBagConstraints();

		// Define the x position of the component

		gridConstraints.gridx = 1;

		// Define the y position of the component

		gridConstraints.gridy = 1;

		// Number of columns the component takes up

		gridConstraints.gridwidth = 1;

		// Number of rows the component takes up

		gridConstraints.gridheight = 1;

		// Gives the layout manager a hint on how to adjust
		// component width (0 equals fixed)

		gridConstraints.weightx = 10;

		// Gives the layout manager a hint on how to adjust
		// component height (0 equals fixed)

		gridConstraints.weighty = 50;

		// Defines padding top, left, bottom, right

		gridConstraints.insets = new Insets(5, 5, 5, 5);

		// Defines where to place components if they don't
		// fill the space: CENTER, NORTH, SOUTH, EAST, WEST
		// NORTHEAST, etc.

		gridConstraints.anchor = GridBagConstraints.CENTER;

		// How should the component be stretched to fill the
		// space: NONE, HORIZONTAL, VERTICAL, BOTH

		gridConstraints.fill = GridBagConstraints.BOTH;

		textResult = new JTextField("0", 15);
		textResult.setEditable(false); // user shouldn`t change it
		
		// Defines the font to use in the text field

		Font font = new Font("Helvetica", Font.PLAIN, 18);
		textResult.setFont(font);
		
		but1 = new JButton("1");
		but2 = new JButton("2");
		but3 = new JButton("3");
		but4 = new JButton("4");
		but5 = new JButton("5");
		but6 = new JButton("6");
		but7 = new JButton("7");
		but8 = new JButton("8");
		but9 = new JButton("9");
		butPlus = new JButton("+");
		but0 = new JButton("0");
		butMinus = new JButton("-");
		clearAll = new JButton("C");
		butDivide = new JButton("/");
		butMultiply = new JButton("*");
		butEquals = new JButton("=");
		butPeriod = new JButton(".");

		butEquals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				textResult.setText(Double.toString(curCompute)); 
				
			}
			
		});		
		
		ListenForText lfText = new ListenForText();
		ListenForButton lfButton = new ListenForButton();
		
		
		but1.addActionListener(lfButton);
		but2.addActionListener(lfButton);
		but3.addActionListener(lfButton);
		but4.addActionListener(lfButton);
		but5.addActionListener(lfButton);
		but6.addActionListener(lfButton);
		but7.addActionListener(lfButton);
		but8.addActionListener(lfButton);
		but9.addActionListener(lfButton);
		but0.addActionListener(lfButton);
		textResult.addActionListener(lfText);
		clearAll.addActionListener(lfButton);
		butMultiply.addActionListener(lfButton);
		butDivide.addActionListener(lfButton);
		butEquals.addActionListener(lfButton);
		butPlus.addActionListener(lfButton);
		butMinus.addActionListener(lfButton);
		butPeriod.addActionListener(lfButton);
		
		
		
		thePanel.add(clearAll, gridConstraints);
		gridConstraints.gridwidth = 5;
		gridConstraints.gridx = 5;
		thePanel.add(textResult, gridConstraints);
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		thePanel.add(but1, gridConstraints);
		gridConstraints.gridx = 5;
		thePanel.add(but2, gridConstraints);
		gridConstraints.gridx = 9;
		thePanel.add(but3, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 3;
		thePanel.add(but4, gridConstraints);
		gridConstraints.gridx = 5;
		thePanel.add(but5, gridConstraints);
		gridConstraints.gridx = 9;
		thePanel.add(but6, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		thePanel.add(but7, gridConstraints);
		gridConstraints.gridx = 5;
		thePanel.add(but8, gridConstraints);
		gridConstraints.gridx = 9;
		thePanel.add(but9, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 5;
		thePanel.add(butPlus, gridConstraints);
		gridConstraints.gridx = 5;
		thePanel.add(but0, gridConstraints);
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
		gridConstraints.gridheight = 2;
		thePanel.add(butEquals, gridConstraints);

		this.add(thePanel);

		this.setVisible(true);
		

		
	} // END OF Lesson29 CONSTRUCTOR
	
	
	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			if( event.getSource() == but1){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("1");
					 if (calcType!=null) bool = false; 
				} else {textResult.replaceSelection("1");}
			}
			
			if( event.getSource() == but2 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("2");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("2");}
			}
			
			if( event.getSource() == but3 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("3");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("3");}
			}
			
			if( event.getSource() == but4 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("4");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("4");}
			}
			
			if( event.getSource() == but5 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("5");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("5");} 
			}
			
			if( event.getSource() == but6 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("6");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("6");}
			}
			
			if( event.getSource() == but7 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("7");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("7");}
			}
			
			if( event.getSource() == but8 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("8");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("8");}
			}
			
			if( event.getSource() == but9 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("9");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("9");}
			}
			
			if( event.getSource() == but0 ){
				if( textResult.getText().equals("0") || bool == true && calcType!=null){
					textResult.setText("0");
					 if (calcType!=null) bool = false;
				} else {textResult.replaceSelection("0");}
			}
			
			if( event.getSource() == clearAll ){
				textResult.setText("0");
				curCompute = 0.00;
				isPeriod = false;
				calcType = null;
				bool = true; // ????
			}
			
			if( event.getSource() == butMultiply){
				curCompute = Double.parseDouble(textResult.getText());
				//System.out.println("Multiply");
				System.out.println(textResult.getText());
				System.out.println(curCompute);
				calcType = CalculationType.MULTIPLY;
				isPeriod = false;
			}
			
			if( event.getSource() == butDivide){
				curCompute = Double.parseDouble(textResult.getText());
				//System.out.println("Divide");
				System.out.println(textResult.getText());
				System.out.println(curCompute);
				calcType = CalculationType.DIVIDE;
				isPeriod = false;
			}
			
			if( event.getSource() == butPlus){
				curCompute = Double.parseDouble(textResult.getText());
				//System.out.println("Addition");
				System.out.println(textResult.getText());
				System.out.println(curCompute);
				calcType = CalculationType.PLUS;
				isPeriod = false;
			}
			
			if( event.getSource() == butMinus){
				curCompute = Double.parseDouble(textResult.getText());
				//System.out.println("Subtraction");
				System.out.println(textResult.getText());
				System.out.println(curCompute);
				calcType = CalculationType.MINUS;
				isPeriod = false;
			}
			
			if( event.getSource() == butPeriod){
				if( isPeriod == false ){
					textResult.replaceSelection(".");
					System.out.println("Period");
				} else {System.out.println("Period jest juz obecny");}
				
				isPeriod = true;
				
			}			
			
			
			if( event.getSource() == butEquals){
				/// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				 switch (calcType) {
		            case MULTIPLY:
		                    System.out.println("Multiplying");
		                    curCompute = multiply(curCompute, Double.parseDouble(textResult.getText()));		                    
		                    break;
		            case DIVIDE:
		                    System.out.println("Division");
		                    curCompute = divide(curCompute, Double.parseDouble(textResult.getText()));
		                    break;
		            case PLUS:
		                    System.out.println("Addition");
		                    curCompute = addition(curCompute, Double.parseDouble(textResult.getText()));
		                    break;
		            case MINUS:
		                    System.out.println("Subtraction");
		                    curCompute = subtraction(curCompute, Double.parseDouble(textResult.getText()));
		                    break;
		    }
				
				if ( (curCompute % 1) == 0 ){ // Checks if value has decimal
					
					temp = Integer.toString((int)curCompute); // should get rid of .0
					
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
					//System.out.println(curCompute + " No decimal places " + (int)curCompute);
			        //System.out.printf("Floating point number with 3 decimal digits: %.3f\n",1.21312939123);
			        //System.out.printf("Floating point number with 8 decimal digits: %.0f\n",1.21312939123);
					
				} else {
					temp = Double.toString(curCompute);
					
					System.out.println("Has decimal places");
					isPeriod = true;
				}
				
				textResult.setText( temp );
				bool = true;
				isPeriod = false;
				
			}
			
		}

		private double multiply(double double1, double double2) {
			BigDecimal bd1 = new BigDecimal(Double.toString(double1));
			BigDecimal bd2 = new BigDecimal(Double.toString(double2));
			return bd1.multiply(bd2).doubleValue();
		}
		
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
			// TODO Auto-generated method stub
			double valueJText = Double.parseDouble(textResult.getText());
			System.out.println("Listen for text: " + valueJText);
		}
		
	}
	
} // END OF Lesson29 CLASS

