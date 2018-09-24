import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameStatus {
	
	ArrayList<ArrayList<EggData>> eggs = new ArrayList<ArrayList<EggData>>();//will hold 3 arrays which holds eggs of each hen
	ArrayList<Double> nextEggFallTime = new ArrayList<Double>();	
	Double time = 0.1; //seconds
	Basket basket;
	Random rand;
	int lastEggFallIndex;
	
	GameStatus(){
		basket = new Basket(time);
		rand = new Random();
		nextEggFallTime.add(rand.nextInt(10 ) + 3.0);
		nextEggFallTime.add(rand.nextInt(5) * 1.0);
		nextEggFallTime.add(rand.nextInt(10) + 3.0);
		lastEggFallIndex = 0;
		
		for(int i = 0; i < 3; i++) {
			ArrayList<EggData> egg = new ArrayList<EggData>();
			eggs.add(egg);
		}
	}
	
	void decideNextEggFallingTime(int henIndex) {
		int  n = rand.nextInt(7) + 0;  // minimum 3 seconds between 2 consecutive eggs
		nextEggFallTime.set(henIndex, nextEggFallTime.get(lastEggFallIndex) + 3 + n * 1.0);
		lastEggFallIndex = henIndex;
	}
	
	void checkIfTimeToReleaseEgg() {
		for(int i = 0; i < nextEggFallTime.size(); i++) {
			if(nextEggFallTime.get(i) <= 0) {
				SoundEffect.HEN.play();
				eggs.get(i).add(new EggData(time));
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
	}
	
	void moveBasket(int keyPressStatus)
	{
		//basketPos should be within 0 and 100. if 1, moves right. if -1 moves left
		basket.basketPos = 
				Math.max(0.0, Math.min(85.0, basket.basketPos + (keyPressStatus * basket.basketVelocity * time)));
	}
	
	void checkEggCaught() {
		for(Iterator<EggData> i = eggs.get(0).iterator(); i.hasNext();) {
			EggData pos = i.next();
			if(pos.eggPos >= 95 && (basket.basketPos >= 5 && basket.basketPos < 11)){
				if(pos.eggType == true) {
					Player.getInstance().setScore(Player.getInstance().getScore() + 5);
					SoundEffect.DINGDONG.play();
				}
				else {
					Player.getInstance().setLivesRemaining(Player.getInstance().getLivesRemaining() - 1);
				}
				i.remove();
			}
			
		}
		
		for(Iterator<EggData> i = eggs.get(1).iterator(); i.hasNext();) {
			EggData pos = i.next();
			if(pos.eggPos >= 95 && (basket.basketPos >= 40 && basket.basketPos < 45)){
				if(pos.eggType == true) {
					Player.getInstance().setScore(Player.getInstance().getScore() + 5);
          SoundEffect.DINGDONG.play();
				}
				else {
					Player.getInstance().setLivesRemaining(Player.getInstance().getLivesRemaining() - 1);
				}
				i.remove();
			}
		}
		for(Iterator<EggData> i = eggs.get(2).iterator(); i.hasNext();) {
			EggData pos = i.next();
			if(pos.eggPos >= 95 && (basket.basketPos >= 75 && basket.basketPos < 81)){
				if(pos.eggType == true) {
					Player.getInstance().setScore(Player.getInstance().getScore() + 5);
          SoundEffect.DINGDONG.play();
				}
				else {
					Player.getInstance().setLivesRemaining(Player.getInstance().getLivesRemaining() - 1);
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
					if(pos.eggType == true) {
						Player.getInstance().setLivesRemaining(Player.getInstance().getLivesRemaining() - 1);
					}
					i.remove();
				}
			}
		}
	}
	
	void updateBoardStatus(int keyPressStatus){
		moveBasket(keyPressStatus);
		for(int i = 0; i < nextEggFallTime.size(); i++) {
			nextEggFallTime.set(i, nextEggFallTime.get(i) - time);
		}
		updateEggPosition();
		checkIfTimeToReleaseEgg();
		checkEggCaught();
		checkEggMissed();
	}
	
	void printEggs() {
		System.out.println(nextEggFallTime);
		System.out.println("\n");
	}
}
