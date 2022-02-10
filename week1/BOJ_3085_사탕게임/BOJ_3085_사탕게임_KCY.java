package Algo_2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_사탕_게임_2 {
	// 옮기고
	// 행,열 체크하고
	// 원복하고
	// 행*열 갯수 만큼 반복
	static int N;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		// print();
		// 행,열 방향으로 다른지 체크, 다르면 함수 시작
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 행
				if (i + 1 < N) {
					char temp = arr[i][j];
					arr[i][j] = arr[i + 1][j];
					arr[i + 1][j] = temp;
					res = Math.max(res, check());
					temp = arr[i][j];
					arr[i][j] = arr[i + 1][j];
					arr[i + 1][j] = temp;
				}
				// 열
				if (j + 1 < N) {
					char temp = arr[i][j];
					arr[i][j] = arr[i][j + 1];
					arr[i][j + 1] = temp;
					res = Math.max(res, check());
					temp = arr[i][j];
					arr[i][j] = arr[i][j + 1];
					arr[i][j + 1] = temp;
				}
				

			}
		}
		System.out.println(res);
	}

	private static int check() {
		// 행,열의 최댓값, 결과의 최댓값
		int row = 0, col = 0, res = 0;
		for (int i = 0; i < N; i++) {
			// 행
			// 최소 1개
			int cnt=1;
			for (int j = 0; j < N; j++) {
				if (j + 1 < N) {
					if (arr[j + 1][i] == arr[j][i]) {
						cnt++;
					} else {
						cnt = 1;
					}
					row = Math.max(row, cnt);
				}
			}
			// 열
			cnt = 1;
			for (int j = 0; j < N; j++) {
				if (j + 1 < N) {
					if (arr[i][j + 1] == arr[i][j]) {
						cnt++;
					} else {
						cnt = 1;
					}
					col = Math.max(col, cnt);
				}
			}
			res = Math.max(row, col);
		}
		return res;
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
