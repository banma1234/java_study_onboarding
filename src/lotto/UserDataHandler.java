package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserDataHandler {
	private Scanner sc = new Scanner(System.in);
	private int bonusNumber;
	
	public int setPlayableCount() {
		while(true) {
			try {
				System.out.print("구매 금액 입력 : ");
				int userPayedBill = sc.nextInt();
				
				if(userPayedBill < 1000 || userPayedBill % 1000 != 0) {
					throw new Exception();
				}
				
				return userPayedBill / 1000;
			} catch(Exception e) {
		        System.out.println("\n[Error] 올바른 형식의 데이터를 입력하세요.");
		        sc.nextLine();
			}
		}
	}
	
	public int setBonusNumber() {
		while(true) {
			try {
				System.out.print("\n보너스 번호 입력 : ");
				int bonusNumber = sc.nextInt();
				
				if(bonusNumber < 1 || bonusNumber > 45) {
					throw new Exception();
				}
				
				this.bonusNumber = bonusNumber;
				return bonusNumber;
			} catch(Exception e) {
		        System.out.println("\n[Error] 올바른 형식의 데이터를 입력하세요.");
		        sc.nextLine();
			}
		}
	}
	
	public List<Integer> setAnswer() {
		while(true) {
			try {
				System.out.print("\n당첨 번호 입력 : ");
				String userInputData = sc.next();
				
				String[] arr = userInputData.split(",");
				Set<Integer> unique = new HashSet<>();
				
				for(String s:arr) {
					int target = Integer.parseInt(s);
					if(target < 0 || target > 45) {
						throw new Exception();
					}
					if(target == this.bonusNumber) {
						throw new Exception();
					}
					
					unique.add(target);
				}
				if(unique.size() < 6) {
					throw new Exception();
				}
				
				return new ArrayList<>(unique);
			} catch(Exception e) {
		        System.out.println("\n[Error] 올바른 형식의 데이터를 입력하세요.");
		        sc.nextLine();
			}
		}
	}

}
