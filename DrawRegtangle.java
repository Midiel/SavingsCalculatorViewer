/*
 * Midiel Rodriguez
 */
package savingscalculatorviewer;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author midiel rodriguez
 */
public class DrawRegtangle extends JPanel {
    
    private double[] values;        // Values for the graph
    private String[] years;         // To hold the years
    private String title;           // To hold the titile of the graph
    
    /**
     * Default construction accepts not argument
     */
    public DrawRegtangle () {
        values = new double[1];
        values[0] = 0;
        years = new String[1];
        years[0] = "";
        title = "";
    }
    
    /**
     * Constructor that accepts the values, the years as a string and title
     * 
     * @param values the y values for the graph
     * @param years the x values for the graph
     * @param title the title for the graph
     */
    public DrawRegtangle (double[] values, String[] years, String title) {
        this.values = values;
        this.years = years;
        this.title = title;
    }

    /**
     * Setter to update the values of the graph
     * 
     * @param values the y values for the graph
     * @param years the x values for the graph
     * @param title the title for the graph
     */
    public void setValues(double[] values, String[] years, String title) {
        this.values = values;
        this.years = years;
        this.title = title;
    }
    
    /**
     * To catch exceptions
     * 
     * @param cardLayout a cardLayout
     */
    DrawRegtangle(CardLayout cardLayout) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * To paint the graph
     * 
     * @param g the graphics variable
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i])
              minValue = values[i];
            if (maxValue < values[i])
              maxValue = values[i];
        }

        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length;

        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue) {
            return;
        }
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        // Loop to paint the graph
        for (int i = 0; i < values.length; i++) {
            
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0)
              valueY += (int) ((maxValue - values[i]) * scale);
            else {
              valueY += (int) (maxValue * scale);
              height = -height;
            }
            
            // Set the color to red
            g.setColor(Color.red);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(years[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(years[i], x, y);
        }
    }   
}
