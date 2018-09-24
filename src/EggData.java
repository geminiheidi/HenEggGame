import java.util.Random;

public class EggData {
	boolean eggType;
	double eggPos;
	double eggVelocity;
	double time;
	EggData(Double time){
		this.time = time;
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1; 
		if(n <= 2) {
			eggType = false;
		}
		else {
			eggType = true;
		}
		eggPos = 0;
		eggVelocity = 20 * 0.1 / time; //5 secs from top to bottom 100/5
	}
}

