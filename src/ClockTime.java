import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClockTime implements Runnable{
    List<Observer> observerList = new ArrayList<>();
    LocalTime time;
    int hour;
    int minute;
    int second;
    public ClockTime(){
      tick();
      Thread thread = new Thread(this);
      thread.start();
    }
    public void tick(){
      time = LocalTime.now();
      this.hour = time.getHour();
      this.minute = time.getMinute();
      this.second = time.getSecond();
      nontifyObsever();
    }
    public void attach(Observer o) {
      if(!observerList.contains(o)) {
        observerList.add(o);
      }
    }
    public void dettach(Observer o) {
      if(observerList.contains(o)){
        observerList.remove(o);
      }
    }
    public void nontifyObsever() {
      for (Observer o : observerList) {
        o.update(hour,minute,second);
      }
    }

  @Override
  public void run() {
    try {
      while (true){
        tick();
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
