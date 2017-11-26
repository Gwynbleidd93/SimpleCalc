/**
 *
 *  @author Celejewski Paweł S15799
 *
 */

package zadanie1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calc extends JFrame implements ActionListener {

	JButton[] numButtons = new JButton[10];
	JButton[] operationsButtons = new JButton[6];
	GridLayout glButtons = new GridLayout(4, 4, 20, 20);
	GridLayout glScreen = new GridLayout(2, 1, 10, 10);
	GridLayout glGeneral = new GridLayout(2, 1);
	JLabel lOperation, lResult;
	JPanel PButtons, PScreen, PGeneral;

	double arg1, arg2, outcome = 0.0;
	char operand;
	String print;
	String output = "";
	Pattern pattern = Pattern.compile("[\\d.]+");

	public Calc() {
		super("Kalkulator");
		setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();

		for (int i = 0; i < numButtons.length; i++) {
			numButtons[i] = new JButton("n" + i);
			numButtons[i].addActionListener(this);
		}

		operationsButtons[0] = new JButton("add");
		operationsButtons[0].addActionListener(this);
		operationsButtons[1] = new JButton("rem");
		operationsButtons[1].addActionListener(this);
		operationsButtons[2] = new JButton("mul");
		operationsButtons[2].addActionListener(this);
		operationsButtons[3] = new JButton("div");
		operationsButtons[3].addActionListener(this);
		operationsButtons[4] = new JButton("eq");
		operationsButtons[4].addActionListener(this);
		operationsButtons[5] = new JButton("clr");
		operationsButtons[5].addActionListener(this);

		// ============================================

		PButtons = new JPanel(glButtons);
		PButtons.setVisible(true);
		add(PButtons);

		PButtons.add(numButtons[1]);
		PButtons.add(numButtons[2]);
		PButtons.add(numButtons[3]);
		PButtons.add(operationsButtons[0]);

		PButtons.add(numButtons[4]);
		PButtons.add(numButtons[5]);
		PButtons.add(numButtons[6]);
		PButtons.add(operationsButtons[1]);

		PButtons.add(numButtons[7]);
		PButtons.add(numButtons[8]);
		PButtons.add(numButtons[9]);
		PButtons.add(operationsButtons[2]);

		PButtons.add(operationsButtons[5]);
		PButtons.add(numButtons[0]);
		PButtons.add(operationsButtons[4]);
		PButtons.add(operationsButtons[3]);

		// ================================================

		PScreen = new JPanel(glScreen);
		PScreen.setVisible(true);
		add(PScreen);

		lOperation = new JLabel("");
		lOperation.setFont(new Font("Dialog", Font.PLAIN, 18));
		lOperation.setForeground(Color.GRAY);

		lResult = new JLabel("0.0");
		lResult.setFont(new Font("Dialog", Font.BOLD, 24));
		lResult.setForeground(Color.BLACK);

		lOperation.setHorizontalAlignment(JLabel.CENTER);
		lResult.setHorizontalAlignment(JLabel.CENTER);

		PScreen.add(lOperation);
		PScreen.add(lResult);

		// ===============================================

		PGeneral = new JPanel(glGeneral);
		PGeneral.setVisible(true);
		add(PGeneral);

		PGeneral.add(PScreen);
		PGeneral.add(PButtons);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	// ========================

	public double add(double ar1, double ar2) {
		this.arg1 = ar1;
		this.arg2 = ar2;
		outcome = ar1 + ar2;
		print = arg1 + " + " + arg2 + " = " + outcome;
		return outcome;

	}

	public double substract(double ar1, double ar2) {
		this.arg1 = ar1;
		this.arg2 = ar2;
		outcome = ar1 - ar2;
		print = arg1 + " - " + arg2 + " = " + outcome;
		return outcome;
	}

	public double multiply(double ar1, double ar2) {
		this.arg1 = ar1;
		this.arg2 = ar2;
		outcome = ar1 * ar2;
		print = arg1 + " * " + arg2 + " = " + outcome;
		return outcome;
	}

	public double divide(double ar1, double ar2) {
		this.arg1 = ar1;
		this.arg2 = ar2;

		if (ar2 == 0) {
			print = arg1 + " / " + arg2 + " = ERR";
			return Double.longBitsToDouble(0x7ff8000000000000L);

		}

		else {

			outcome = ar1 / ar2;
			print = arg1 + " / " + arg2 + " = " + outcome;
			return outcome;
		}

	}

	public String toString() {
		return print;
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		for (int i = 0; i < numButtons.length; i++) {
			if (source == numButtons[i]) {
				output = output + i;
				lOperation.setText(output);
			}
		}

		if (source == operationsButtons[0]) {

			operand = '+';
			output = output + " " + operand + " ";
			lOperation.setText(output);
		}

		if (source == operationsButtons[1]) {

			operand = '-';
			output = output + " " + operand + " ";
			lOperation.setText(output);
		}

		if (source == operationsButtons[2]) {

			operand = '*';
			output = output + " " + operand + " ";
			lOperation.setText(output);
		}

		if (source == operationsButtons[3]) {

			operand = '/';
			output = output + " " + operand + " ";
			lOperation.setText(output);
		}

		// znak równa się
		if (source == operationsButtons[4]) {
			Matcher matcher = pattern.matcher(output);

			matcher.find();
			arg1 = Double.parseDouble(matcher.group());
			matcher.find();
			arg2 = Double.parseDouble(matcher.group());

			output = output + " =";
			lOperation.setText(output);

			switch (operand) {
			case '+': {
				outcome = add(arg1, arg2);
				break;
			}
			case '-': {
				outcome = substract(arg1, arg2);
				break;
			}
			case '*': {
				outcome = multiply(arg1, arg2);
				break;
			}
			case '/': {
				outcome = divide(arg1, arg2);
				break;
			}
			default: {
				break;
			}
			}

			lResult.setText(Double.toString(outcome));
			System.out.println(output + " " + outcome);

		}
		// znak clear
		if (source == operationsButtons[5]) {

			output = "";
			arg1 = 0;
			arg2 = 0;
			outcome = 0;

			lResult.setText(Double.toString(outcome));
			lOperation.setText("");
		}

	}

}
