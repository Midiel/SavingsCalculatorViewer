/*
 * Midiel Rodriguez
 * Course: COP-337
 * Assignemnt: 7
 * 
 * This programs calculates the savings based on initial balance, interest rate
 * and amount of years.
 * 
 * Disclaimer:
 * I hereby certify that this code is my work and my work alone and understand
 * the syllabus regarding plagiarized code.
 * Copyright 2018 Midiel
 */
package savingscalculatorviewer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Midiel Rodriguez
 */
public class SavingsCalculatorViewer extends JFrame implements ActionListener {
    
    private JTextArea outputArea;                  // Displays yearly savings
    private JButton calcButton;                    // Triggers savings calculation
    private JFormattedTextField initSavingsField;  // Holds savings amount
    private JFormattedTextField interestRateField; // Holds interest amount
    private JFormattedTextField yearsField;        // Holds num years
    private JButton quitButton;                    // Triggers termination of GUI
    private DrawRegtangle barChart;
    private String title = "Savings";
    
   
   
    // Set up the window
    SavingsCalculatorViewer() {
        GridBagConstraints layoutConst = null; // Used to specify GUI component layout
        JScrollPane scrollPane = null;         // Container that adds a scroll bar
        JLabel initSavingsLabel = null;        // Label for savings
        JLabel interestRateLabel = null;       // Label for interest
        JLabel yearsLabel = null;              // Label for num years
        JLabel outputLabel = null;             // Label for yearly saving
       
        // Format for the savings input
        NumberFormat currencyFormat = null;

        // Format for the interest rate input
        NumberFormat percentFormat = null;

        // Format for the years input
        NumberFormat integerFormat = null;
        
        // Set frame's title
        setTitle("Savings Calculator");
        
        // Create labels
        initSavingsLabel = new JLabel("Initial Amount $:");
        interestRateLabel = new JLabel("Annual Interest Rate %:");
        yearsLabel = new JLabel("Years:");
        outputLabel = new JLabel("Total Savings:");
        
        // Create output area and add it to scroll pane
        outputArea = new JTextArea(10, 15);
        scrollPane = new JScrollPane(outputArea);
        outputArea.setEditable(false);
   
        calcButton = new JButton("Calculate");
        calcButton.addActionListener(this);
        
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        
        // Create savings field and specify the currency format
        initSavingsField = new JFormattedTextField
                               (NumberFormat.getNumberInstance());
        initSavingsField.setEditable(true);
        initSavingsField.setColumns(10); // Initial width of 10 units
        initSavingsField.setValue(0);
        
        // Create rate field and specify the percent format
        interestRateField = new JFormattedTextField
                                (NumberFormat.getNumberInstance());
        interestRateField.setEditable(true);
        interestRateField.setValue(0.0);
        
        // Create the Graph
        barChart = new DrawRegtangle();
        barChart.setPreferredSize(new Dimension(500, 300));
        barChart.setBorder(new TitledBorder("Bar Chart"));
        barChart.setVisible(true);
        
        // Create years field and specify the default number (for doubles) 
        integerFormat = NumberFormat.getIntegerInstance();
        yearsField = new JFormattedTextField(integerFormat);
        yearsField.setEditable(true);
        yearsField.setValue(0);
        
        // Use a GridBagLayout
        setLayout(new GridBagLayout());
 
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 5, 1);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(initSavingsLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 5, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(initSavingsField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 10, 5, 1);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(interestRateLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 1, 5, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(interestRateField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 10, 10, 1);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        add(yearsLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 1, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        add(yearsField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.BOTH;
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        add(calcButton, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.BOTH;
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        add(quitButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        add(outputLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 5;
        layoutConst.gridwidth = 2; // 2 cells wide
        add(scrollPane, layoutConst);
                
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 5, 10);
        layoutConst.fill = GridBagConstraints.BOTH;
        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        layoutConst.gridheight = 10;
        add(barChart, layoutConst);
    }
    
    /**
     * Action listener to handle buttons. Id the quit button is press, the
     * program ends. If the calculate button is press, it calculates the 
     * savings.
     * @param event press of the calculate and quit button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
    
        int i = 0;                   // Loop index
        double savingsDollars = 0.0; // Yearly savings
        double interestRate = 0.0;   // Annual interest rate
        int numYears = 0;            // Num years to calc savings
        
        double[] savings;           // To store the savings for the graph
        String[] year;              // To save the year for the grapgh
     
        // Get source of event (2 buttons in GUI)
        JButton sourceEvent = (JButton) event.getSource();
        
        // Decimal Formatter
        DecimalFormat dollar = new DecimalFormat("#,##0.00");

        // Get values from fields
        savingsDollars = ((Number) initSavingsField.getValue()).intValue();
        interestRate = ((Number) interestRateField.getValue()).doubleValue();
        numYears = ((Number) yearsField.getValue()).intValue();
        
        // Create the variable according to the user input
        savings = new double[numYears];
        year = new String[numYears];

        // Clear the text area
        outputArea.setText("");
        
        // Handless the event
        if (sourceEvent == calcButton) {
            // Calculate savings iteratively in a while loop
            i = 1;
            while (i <= numYears) {  
                savings[i - 1] = savingsDollars;
                year[i - 1] = "Year " + i;
                savingsDollars = savingsDollars + (savingsDollars * 
                                                   interestRate * 0.01);
                outputArea.append("Savings in year " + i +
                                  ": $" + dollar.format(savingsDollars) + "\n");              
                i = i + 1;
            }
            
            // Update the grapgh with the new values
            barChart.setValues(savings, year, title);
            barChart.repaint();
        }
        else if(sourceEvent == quitButton) {
            dispose();                      // Terminate program
        }
        return;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Creates SavingsInterestCalcFrame and its components
        SavingsCalculatorViewer myFrame = new SavingsCalculatorViewer();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);

        return;
    }   
}
