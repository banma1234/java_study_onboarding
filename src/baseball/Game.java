package baseball;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class Game {
	public Scanner sc = new Scanner(System.in);
	private Set<Integer> randomValueSet = new HashSet<>();
	
	
	public void playGame() {
		System.out.println("숫자 야구를 시작합니다.\n");
		
		List<Integer> randomValue = this.generateRandomValue();
		
		while(true) {
			int strikeCount = 0;
			int ballCount = 0;
			int[] userValue = this.inputUserValue();
			
			for(int i=0; i<3; i++) {
				if(this.randomValueSet.contains(userValue[i])) {
					ballCount++;
				}
				if(userValue[i] == randomValue.get(i)) {
					strikeCount++;
				}
			}
			ballCount -= strikeCount;
			
			System.out.printf("%dS%dB\n", strikeCount, ballCount);
			
			if(strikeCount == 3) {
				System.out.println("====================");
				System.out.println("승리!!");
				break;
			}
		}
	}
	
	private List<Integer> generateRandomValue() {
		while(this.randomValueSet.size() < 3) {
			this.randomValueSet.add(ThreadLocalRandom.current().nextInt(1, 10));
		}
		List<Integer> result = new ArrayList<>(this.randomValueSet);
		
		return result;
	}
	
	private int[] inputUserValue() {
		int inputValue;
		
		while (true) {
		    System.out.print("\n3자리 숫자를 입력하세요: ");
		    try {
		        inputValue = sc.nextInt();

		        if (inputValue < 100 || inputValue > 999) {
		            System.out.println("\n[Error] 반드시 3자리 숫자를 입력하세요.");
		            continue;
		        }
		        
				int[] userValue = new int[3];
				
				userValue[0] = inputValue / 100;
				userValue[1] = (inputValue / 10) % 10;
				userValue[2] = inputValue % 10;
				
				Set<Integer> checkUnique = new HashSet<>();
				
				for(int i=0; i<3; i++) {
					checkUnique.add(userValue[i]);
				}
				if(checkUnique.size() < 3) {
					System.out.println("\n[Error] 서로 다른 3자리 숫자를 입력하세요.");
					continue;
				}
				
				return userValue;
		    } catch (Exception e) {
		        System.out.println("\n[Error] 반드시 숫자를 입력하셔야 합니다.");
		        sc.nextLine();
		    }
		}
	}
}