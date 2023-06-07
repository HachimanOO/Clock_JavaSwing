import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class DigitalClock extends JPanel implements Observer {
  private ClockTime clockTime;
  private JLabel jLabel;
  private int hour = 9;
  private int minute = 0;
  private int second = 0;
  public static int SIZE = 50;
  public DigitalClock(ClockTime clockTime) {
    super();
    this.clockTime = clockTime;
    this.clockTime.attach(this);
    this.jLabel = new JLabel();
    this.jLabel.setFont(new Font("ITALIC",Font.BOLD,SIZE));
    update(clockTime.hour, clockTime.minute,clockTime.second);
    this.add(jLabel);
    this.setSize(new Dimension(jLabel.getWidth() + 20, jLabel.getHeight() +20));

  }

  @Override
  public void update(int hour, int minute, int second) {
    this.hour = hour;
    this.second = second;
    this.minute = minute;
    display();
  }
  public void display(){
    jLabel.setText(String.format("%s:%s:%s",hour,minute,second));
  }
}
