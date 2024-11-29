package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Game {

	private String[][] board = { { "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜" },
			{ "⬜", "⬜", "⬜", "⬛", "⬛", "⬛", "⬜", "⬜" },
			{ "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬛", "⬜" },
			{ "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬛", "⬜" },
			{ "⬜", "⬜", "⬜", "⬜", "⬜", "⬛", "⬜", "⬜" },
			{ "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬛", "⬜" },
			{ "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬛", "⬜" },
			{ "⬜", "⬜", "⬜", "⬛", "⬛", "⬛", "⬜", "⬜" } };

	private static int[][] blackTilesPattern1 = { { 1, 3 }, { 1, 4 }, { 1, 5 }, { 2, 2 }, { 2, 5 }, { 3, 6 }, { 4, 5 },
			{ 5, 4 }, { 6, 3 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 } };

	private static int[][] blackTilesPattern2 = { { 1, 4 }, { 2, 3 }, { 3, 4 }, { 4, 4 }, { 5, 4 }, { 6, 4 }, { 7, 2 },
			{ 7, 3 }, { 7, 4 }, { 7, 5 } };

	private void printBoard() {
		for (String[] row : this.board) {
			for (String elem : row) {
				System.out.print(" " + elem + " ");
			}
			System.out.println("");
		}
	}

	private void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void clearRow(String[] row) {
		Arrays.fill(row, "⬜");
	}

	private void loadBlackTiles(int[][] blackTiles) {
		for (int[] tile : blackTiles) {
			board[tile[0]][tile[1]] = "⬛";
		}
	}

	private void updateBoardPattern(int[][] pattern) {
		wait(1000);

		clearRow(this.board[0]);

		loadBlackTiles(pattern);

		printBoard();
	}

	public void play() {
		int player = 0;

		printBoard();

		updateBoardPattern(blackTilesPattern1);

		updateBoardPattern(blackTilesPattern2);

		wait(1000);

		clearRow(this.board[0]);

		boolean playing = true;
		while (playing) {
			try {
				Random r = new Random();
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int newCar = r.nextInt(8);
				this.board[0][newCar] = "🚙";
				this.board[7][player] = "🚗";

				printBoard();

				// input
				String key = br.readLine();

				if (key.equals("q")) {
					playing = false;
					break;
				} else if (key.equals("a") && player > 0) {
					this.board[7][player] = "⬜";
					player -= 1;
				} else if (key.equals("d") && player < 7) {
					this.board[7][player] = "⬜";
					player += 1;
				}
				if (this.board[7][player].equals("🚙") || this.board[6][player].equals("🚙")) {
					playing = false;
					System.out.println("Perdiste!");

					// move cars down
					for (int i = 0; i < 7; i++)
						this.board[7 - i] = this.board[6 - i];

					for (int i = 0; i < this.board[0].length; i++)
						this.board[0][i] = "⬜";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}
