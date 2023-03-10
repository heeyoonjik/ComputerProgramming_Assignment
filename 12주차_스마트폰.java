package org.java_code.homework;

import java.util.Scanner;
import java.util.Calendar;

class SmartPhone {
	Scanner sc = new Scanner(System.in);
	String number;
	String mobileCarrier = "SKT";
	int battery = 10;
	int memory = 10;
	String photo[][] = new String[2][2];

	public void getMobileCarrier() {
		System.out.println(mobileCarrier);
	}

	public void changeMobileCarrier(String ChangingmobileCarrier) {
		if (ChangingmobileCarrier.equals(mobileCarrier)) {
			System.out.println("이미 해당 통신사 이용 중");
		} else if (ChangingmobileCarrier.equals("SKT") || ChangingmobileCarrier.equals("KT")
				|| ChangingmobileCarrier.equals("LGU")) {
			mobileCarrier = ChangingmobileCarrier;
			System.out.println("통신사가 변경되었습니다.");
		} else {
			System.out.println("[에러]존재하지 않는 통신사 입니다.");
		}
	}

	public void chargeBattery(double time) {
		if (battery + (time * 30) >= 100) {
			battery = 100;
			System.out.println("100% 완충 되었습니다.");
		} else {
			battery += (time * 30);
			System.out.println(battery + "% 충전 되었습니다.");
		}

	}

	public void takePhoto() {
		Calendar cal = Calendar.getInstance();
		int saveIndex = 0;
		if (memory == 0) {
			System.out.println("메모리가 부족하여 사진 촬영이 불가능합니다. 사진을 삭제해 주세요");
		} else {
			memory -= 5;
			System.out.println("찰칵!");
			if (memory == 0) {
				saveIndex = 1;
			}
			photo[saveIndex][0] = cal.get(Calendar.YEAR) + "년 " + cal.get(Calendar.MONTH) + "월 "
					+ cal.get(Calendar.DAY_OF_MONTH) + "일 " + cal.get(Calendar.HOUR_OF_DAY) + "시 "
					+ cal.get(Calendar.MINUTE) + "분 " + cal.get(Calendar.SECOND) + "초";
			System.out.println("어떤 사진인가요?");
			photo[saveIndex][1] = sc.nextLine();
			System.out.println("사진 저장이 완료되었습니다.");

		}

	}

	public void deltetPhoto() {
		if (photo[0][0] == null) {
			System.out.println("삭제할 사진이 없습니다.");
			return;
		}
		System.out.println("가장 마지막으로 촬영한 사진이 삭제됩니다. 삭제하시겠습니까?(y/n)");
		String command = sc.next();
		int n = 1;
		if (command.equals("y")) {
			if (photo[1][0] == null)
				n = 0;
			for (int i = 0; i < 2; i++) {
				photo[n][i] = null;
			}
			System.out.println("삭제되었습니다.");
			memory += 5;
		} else {
			System.out.println("취소되었습니다.");
		}

	}

	public void showPhoto() {
		if (photo[0][0] == null) {
			System.out.println("저장된 사진이 존재하지 않습니다.");
			return;
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (photo[i][j] == null)
					System.out.print("");
				else
					System.out.print(photo[i][j] + " ");
			}
			System.out.println();
		}
	}
}

public class HW12_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("60220435 김희윤 12주차 개인톡 과제");
		SmartPhone phone = new SmartPhone();
		while (true) {
			Calendar cal = Calendar.getInstance();
			System.out.println(cal.get(Calendar.HOUR_OF_DAY) + "시 " + cal.get(Calendar.MINUTE) + "분 || 배터리 "
					+ phone.battery + "% || " + phone.mobileCarrier + " || " + phone.memory + "b "
					+ "버튼 - 1:통신사 변경, 2:배터리 충전 3:사진 촬영 4:사진 보기 5:사진 삭제 6:종료");
			int n = sc.nextInt();
			if (n == 6) {
				System.out.println("스마트폰을 종료합니다.");
				break;
			}
			switch (n) {
			case 1:
				System.out.println("어떤 통신사로 교체하시겠습니까?");
				phone.changeMobileCarrier(sc.next());
				break;

			case 2:
				System.out.println("몇 시간 동안 충전하시겠습니까?");
				phone.chargeBattery(sc.nextDouble());
				break;

			case 3:
				phone.takePhoto();
				break;
			case 4:
				phone.showPhoto();
				break;
			case 5:
				phone.deltetPhoto();
				break;
			}
		}
		sc.close();

	}

}
