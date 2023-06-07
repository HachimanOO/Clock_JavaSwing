import javax.swing.*;

public class Main extends JFrame {
  private JPanel clock;
  public Main(String s, JPanel clock) {
    super(s);
    this.add(clock);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();

  }

  public static void main(String[] args) {
    ClockTime subject = new ClockTime();
    new Main("Analog Clock" , new AnalogClock(subject));
    new Main("Digital Clock" , new DigitalClock(subject));
  }
}
