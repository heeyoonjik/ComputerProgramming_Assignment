package org.java_code.homework;

import java.util.Scanner;

class LadderGame {
	String ladder[][];
	String ladderCopy[][];
	Scanner sc = new Scanner(System.in);
	int x, y, number;

	public void gameStart() {
		System.out.println("\n몇 명이 참가합니까? 2명부터 가능.");
		number = sc.nextInt();
		ladder = new String[11][(number * 2) - 1];

		for (int i = 0; i < ladder.length - 1; i++) {
			for (int j = 0; j < ladder[i].length; j++) {
				if (j % 2 == 0)
					ladder[i][j] = "|";
				else {
					ladder[i][j] = "   ";
				}
			}
		}
		int count = 0;
//		가로줄의 개수를 정하는 난수
		int max = (int) (Math.random() * number + number * 2);
		/*
		 * 절대 가로줄이 연달아 발생하지 않도록 하기 위해 난수를 활용해 배열의 무작위적인 위치를 선정하고, 그 지점의 양옆에 가로줄이 없다면
		 * 가로줄을 긋는다.
		 */
		while (count < max) {
			int r1 = (int) (Math.random() * 10);
			int r2 = (int) (Math.random() * (number * 2) - 1);
			/*
			 * ArrayIndexOutOfBoundsException을 예방하기 위해 배열의 왼쪽 끝은 오른쪽에서만 가로줄이 있는지 확인하고 배열의
			 * 오른쪽 끝은 왼쪽에서만 가로줄이 있는지 확인하고 나머지는 자기 위치의 양 끝에서 가로줄이 있는지 확인하다.
			 */
			if (r2 % 2 != 0) {
				if (r2 == 1) {
					if (ladder[r1][r2 + 2].equals("   ") && ladder[r1][r2].equals("   ")) {
						ladder[r1][r2] = " - ";
						count++;
					}
				} else if (r2 == ladder[0].length - 2) {
					if (ladder[r1][r2 - 2].equals("   ") && ladder[r1][r2].equals("   ")) {
						ladder[r1][r2] = " - ";
						count++;
					}

				} else {
					if (ladder[r1][r2 + 2].equals("   ") && ladder[r1][r2 - 2].equals("   ")
							&& ladder[r1][r2].equals("   ")) {
						ladder[r1][r2] = " - ";
						count++;
					}

				}
			}

		}
		// 번호를 선택하기 전까지 사다리 일부를 가리기 위함.
		for (int i = 0; i < ladder.length - 1; i++) {
			for (int j = 0; j < ladder[i].length; j++) {
				if (i >= 3 && i <= 6) {
					System.out.print("? ");
				} else {
					System.out.print(ladder[i][j]);
				}
			}
			System.out.println();
		}
		// 사다리 게임의 종착지에 숫자를 표시하기 위함.
		for (int i = 0; i < ladder[0].length; i++) {
			if (i % 2 == 0) {
				ladder[ladder.length - 1][i] = ((i / 2) + 1) + "";
			} else {
				ladder[ladder.length - 1][i] = "   ";
			}
		}
		/*
		 * 사다리 게임의 배열을 임시 저장 배열에 복사하여 사용자가 다른 번호의 결과도 확인하고자 할 때 사용함
		 */
		ladderCopy = new String[11][(number * 2) - 1];
		for (int i = 0; i < ladder.length; i++) {
			for (int j = 0; j < ladder[0].length; j++) {
				ladderCopy[i][j] = ladder[i][j];
			}
		}
		System.out.print("몇 번 볼거야?(1~" + number + ") : ");
		gameLogic(sc.nextInt());

	}

	public void gameLogic(int n) {
		y = 0;
		x = 2 * (n - 1);
		ladder[y][x] = "@";
		while (y < 9) {
			if (x == 0) {
				if (ladder[y][x + 1].equals(" - ")) {
					gameLogicRight();
				} else {
					gameLogicBottom();
				}
			} else if (x == (number * 2) - 2) {
				if (ladder[y][x - 1].equals(" - ")) {
					gameLogicLeft();
				} else {
					gameLogicBottom();
				}
			} else {
				if (ladder[y][x - 1].equals(" - ")) {
					gameLogicLeft();
				} else if (ladder[y][x + 1].equals(" - ")) {
					gameLogicRight();
				} else {
					gameLogicBottom();
				}
			}

		}
		System.out.println("\n=====GAME RESULT=====\n");
		for (int i = 0; i < ladder.length; i++) {
			for (int j = 0; j < ladder[i].length; j++) {
				System.out.print(ladder[i][j]);
			}
			System.out.println();
		}
		x = (x / 2) + 1;
		System.out.println(x + "번 당첨!");
	}

	public void gameLogicLeft() {
		ladder[y][x - 1] = " ← ";
		ladder[y][x - 2] = "←";
		x -= 2;
		if (x == 0) {
			ladder[++y][x] = "↓";
		}

	}

	public void gameLogicRight() {
		ladder[y][x + 1] = " → ";
		ladder[y][x + 2] = "→";
		x += 2;
		if (x == 6) {
			ladder[++y][x] = "↓";
		}
	}

	public void gameLogicBottom() {
		ladder[++y][x] = "↓";
	}

	public void gameOtherResult() {
		System.out.print("몇 번 볼거야?(1~" + number + ") : ");
		for (int i = 0; i < ladder.length; i++) {
			for (int j = 0; j < ladder[0].length; j++) {
				ladder[i][j] = ladderCopy[i][j];
			}
		}
		gameLogic(sc.nextInt());
	}
}

public class HW_LadderGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("60220435 김희윤 사다리 문제");
		System.out.println("1.줄 개수를 선택할 수 있으며, 그러한 줄 중 하나의 결과를 확인할 수 있습니다.\n"
				+ "2.선택하기 전에는 사다리 일부만 보이며, 몇 번에 당첨되었는지 경로를 보여줍니다." + "\n3.처음 선택한 번호 외에 다른 번호의 결과도 확인할 수 있습니다.\n"
				+ "4.하나의 세로선을 기준으로 동시에 양쪽으로 가로줄이 생기지 않아" + "\n결과가 겹치지 않습니다.");

		System.out.print("=====GAME START=====");
		LadderGame l = new LadderGame();
		l.gameStart();
		while (true) {
			System.out.print("\n1: 다른 번호 결과 확인, 2: 다시 하기 3: 종료 >> ");
			int input = sc.nextInt();
			if (input == 2) {
				l.gameStart();
			} else if (input == 1) {
				l.gameOtherResult();
			} else {
				System.out.println("======GAME END======");
				break;
			}
		}
		sc.close();
	}

}
