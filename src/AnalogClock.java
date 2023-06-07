import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class AnalogClock extends JPanel implements Observer {
  private ClockTime clockTime;
  private int hour = 9;
  private int minute = 0;
  private int second = 0;
  private int radius = 150;
  private final int SIZE = 300;
  public AnalogClock(ClockTime clockTime){
    this.clockTime = clockTime;
    this.clockTime.attach(this);
    setPreferredSize(new Dimension(SIZE+30,SIZE+30));
    update(clockTime.hour, clockTime.minute,clockTime.second);
  }
  @Override
  public void update(int hour, int minute, int second) {
    this.hour = hour;
    this.second = second;
    this.minute = minute;
    this.repaint();
  }
  public Point center(){
    int x = this.getHeight()/2;
    int y = this.getWidth()/2;
    return new Point(x,y);
  }
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics.create();
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    drawOval(graphics2D);
    drawCH(graphics2D);
    drawCM(graphics2D);
    drawCS(graphics2D);
  }

  private void drawOval(Graphics2D graphics2D){
    Graphics2D g2 = (Graphics2D) graphics2D.create();
    int radius = (SIZE/2);
    Point center = center();
    center.x -= radius;
    center.y -= radius;
    g2.setStroke(new BasicStroke(2));
    g2.setColor(Color.WHITE);
    g2.fillOval(center.x,center.y,SIZE,SIZE);
    g2.setColor(Color.black);
    g2.drawOval(center.x,center.y,SIZE,SIZE);
    g2.dispose();

  }
  public void drawCH(Graphics2D g) {
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setStroke(new BasicStroke(2));
    g2.setColor(Color.black);
    Point center = center();
    int length = SIZE/4;
    int hA = ((hour%12) * 30 + minute/2);
    int hX = (int)  (center.x + length * Math.sin(Math.toRadians(hA)));
    int hY = (int)  (center.y - length * Math.cos(Math.toRadians(hA)));
    g2.drawLine(center.x,center.y,hX,hY);
    g2.dispose();

  }
  public void drawCM(Graphics2D g) {
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setStroke(new BasicStroke(2));
    g2.setColor(Color.black);
    Point center = center();
    int length = SIZE/2 * 70/100;
    int mA = (minute * 6 + second/10);
    int mX = (int)  (center.x + length * Math.sin(Math.toRadians(mA)));
    int mY = (int)  (center.y - length * Math.cos(Math.toRadians(mA)));
    g2.drawLine(center.x,center.y,mX,mY);
    g2.dispose();
  }
  public void drawCS(Graphics2D g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setStroke(new BasicStroke(2));
    g2.setColor(Color.red);
    int length = SIZE/2 * 90/100;
    Point center = center();
    int sA = (second*6);
    int sX = (int) (center.x + length * Math.sin(Math.toRadians(sA)));
    int sY = (int) (center.y - length * Math.cos(Math.toRadians(sA)));
    g2.drawLine(center.x,center.y,sX,sY);
    g2.dispose();
  }
}



