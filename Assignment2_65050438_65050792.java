package Assignment2.Assignment2CG_Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Queue;

import java.awt.geom.AffineTransform;

public class Assignment2_65050438_65050792 extends JPanel implements Runnable {
    //การเคลื่อนที่ของไข่
    double eggMove = 0;
    //ความเร็วของไข่แกนY
    double eggVelocityY = -160;

    public static void main(String[] args) {
        Assignment2_65050438_65050792 assignment2 = new Assignment2_65050438_65050792();
        JFrame f = new JFrame();
        f.add(assignment2);
        f.setTitle("Assignment 2");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);        

        (new Thread(assignment2)).start();
    }

    @Override
    public void run() {
        double lastTime = System.currentTimeMillis();
        double currentTime, elapsedTime;
        double elapsedTotalTime = 0; // เวลาที่ผ่านไปทั้งหมด

        while (true) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastTime;
            lastTime = currentTime;
            elapsedTotalTime += elapsedTime; // เพิ่มเวลาที่ผ่านไปทั้งหมด

            eggMove += eggVelocityY * elapsedTime / 1000.0;

            if(elapsedTotalTime >= 1000){
                eggVelocityY = 0;
            }



            // Display
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(601, 601, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 600, 600);

        Color pinkegg1 = new Color(237, 40, 128);
        Color pinkegg2 = new Color(235, 146, 189);
        Color pinkegg3 = new Color(239, 79, 147);


        // originalTransform
        AffineTransform originalTransform = g2.getTransform();
        g2.setColor(Color.black);

        // เคลื่อนที่เปลือกไข่
        g2.setTransform(new AffineTransform(1, 0, 0, 1, 0, eggMove));
        
        
        
        //เส้นโค้ง เปลือกไข่
        g2.setColor(pinkegg1);
        setPlotSize(2);
        bezierCurve(g2, 295, 352, 227, 356, 207, 450);
        bezierCurve(g2, 207, 450, 194, 506, 235, 549);
        bezierCurve(g2, 235, 549, 269, 572, 295, 571);
        bezierCurve(g2, 295, 571, 350, 578, 382, 519);
        bezierCurve(g2, 382, 519, 407, 472, 375, 411);
        bezierCurve(g2, 375, 411, 352, 356, 295, 352);

        //รายละเอียดเปลือกไข่        
        bezierCurve(g2, 319,427,278, 437 , 300 , 481);
        bezierCurve(g2, 300,481,334, 514 , 356 , 472);
        bezierCurve(g2, 356,472,368, 430 , 323 , 427);
        bresenhamLine(g2, 323,427,319,427);   

        bezierCurve(g2, 220,409,252, 430 , 222 , 454);
        bezierCurve(g2, 222,454,211, 457 , 206 , 454);

        bezierCurve(g2, 244,395,252, 397 , 248 , 406);
        bezierCurve(g2, 248,406,243, 410 , 239 , 406);
        bezierCurve(g2, 239,406,235, 399 , 242 , 395);
        bresenhamLine(g2, 242,395,244,395);

        bezierCurve(g2, 331,368,344, 370 , 340 , 385);
        bezierCurve(g2, 340,385,336, 396 , 323 , 391);
        bezierCurve(g2, 323,391,315, 384 , 319 , 374);
        bezierCurve(g2, 319,374,322, 367 , 328 , 368);
        bezierCurve(g2, 328,368,334, 369 , 331 , 368); 
        
        bezierCurve(g2, 265,481,297, 495 , 275 , 526);
        bezierCurve(g2, 275,526,257, 544 , 236 , 523);
        bezierCurve(g2, 236,523,225, 506 , 236 , 492);
        bezierCurve(g2, 236,492,244, 479 , 260 , 481);
        bezierCurve(g2, 260,481,276, 483 , 265 , 481);

        bezierCurve(g2, 313,526,334, 525 , 334 , 546);
        bezierCurve(g2, 334,546,329, 568 , 306 , 556);
        bezierCurve(g2, 306,556,292, 540 , 309 , 526);
        bresenhamLine(g2, 309,526,313,526);  
        
        g2.setColor(pinkegg2);                    

        bezierCurve(g2, 262,370,296, 355 , 294 , 376);
        bezierCurve(g2, 294,376,294, 388 , 274 , 396);
        bezierCurve(g2, 274,396,256, 403 , 251 , 392);
        bezierCurve(g2, 251,392,248, 381 , 260 , 372);
        bezierCurve(g2, 260,372,272, 363 , 262 , 370);        
              
        g2.setColor(Color.black);  
        setPlotSize(1); 

        //egg color
        buffer = floodFill(buffer, 321, 450 + (int)eggMove , Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 220, 430 + (int)eggMove , Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 243, 400 + (int)eggMove , Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 330, 380 + (int)eggMove , Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 256, 506 + (int)eggMove , Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 317, 542 + (int)eggMove , Color.WHITE, pinkegg1);        
        buffer = floodFill(buffer, 272, 377 + (int)eggMove , Color.WHITE, pinkegg2);
        buffer = floodFill(buffer, 300, 400 + (int)eggMove , Color.WHITE, pinkegg3);


        
        g2.setTransform(originalTransform);


        
        g.drawImage(buffer, 0, 0, null);

    }

    private int plotSize = 1;

    private void setPlotSize(int size) {
        plotSize = size;
    }

    private int getPlotSize() {
        return plotSize;
    }

    private void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }

    // method เส้นตรง
    private void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        boolean isSwap = false;

        if (dy > dx) {
            int temp = dx;
            dx = dy;
            dy = temp;
            isSwap = true;
        }

        int D = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 1; i <= dx; i++) {
            plot(g, x, y, getPlotSize());
            if (D > 0) {
                if (isSwap)
                    x += sx;
                else
                    y += sy;

                D -= 2 * dx;
            }

            if (isSwap)
                y += sy;
            else
                x += sx;

            D += 2 * dy;
        }
    }

    // method เส้นโค้ง
    public void bezierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        for (int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;

            int x = (int) (Math.pow(1 - t, 3) * x1 +
                    3 * t * Math.pow(1 - t, 2) * x2 +
                    3 * t * t * (1 - t) * x3 +
                    Math.pow(t, 3) * x4);

            int y = (int) (Math.pow(1 - t, 3) * y1 +
                    3 * t * Math.pow(1 - t, 2) * y2 +
                    3 * t * t * (1 - t) * y3 +
                    Math.pow(t, 3) * y4);

            plot(g, x, y, getPlotSize());
        }
    }

    public void bezierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        for (int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;

            int xt = (int) (Math.pow(1 - t, 2) * x1 +
                    2 * t * Math.pow(1 - t, 1) * x2 +
                    t * t * x3);

            int yt = (int) (Math.pow(1 - t, 2) * y1 +
                    2 * t * Math.pow(1 - t, 1) * y2 +
                    t * t * y3);

            plot(g, xt, yt, getPlotSize());
        }
    }

    // ลงสี
    public BufferedImage floodFill(BufferedImage m, int x, int y, Color target_colour, Color replacement_colour) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) == target_colour.getRGB()) {
            g2.setColor(replacement_colour);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            // south
            if (p.y < 600 && m.getRGB(p.x, p.y + 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }

            // north
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }

            // east
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }

            // west
            if (p.x > 0 && m.getRGB(p.x - 1, p.y) == target_colour.getRGB()) {
                g2.setColor(replacement_colour);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
    }

    private void createTrianglePolygon(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        Polygon poly = new Polygon();
        poly.addPoint(x1, y1);
        poly.addPoint(x2, y2);
        poly.addPoint(x3, y3);

        g.drawPolygon(poly);

    }

}