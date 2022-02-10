package 스터디문제_2022;

import java.util.*;
import java.io.*;

/**
 * N <= 50
 * 시간복잡도(예상) 4 * (N ^ 4) = 2500만
 */
public class BOJ_3085_사탕게임 {

	static int N, ans;
	static char[][] map;
	
	// 2방 탐색 (우, 하)
	// 인접한 두 사탕을 교환하는 것이므로 한 쪽에서 확인하면 반대편에서는 확인 안해도 됨
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("BOJ_3085_사탕게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		solve();
		
		System.out.println(ans);
		
		print(map);
	}

	private static void print(char[][] arr) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 교환할 수 있는 사탕을 찾고 교환하는 함수
	 */
	private static void solve() {
		
		// 사탕을 교환하기 위한 char변수
		char swap;
		
		// map의 모든 위치에서 직접 교환하며 먹을 수 있는 사탕 개수 체크
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < 2; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 경계체크
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					// 사탕이 같으면 교환x
					if (map[r][c] == map[nr][nc]) continue;
					
					// 사탕 교환
					swap = map[r][c];
					map[r][c] = map[nr][nc];
					map[nr][nc] = swap;
					
					// 한 번 교환할 때마다 현재 배치에서 먹을 수 있는 사탕 파악
					int eat = eatCandy();
					ans = ans < eat ? eat : ans;
					
					// 먹을 수 있는 사탕개수 체크가 끝났으므로 교환한 사탕 제자리로 복구
					swap = map[r][c];
					map[r][c] = map[nr][nc];
					map[nr][nc] = swap;
				}
			}
		}
	}

	/**
	 *	현재 상태에서 최대로 먹을 수 있는 사탕개수를 찾는 함수 
	 */
	private static int eatCandy() {
		// 최소로 먹는 개수는 1개
		int maxEat = 1;
		
		// 행 단위로 먹을 수 있는 사탕개수 확인
		for (int r = 0; r < N; r++) {
			int idx = 1;
			for (int c = 0; c < (N - 1); c++) {
				if (map[r][c] == map[r][c + 1]) {
					idx++;
					maxEat = maxEat < idx ? idx : maxEat;
				} else {
					idx = 1;
				}
				
			}
		}
		
		// 열 단위로 먹을 수 있는 사탕개수 확인
		for (int c = 0; c < N; c++) {
			int idx = 1;
			for (int r = 0; r < (N - 1); r++) {
				if (map[r][c] == map[r + 1][c]) {
					idx++;
					maxEat = maxEat < idx ? idx : maxEat;
				} else {
					idx = 1;
				}
			}
		}
		
		// 최대로 먹은 사탕개수 반환
		return maxEat;
	}

}
