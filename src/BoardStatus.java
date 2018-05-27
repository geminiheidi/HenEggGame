import static javax.swing.JOptionPane.PLAIN_MESSAGE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JOptionPane;

class EggData{
	boolean eggType;
	double eggPos;
	double eggVelocity;
	EggData(){
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		if(n <= 1) {
			eggType = false;
		}
		else {
			eggType = true;
		}
		eggPos = 0;
		eggVelocity = 20; //5 secs from top to bottom 100/5
	}
}

class PlayerStatus{
	int score;
	int livesRemaining;
	PlayerStatus(){
		livesRemaining = 3;
		score = 0;
	}
}

class Basket{
	double basketPos;
	double basketVelocity;
	Basket(){
		basketPos = 0;
		basketVelocity = 25; //4 secs from top to bottom 100/4 = 25
	}
}

public class BoardStatus {
	
	ArrayList<ArrayList<EggData>> eggs = new ArrayList<ArrayList<EggData>>();//will hold 3 arrays which holds eggs of each hen
	ArrayList<Double> nextEggFallTime = new ArrayList<Double>();	
	Double time = 0.1; //seconds
	Basket basket;
	PlayerStatus player;
	
	BoardStatus(){
		basket = new Basket();
		player = new PlayerStatus();
//		basketPos = 0;//-1,0,1	
		nextEggFallTime.add(2.0);
		nextEggFallTime.add(1.0);
		nextEggFallTime.add(3.0);
		
		for(int i = 0; i < 3; i++) {
			ArrayList<EggData> egg = new ArrayList<EggData>();
			eggs.add(egg);
		}
	}
	
	void decideNextEggFallingTime(int j) { 
		Random rand = new Random();
		int  n = rand.nextInt(10) + 15;
		
		nextEggFallTime.set(j, n*1.0);
	}
	
	void checkIfTimeToReleaseEgg() {
		for(int i = 0; i < nextEggFallTime.size(); i++) {
			if(nextEggFallTime.get(i) <= 0) {
				eggs.get(i).add(new EggData());
//				nextEggFallTime.set(i, 1.0 * (i + 1));
				decideNextEggFallingTime(i);
			}
		}
	}
	
	private void removes() {
		for(int j = 0; j < eggs.size(); j++) {
			for(Iterator<EggData> i = eggs.get(j).iterator(); i.hasNext();) {
				EggData pos = i.next();
				if(pos.eggPos > 100.0) {
					i.remove();
				}
			 }
		}
	}
	
	void updateEggPosition() {
		for(ArrayList<EggData> a: eggs) {
			for(EggData b: a) {
				b.eggPos = b.eggPos + (b.eggVelocity * time);
			}
		}
//		removes();
	}
	
	void moveBasket(int keyPressStatus)
	{
		//basketPos should be within 0 and 100. if 1, moves right. if -1 moves left
		basket.basketPos = 
				Math.max(0.0, Math.min(100.0, basket.basketPos + (keyPressStatus * basket.basketVelocity * time)));
	}
	
	void checkEggCaught() {
		for(Iterator<EggData> i = eggs.get(0).iterator(); i.hasNext();) {
			EggData pos = i.next();
//			System.out.println("egg and basket pos " + pos.eggPos + " " + basket.basketPos);
			if(pos.eggPos >= 95 && (basket.basketPos >= 5 && basket.basketPos < 11)){
				if(pos.eggType == true) {
					System.out.println("caught egg and basket pos " + pos.eggPos + " " + basket.basketPos);
					player.score = player.score + 5;
				}
				else {
					player.score = player.score - 5;
					
				}
				i.remove();
			}
			
		}
		
		for(Iterator<EggData> i = eggs.get(1).iterator(); i.hasNext();) {
			EggData pos = i.next();
//			System.out.println("egg and basket pos " + pos.eggPos + " " + basket.basketPos);
			if(pos.eggPos >= 95 && (basket.basketPos >= 45 && basket.basketPos < 51)){
				if(pos.eggType == true) {
					System.out.println("caught egg and basket pos " + pos.eggPos + " " + basket.basketPos);
					player.score = player.score + 5;
				}
				else {
					player.score = player.score - 5;
					
				}
				i.remove();
			}
		}
		for(Iterator<EggData> i = eggs.get(2).iterator(); i.hasNext();) {
			EggData pos = i.next();
//			System.out.println(pos.eggPos + " " + basket.basketPos);
			if(pos.eggPos >= 95 && (basket.basketPos >= 75 && basket.basketPos < 81)){
				if(pos.eggType == true) {
					System.out.println("caught egg and basket pos " + pos.eggPos + " " + basket.basketPos);
					player.score = player.score + 5;
//					System.out.println("Caught inside if" + pos.eggPos + " " + basket.basketPos);
				}
				else {
					player.score = player.score - 5;
					
				}
				i.remove();
			}
		}
	}
	
	void checkEggMissed() {
		for(ArrayList<EggData> a: eggs) {
			for(Iterator<EggData> i = a.iterator(); i.hasNext();) {
				EggData pos = i.next();
				if(pos.eggPos >= 100) {
					System.out.println("Missed");
					if(pos.eggType == true) {
						player.livesRemaining--;
					}
					i.remove();
				}
			}
		}
	}


	
	void updateBoardStatus(int keyPressStatus){
		moveBasket(keyPressStatus);
//		decideNextEggFallingTime();
		for(int i = 0; i < nextEggFallTime.size(); i++) {
			nextEggFallTime.set(i, nextEggFallTime.get(i) - time);
		}
		updateEggPosition();
		checkIfTimeToReleaseEgg();
		checkEggCaught();
		checkEggMissed();
//		printEggs();

		
	}
	
	void printEggs() {
		System.out.println(nextEggFallTime);
//		for(ArrayList<EggData> a: eggs) {
//			for(EggData b: a) {
//				System.out.println(b.eggPos);
//			}
//		}
		System.out.println("\n");
		
	}

}
