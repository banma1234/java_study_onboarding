package lotto;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;

public class Game {
	private int playableCount;
	private int bonusNumber;
	private int prize = 0;
	private List<Integer> answer;
	private List<List<Integer>> generatedLotto = new ArrayList<>();
	
	public void playGame() {
		UserDataHandler dataHandler = new UserDataHandler(); 
		
		this.bonusNumber = dataHandler.setBonusNumber();
		this.playableCount = dataHandler.setPlayableCount();
		this.answer = dataHandler.setAnswer();
		
	    for (int i = 0; i < this.playableCount; i++) {
	        this.generatedLotto.add(generateUniqueLotto());
	    }
	    
	    int[] resultBoard = this.checkAnswer();
	    checkPrize(resultBoard);
	    
	    System.out.println("=====================================");
	    System.out.printf("3개 일치 (5,000원) - %d개\n", resultBoard[4]);
	    System.out.printf("4개 일치 (5,0000원) - %d개\n", resultBoard[3]);
	    System.out.printf("5개 일치 (1,500,000원) - %d개\n", resultBoard[2]);
	    System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", resultBoard[1]);
	    System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", resultBoard[0]);
	    System.out.println("=====================================");
	    System.out.printf("수익률 : %d", prize / (this.playableCount * 1000));
	}
	
	private List<Integer> generateUniqueLotto() {
	    Set<Integer> uniqueSet = new TreeSet<>();
	    while (uniqueSet.size() < 8) {
	        uniqueSet.add(ThreadLocalRandom.current().nextInt(1, 46));
	    }
	    return new ArrayList<>(uniqueSet);
	}
	
	private int[] checkAnswer() {
		int[] result = new int[5];
		
		for(List<Integer> ticket:this.generatedLotto) {
			int matchCount = 0;
			boolean isBonusMatch = false;
			
			for(int i=0; i<6; i++) {
				if(answer.get(i).equals(ticket.get(i))) {
					matchCount++;
					
					if(answer.get(i) == this.bonusNumber) {
						isBonusMatch = true;
					}
				}
			}
			
			if(matchCount == 3)	result[4]++;
			else if (matchCount == 4)	result[3]++;
			else if (matchCount == 5 && isBonusMatch)	result[1]++;
			else if (matchCount == 5)	result[2]++;
			else if (matchCount == 6)	result[0]++;
		}
		
		return result;
	}
	
	private void checkPrize(int[] result) {
		int[] prizeBoard = new int[]{2000000000, 30000000, 1500000, 50000, 5000};
		
		for(int i=0; i<5; i++) {
			if(result[i] != 0) {
				this.prize += prizeBoard[i];
			}
		}
	}
}
