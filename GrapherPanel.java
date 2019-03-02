package Tests;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GrapherPanel extends JPanel {
	private int x0;
	private int y0;
	
    private static final GrapherPanel GpInstance = new GrapherPanel();
	
    public static GrapherPanel getInstance(){
    	  return GpInstance;
    }
    
	public void paint(Graphics g) {
		super.paint(g);	
		if (GrapherFrame.xmax + GrapherFrame.xmin < GrapherFrame.xmax) {
			x0 = GrapherFrame.x0;
		}
		else  {
			x0 = 0;
		}
		if (GrapherFrame.ymax + GrapherFrame.ymin < GrapherFrame.ymax) {
			y0 = GrapherFrame.y0;
		}
		else  { 
			y0 = 0;
		}
		
		g.setColor(Color.PINK);
		for (Point p : GrapherFrame.secondDerivative) {
			Point fx = new Point(p.x, Poly.Evaluate(p.x));
			if (p.y > 0) {
				g.drawLine((int)GrapherFrame.convertToPixels(fx).x, 0,(int)GrapherFrame.convertToPixels(fx).x, (int) GrapherFrame.convertToPixels(fx).y);
			}
			else if (p.y < 0) {
				g.drawLine((int)GrapherFrame.convertToPixels(fx).x, 600,(int)GrapherFrame.convertToPixels(fx).x, (int) GrapherFrame.convertToPixels(fx).y);
			}
		}
		g.setColor(Color.RED);
		for (int i = 0; i <GrapherFrame.originalFunctionPixels.size()-1; i++) {
			Point p0 = GrapherFrame.originalFunctionPixels.get(i);
			Point p = GrapherFrame.originalFunctionPixels.get(i+1);
			if (!Double.isNaN(p.y) && !Double.isNaN(p0.y)) {
				g.drawLine((int)p0.x, (int)p0.y, (int)p.x, (int)p.y);
			}
			
		}
		g.setColor(Color.BLUE);
		for (int i = 0; i <GrapherFrame.firstDerivativePixels.size()-1; i++) {
			Point p0 = GrapherFrame.firstDerivativePixels.get(i);
			Point p = GrapherFrame.firstDerivativePixels.get(i+1);
			if (!Double.isNaN(p.y) && !Double.isNaN(p0.y)) {
				g.drawLine((int)p0.x, (int)p0.y, (int)p.x, (int)p.y);
			}
			
		}
		g.setColor(Color.GREEN);
		for (int i = 0; i <GrapherFrame.secondDerivativePixels.size()-1; i++) {
			Point p0 = GrapherFrame.secondDerivativePixels.get(i);
			Point p = GrapherFrame.secondDerivativePixels.get(i+1);
			if (!Double.isNaN(p.y) && !Double.isNaN(p0.y)) {
				g.drawLine((int)p0.x, (int)p0.y, (int)p.x, (int)p.y);
			}
			
		}
		g.setColor(Color.YELLOW);
		for (Point p : GrapherFrame.zero) {
			Point pPixel = GrapherFrame.convertToPixels(p);
			g.fillRect((int) pPixel.x, (int)pPixel.y, 4, 4);
		}
//		for (Point p : GrapherFrame.firstDerivativeZero) {
//			Point pPixel = GrapherFrame.convertToPixels(p);
//			g.fillRect((int) pPixel.x, (int)pPixel.y, 3, 3);
//		}
		
		
		g.setColor(Color.CYAN);
		for (Point p : GrapherFrame.relativeExtrema) {
			Point pPixel = GrapherFrame.convertToPixels(p);
			g.fillRect((int)pPixel.x, (int)pPixel.y, 4, 4);
		}
		g.setColor(Color.GREEN);
		if (Solver.discontinuities.size() != 0) {
			for (Point p : Solver.discontinuities ) {
				Point pPixel = GrapherFrame.convertToPixels(p);
				g.fillRect((int)pPixel.x, (int)pPixel.y, 4, 4);
			}
		}
		g.setColor(Color.CYAN);
		if ( (Solver.vertical_asymptotes.size() != 0) ) {
			for (Double x : Solver.vertical_asymptotes) {
				if (!Double.isNaN(Poly.Evaluate(x))) {
					Point p0 = new Point(x, 0);
					g.drawLine((int)GrapherFrame.convertToPixels(p0).x, 0,(int)GrapherFrame.convertToPixels(p0).x, 600);
				}
				
			}
		}
		
		g.setColor(Color.MAGENTA); 
		if (GrapherFrame.inflectionPoints.size() !=0) {
			for (Point p : GrapherFrame.inflectionPoints) {
				if (!Double.isNaN(Poly.Evaluate(p.x))) {
					Point pPixel = GrapherFrame.convertToPixels(p);
					g.drawOval((int)pPixel.x, (int)pPixel.y, 4, 4);
				}
				
			}
		}
		g.setColor(Color.BLACK);
		g.drawLine(x0, 0, x0, 600);
		g.drawLine(0, y0, 600, y0);
		for (int i = 0; i < 16; i++) {
			
		}

	}

}
