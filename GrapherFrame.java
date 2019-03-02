package Tests;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
public class GrapherFrame extends JFrame{
	
	public static ArrayList<Point> DNE;
	
	public static ArrayList<Point> zero;
	public static ArrayList<Point> firstDerivativeZero;
	public static ArrayList<Point> secondDerivativeZero;
	public static ArrayList<Point> relativeExtrema;
	public static ArrayList<Point> inflectionPoints;
	public static double xmin;
	public static double xmax;
	public static double ymin;
	public static double ymax;
	public static double evalUnit;
	public static double yInterval;
	public static int x0;
	public static int y0;
	public static ArrayList<Point> originalFunction = new ArrayList<Point>();
	public static ArrayList<Point> originalFunctionPixels;
	public static ArrayList<Point> firstDerivative = new ArrayList<Point>();
	public static ArrayList<Point> firstDerivativePixels;
	public static ArrayList<Point> secondDerivative = new ArrayList<Point>();
	public static ArrayList<Point> secondDerivativePixels;
	public static ArrayList<Double> asymptotes;
	public static ArrayList<Double> holes;
	//private static final int FRAME_WIDTH = 250;
	//private static final int FRAME_HEIGHT = 500;
	private int FRAME_WIDTH = 400;
	private int FRAME_HEIGHT = 200;
	private JLabel expLabel;
	private JTextField expField;
	private JLabel xminLabel;
	private JTextField xminField;
	private JLabel xmaxLabel;
	private JTextField xmaxField;
	private JLabel yminLabel;
	private JTextField yminField;
	private JLabel ymaxLabel;
	private JTextField ymaxField;
	private JButton button;
	private JPanel panel;
	final static boolean shouldFill = true;
	GridBagConstraints c = null;

	public GrapherFrame() {
		c = new GridBagConstraints();
		createTextField();
		createButton();
		createPanel();
		
		setTitle("Graphing Calculator");
		setLayout(new GridLayout(1,2));
		//setLayout(new FlowLayout(FlowLayout.LEFT));
		//setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
	private void createTextField() {
		final int FIELD_WIDTH = 15;
		
		expLabel = new JLabel("Type Expression Here: ",JLabel.TRAILING);
		expField = new JTextField(FIELD_WIDTH);
		expLabel.setLabelFor(expField);
		expField.setText("");
		xminLabel = new JLabel("X-Min: ",JLabel.TRAILING);
		xminField = new JTextField(FIELD_WIDTH);
		xminLabel.setLabelFor(xminField);
		xminField.setText("");
		xmaxLabel = new JLabel("X-Max: ",JLabel.TRAILING);
		xmaxField = new JTextField(FIELD_WIDTH);
		xmaxLabel.setLabelFor(xmaxField);
		xmaxField.setText("");
		yminLabel = new JLabel("Y-Min: ",JLabel.TRAILING);
		yminField = new JTextField(FIELD_WIDTH);
		yminLabel.setLabelFor(yminField);
		yminField.setText("");
		ymaxLabel = new JLabel("Y-Max: ",JLabel.TRAILING);
		ymaxField = new JTextField(FIELD_WIDTH);
		ymaxLabel.setLabelFor(ymaxField);
		ymaxField.setText("");
		
		

		
	}
	
	public static ArrayList<Point> convertToPixels(ArrayList<Point> function) {
		ArrayList<Point> pixelFunction = new ArrayList<Point>();
		
		for (int i = 0; i < function.size();i++) {
			Point p = function.get(i);
			Point pixelP = new Point(i, (int) (y0-(p.y/yInterval)));
			pixelFunction.add(pixelP);
			
		}
		return pixelFunction;
	}
	
	
	public static Point convertToPixels(Point p) {
		Point pixelPoint = new Point(x0 + p.x/evalUnit, y0 -(p.y/yInterval) );	
		return pixelPoint;
	}
	
	private void createButton() {
		button = new JButton("Graph");
		
		class EvaluateListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				String expression = expField.getText();
				Poly poly = new Poly(expression);
				xmin = Double.parseDouble(xminField.getText());
				xmax = Double.parseDouble(xmaxField.getText());
				ymin = Double.parseDouble(yminField.getText());
				ymax = Double.parseDouble(ymaxField.getText());
				evalUnit = (Math.abs(xmin) + Math.abs(xmax))/600;
				yInterval = (Math.abs(ymin) + Math.abs(ymax))/600;
				originalFunction = poly.Evaluate(xmin, xmax, evalUnit);
				x0 = (int) ((Math.abs(xmin))/evalUnit);
				y0 = (int) ((Math.abs(ymax))/yInterval);
			    originalFunctionPixels = convertToPixels(originalFunction);
			    for (Point p : originalFunction) {
			    	double x = p.x;
			    	double y = Poly.takeDerivative(p.x);
			    	Point pDeriv = new Point(x, y);
			    	firstDerivative.add(pDeriv);
                }
			    firstDerivativePixels = convertToPixels(firstDerivative);
			    for (Point p : firstDerivative) {
			    	double x = p.x;
			    	double y = Poly.takeSecondDerivative(p.x);
			    	Point pDeriv = new Point(x, y);
			    	secondDerivative.add(pDeriv);
			    } 
			    secondDerivativePixels = convertToPixels(secondDerivative);
				Solver solver = new Solver();
				zero = solver.solve(originalFunction);
				firstDerivativeZero = solver.solve(firstDerivative);
				relativeExtrema = solver.findRelativeExtrema(firstDerivativeZero);
				secondDerivativeZero = solver.solve(secondDerivative);
				inflectionPoints = solver.findInflectionPoints(secondDerivativeZero);
				solver.findGaps(originalFunction);
				JFrame frame = new JFrame();
				//frame.getContentPane().add(new GrapherPanel());
				
			/*	Container contentPane=frame.getContentPane();
				//contentPane.add(panel);
				//contentPane.add(new GrapherPanel());
				frame.setTitle("Graph of " + expression);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setSize(600, 600);
				frame.setVisible (true);
				
				//panel.setVisible(false);
				
				//panel.add(new JPanel());
				//panel.add(expLabel);
				//panel.revalidate();
				//frame.setLocation(250, 150);     
				frame.setLayout(new BorderLayout());
				/*frame.add(panel, BorderLayout.WEST);*/
				
				//GrapherPanel gp = new GrapherPanel();
				
				JPanel gp = GrapherPanel.getInstance();
				Dimension screenSize = new Dimension(1200,700);
				setSize(screenSize);
				//add(gp);
				add(gp,getContentPane());
				//setSize(1200, 800);
				
				
				revalidate();
				repaint();
				//pack();

			}
		}
		
		ActionListener listener = new EvaluateListener();
		button.addActionListener(listener);
	}
	
	private JPanel createPanel() {
		
		Dimension dim = new Dimension(350,200);
		panel = new JPanel(new GridBagLayout());
		
	    if (shouldFill) {
	       	c.fill = GridBagConstraints.HORIZONTAL;
	    }
		
	    c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.WEST;
	    
	    c.gridx = 0;
	    c.gridy = 0;
		panel.add(expLabel,c);
	
	    
	    c.gridx = 1;
	    c.gridy = 0;
		panel.add(expField,c);
	
	    
	    c.gridx = 0;
	    c.gridy = 1;
		panel.add(xminLabel,c);
	
	    
	    c.gridx = 1;
	    c.gridy = 1;
		panel.add(xminField,c);
	
	    
	    c.gridx = 0;
	    c.gridy = 2;
		panel.add(xmaxLabel,c);
	
	    
	    c.gridx = 1;
	    c.gridy = 2;
		panel.add(xmaxField,c);
	
	    
	    c.gridx = 0;
	    c.gridy = 3;
		panel.add(yminLabel,c);
	
	    
	    c.gridx = 1;
	    c.gridy = 3;
		panel.add(yminField,c);
	
	    
	    c.gridx = 0;
	    c.gridy = 4;
		panel.add(ymaxLabel,c);
	
	    
	    c.gridx = 1;
	    c.gridy = 4;
		panel.add(ymaxField,c);
	
	    
	    c.gridx = 0;
	    c.gridy = 5;
		panel.add(button,c);
		//panel.setBackground(Color.RED);
		panel.setMaximumSize(dim);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.WEST;
		
		//panel.setMaximumSize(dim);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
		setSize(dim);
		add(panel);
		//pack();
		
		return panel;
	
	}
}
