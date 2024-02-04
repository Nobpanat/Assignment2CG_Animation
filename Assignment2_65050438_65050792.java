package Assignment2.Assignment2CG_Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Queue;

import java.awt.geom.AffineTransform;

public class Assignment2_65050438_65050792 extends JPanel implements Runnable {
    // การเคลื่อนที่ของไข่
    double eggMove = 0;
    // ความเร็วของไข่แกนY
    double eggVelocityY = -160;

    // การเคลื่อนที่เปลือกไข่ซ้าย
    double eggShellMoveLeft = 0;
    // การเคลื่อนที่เปลือกไข่ขวา
    double eggShellMoveRight = 0;
    // ความเร็วเปลือกไข่ซ้ายแกนX
    double eggShellVelocityLeft = -100;
    // ความเร็วเปลือกไข่ขวาแกนX
    double eggShellVelocityRight = 100;

    double totalTime = 0;

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
            totalTime = elapsedTotalTime;

            eggMove += eggVelocityY * elapsedTime / 1000.0;

            // ครบ1วินาทีหยุดเคลื่อนเปลือกไข่ขึ้นไป
            if (elapsedTotalTime >= 1000) {
                eggVelocityY = 0;
            }

            // การเคลื่อนที่ของเปลือกไข่ซ้ายและขวา
            eggShellMoveLeft += eggShellVelocityLeft * elapsedTime / 1000.0;
            eggShellMoveRight += eggShellVelocityRight * elapsedTime / 1000.0;

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

        // เส้นโค้ง เปลือกไข่
        g2.setColor(pinkegg1);
        setPlotSize(2);
        bezierCurve(g2, 295, 352, 227, 356, 207, 450);
        bezierCurve(g2, 207, 450, 194, 506, 235, 549);
        bezierCurve(g2, 235, 549, 269, 572, 295, 571);
        bezierCurve(g2, 295, 571, 350, 578, 382, 519);
        bezierCurve(g2, 382, 519, 407, 472, 375, 411);
        bezierCurve(g2, 375, 411, 352, 356, 295, 352);

        // รายละเอียดเปลือกไข่
        bezierCurve(g2, 319, 427, 278, 437, 300, 481);
        bezierCurve(g2, 300, 481, 334, 514, 356, 472);
        bezierCurve(g2, 356, 472, 368, 430, 323, 427);
        bresenhamLine(g2, 323, 427, 319, 427);

        bezierCurve(g2, 220, 409, 252, 430, 222, 454);
        bezierCurve(g2, 222, 454, 211, 457, 206, 454);

        bezierCurve(g2, 244, 395, 252, 397, 248, 406);
        bezierCurve(g2, 248, 406, 243, 410, 239, 406);
        bezierCurve(g2, 239, 406, 235, 399, 242, 395);
        bresenhamLine(g2, 242, 395, 244, 395);

        bezierCurve(g2, 331, 368, 344, 370, 340, 385);
        bezierCurve(g2, 340, 385, 336, 396, 323, 391);
        bezierCurve(g2, 323, 391, 315, 384, 319, 374);
        bezierCurve(g2, 319, 374, 322, 367, 328, 368);
        bezierCurve(g2, 328, 368, 334, 369, 331, 368);

        bezierCurve(g2, 265, 481, 297, 495, 275, 526);
        bezierCurve(g2, 275, 526, 257, 544, 236, 523);
        bezierCurve(g2, 236, 523, 225, 506, 236, 492);
        bezierCurve(g2, 236, 492, 244, 479, 260, 481);
        bezierCurve(g2, 260, 481, 276, 483, 265, 481);

        bezierCurve(g2, 313, 526, 334, 525, 334, 546);
        bezierCurve(g2, 334, 546, 329, 568, 306, 556);
        bezierCurve(g2, 306, 556, 292, 540, 309, 526);
        bresenhamLine(g2, 309, 526, 313, 526);

        g2.setColor(pinkegg2);

        bezierCurve(g2, 262, 370, 296, 355, 294, 376);
        bezierCurve(g2, 294, 376, 294, 388, 274, 396);
        bezierCurve(g2, 274, 396, 256, 403, 251, 392);
        bezierCurve(g2, 251, 392, 248, 381, 260, 372);
        bezierCurve(g2, 260, 372, 272, 363, 262, 370);

        g2.setColor(Color.black);
        setPlotSize(1);

        // egg color
        buffer = floodFill(buffer, 321, 450 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 220, 430 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 243, 400 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 330, 380 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 256, 506 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 317, 542 + (int) eggMove, Color.WHITE, pinkegg1);
        buffer = floodFill(buffer, 272, 377 + (int) eggMove, Color.WHITE, pinkegg2);
        buffer = floodFill(buffer, 300, 400 + (int) eggMove, Color.WHITE, pinkegg3);

        g2.setTransform(originalTransform);
        // ลงสีเปลือกไข่
        buffer = floodFill(buffer, 266, 432 + (int) eggMove, Color.WHITE, new Color(253, 75, 149));

        // แยกเปลือกไข่ออก 2 ส่วน
        if (totalTime >= 1300) {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 600, 600);

            // เปลือกไข่ ซีกซ้าย
            g2.setColor(Color.black);
            g2.setTransform(new AffineTransform(1, 0, 0, 1, eggShellMoveLeft, 0));
            bezierCurve(g2, 292, 195, 227, 191, 207, 288);
            bezierCurve(g2, 207, 288, 189, 345, 226, 382);
            bezierCurve(g2, 226, 382, 244, 408, 292, 415);
            bresenhamLine(g2, 292, 415, 318, 371);
            bresenhamLine(g2, 318, 371, 271, 324);
            bresenhamLine(g2, 271, 324, 318, 272);
            bresenhamLine(g2, 318, 272, 274, 229);
            bresenhamLine(g2, 274, 229, 292, 195);
            g2.setTransform(originalTransform);
            // รายละเอียดไข่ซีกซ้าย

            // เปลือกไข่ ซึกขวา
            g2.setTransform(new AffineTransform(1, 0, 0, 1, eggShellMoveRight, 0));
            bezierCurve(g2, 292, 195, 352, 187, 381, 270);
            bezierCurve(g2, 381, 270, 412, 315, 370, 380);
            bezierCurve(g2, 370, 380, 348, 417, 292, 415);
            bresenhamLine(g2, 292, 415, 318, 371);
            bresenhamLine(g2, 318, 371, 271, 324);
            bresenhamLine(g2, 271, 324, 318, 272);
            bresenhamLine(g2, 318, 272, 274, 229);
            bresenhamLine(g2, 274, 229, 292, 195);
            // รายละเอียดไข่ ซีกขวา
            
            g2.setTransform(originalTransform);
            
            if (totalTime >= 2200) {
    
                AffineTransform moveLegS1 = new AffineTransform(
                        Math.cos(Math.toRadians(30)), Math.sin(Math.toRadians(30)),
                        -Math.sin(Math.toRadians(30)), Math.cos(Math.toRadians(30)),
                        311, 347);
    
                AffineTransform moveLegS2 = new AffineTransform(1, 0, 0, 1, -311, -347);
                
                moveLegS1.concatenate(moveLegS2);
                g2.setTransform(moveLegS1);
                
            }
            // หัวนกด้านนอก
            bezierCurve(g2, 306, 179, 291, 179, 285, 197);
            bezierCurve(g2, 285, 197, 247, 234, 285, 270);
            bezierCurve(g2, 285, 270, 288, 277, 293, 270);
            bezierCurve(g2, 293, 270, 292, 277, 302, 273);
            bezierCurve(g2, 302, 273, 310, 291, 321, 271);
            bezierCurve(g2, 321, 271, 333, 289, 345, 268);
            bezierCurve(g2, 345, 268, 369, 279, 358, 258);
            bezierCurve(g2, 358, 258, 385, 237, 360, 205);
            bezierCurve(g2, 360, 205, 365, 182, 345, 174);
            bezierCurve(g2, 345, 174, 327, 172, 323, 187);
            bezierCurve(g2, 323, 187, 313, 177, 306, 179);

            // รายละเอียดหัวด้านใน

            // ปีกขวาของนก
            bresenhamLine(g2, 282, 268, 282, 274);
            bezierCurve(g2, 282, 274, 266, 289, 257, 318);
            bezierCurve(g2, 257, 318, 252, 321, 250, 326);
            bezierCurve(g2, 250, 326, 249, 328, 250, 330);
            bezierCurve(g2, 250, 330, 209, 352, 198, 354);
            bezierCurve(g2, 198, 354, 193, 363, 205, 362);
            bezierCurve(g2, 205, 362, 197, 376, 220, 369);
            bezierCurve(g2, 220, 369, 222, 378, 237, 370);
            bezierCurve(g2, 237, 370, 261, 359, 268, 352);
            bezierCurve(g2, 268, 352, 310, 335, 308, 313);
            bezierCurve(g2, 308, 313, 313, 297, 303, 280);
            bresenhamLine(g2, 303, 280, 304, 276);

            // รายละเอียดปีกขวา

            // ลำตัวนก
            bezierCurve(g2, 273, 349, 322, 353, 341, 338);
            bezierCurve(g2, 341, 338, 361, 321, 360, 312);
            bezierCurve(g2, 360, 312, 367, 283, 358, 270);

            // ลำตัวไม่มีรายละเอียดด้านใน
            // ปีกซ้ายของนก
            bezierCurve(g2, 360, 262, 367, 265, 369, 284);
            bezierCurve(g2, 369, 284, 369, 303, 360, 312);


            // ต้นขาซ้ายของนก
            bezierCurve(g2, 341, 338, 346, 353, 335, 359);
            bezierCurve(g2, 335, 359, 322, 360, 321, 357);
            bezierCurve(g2, 321, 357, 313, 355, 313, 348);
            // ต้นขาซ้ายนกไม่มีรายละเอียด
            // เท้าซ้ายของนก
            bresenhamLine(g2, 333, 359, 333, 364);
            bezierCurve(g2, 333, 364, 354, 366, 355, 374);
            bezierCurve(g2, 355, 374, 356, 386, 344, 383);
            bezierCurve(g2, 344, 383, 332, 383, 324, 373);
            bezierCurve(g2, 324, 373, 309, 371, 312, 364);
            bezierCurve(g2, 312, 364, 313, 359, 320, 362);
            bresenhamLine(g2, 320, 362, 319, 356);

            // รายละเอียดเท้าซ้ายนก

            // ต้นขาขวาของนก
            bezierCurve(g2, 308, 348, 309, 362, 300, 366);
            bresenhamLine(g2, 300, 366, 287, 365);
            bezierCurve(g2, 287, 365, 278, 357, 277, 350);
            // ต้นขวาไม่มีรายละเอียด

            // เท้าขวาของนก
            bresenhamLine(g2, 298, 366, 298, 372);
            bezierCurve(g2, 298, 372, 320, 372, 321, 379);
            bezierCurve(g2, 321, 379, 322, 393, 311, 390);
            bezierCurve(g2, 311, 390, 300, 390, 297, 387);
            bezierCurve(g2, 297, 387, 292, 382, 291, 381);
            bezierCurve(g2, 291, 381, 280, 380, 279, 375);
            bezierCurve(g2, 279, 375, 278, 368, 287, 371);
            bresenhamLine(g2, 287, 371, 287, 365);

            // รายละเอียดเท้าขวานก

        }

        // if(totalTime > 0){
        // }
        // else if(totalTime > 1000.0){
        // buffer = floodFill(buffer, 266, 432, Color.WHITE, Color.WHITE);
        // }

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

    // วงกลม
    public void midpointCircle(Graphics g, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 1 - r;
        int dx = 2 * x;
        int dy = 2 * y;

        while (x <= y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);
            plot(g, y + xc, x + yc, 3);
            plot(g, -y + xc, x + yc, 3);
            plot(g, y + xc, -x + yc, 3);
            plot(g, -y + xc, -x + yc, 3);

            x++;
            dx += 2;
            d = d + dx + 1;

            if (d >= 0) {
                y--;
                dy -= 2;
                d = d - dy;
            }

        }
    }

    // วงรี
    public void midpointEllipse(Graphics g, int xc, int yc, int a, int b) {
        int x, y, d;
        // R1
        x = 0;
        y = b;
        d = Math.round(b * b - a * a * b + a * a / 4);

        while (b * b * x <= a * a * y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);

            x++;
            d = d + 2 * b * b * x + b * b;

            if (d >= 0) {
                y--;
                d = d - 2 * a * a * y;
            }
        }

        // R2
        x = a;
        y = 0;
        d = Math.round(a * a - b * b * a + b * b / 4);

        while (b * b * x >= a * a * y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);

            y++;
            d = d + 2 * a * a * y + a * a;

            if (d >= 0) {
                x--;
                d = d - 2 * b * b * x;
            }

        }
    }

}