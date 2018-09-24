public class Basket {
	double basketPos;
	double basketVelocity;
	double time;
	Basket(double time){
		this.time = time;
		basketPos = 0;
		basketVelocity = 25 * 0.1 / time; //4 secs from top to bottom 100/4 = 25
	}
}
