import java.io.BufferedReader;
import java.util.List;

public class MultiThreadRunnable implements Runnable{

    private static BufferedReader bufferedReaderr;
    private List<String> res;
    MultiThreadRunnable(List<String> res, BufferedReader bufferedReaderr){
        this.bufferedReaderr = bufferedReaderr;
        this.res = res;
    }
    @Override
    public void run() {
        String line = null;
        int cnt = 0;
        while (true) {
            synchronized (bufferedReaderr) {
                try {
                    while ((line = bufferedReaderr.readLine()) != null) {
                        if (cnt < 10) {
                            if (!line.equals("")){
                                res.add(line);
                            }
                            cnt++;
                        } else {
                            if (!line.equals("")){
                                res.add(line);
                            }
                            cnt = 0;
                            break;
                        }
                    }
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
        }
    }
}
