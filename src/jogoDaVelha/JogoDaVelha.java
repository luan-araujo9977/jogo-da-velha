package jogoDaVelha;

import java.util.Scanner;

public class JogoDaVelha {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[][] tabuleiro = new String[3][3];
		
		int rodada = 1, jogador = 1;
		boolean vitoria = false;
		
		System.out.println("-----JOGO INICIADO-----");
		while (rodada < 10 && !vitoria) {
			System.out.println("Rodada " + rodada);
			System.out.println("Jogador: " + jogador);
			System.out.println();
			printBoard(tabuleiro);
			System.out.println();
			System.out.print("coluna e linha: ");
			String s = convertendoPosicao(sc);
			int linha = Integer.parseInt(s.substring(0, 1));
			int coluna = Integer.parseInt(s.substring(1));
			jogador = (jogador == 1) ? 2: 1;
			marcandoTabuleiro(jogador, linha, coluna, tabuleiro);
			System.out.println();
			if (testVitoria(tabuleiro, jogador)) {
				printBoard(tabuleiro);
				System.out.println();
				vitoria = true;
				int jogadorR = (jogador == 2) ? 1: 2;
				System.out.println("-----O JOGADOR "+ jogadorR +" GANHOU-----");
			}
			rodada++;
		}
		sc.close();
	}
	
	public static void printBoard(String[][] mat) {
		for (int i=0; i<mat.length; i++) {
			System.out.print((3 - i) + " ");
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j] == null) {
					mat[i][j] = "-";
				}
				System.out.print(mat[i][j]);
				if (j<2) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
		System.out.println("  a   b   c");
	}
	
	public static void marcandoTabuleiro(int jogador, int linha, int coluna, String[][] tabuleiro) {
		if (jogador == 2) {
			tabuleiro[linha][coluna] = "X";	
		}
		else {
			tabuleiro[linha][coluna] = "O";
		}
	}
	
	public static String convertendoPosicao(Scanner sc) {
		String s = sc.next();
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		String linha = String.valueOf(3 - row);
		String coluna = String.valueOf(column - 'a');
		return linha + coluna;
	}
	
	public static boolean testVitoria(String[][] tabuleiro, int jogador) {
		String aux = (jogador == 2) ? "X" : "O";
		for (int i=0; i<tabuleiro.length; i++) {
			if ((tabuleiro[i][0] == aux) && (tabuleiro[i][1] == aux) && (tabuleiro[i][2] == aux)) {
				return true;	
			}
			if ((tabuleiro[0][i] == aux) && (tabuleiro[1][i] == aux) && (tabuleiro[2][i] == aux)) {
				return true;
			}
		}
		if ((tabuleiro[0][0] == aux) && (tabuleiro[1][1] == aux) && (tabuleiro[2][2] == aux)) {
			return true;
		}
		if ((tabuleiro[0][2] == aux) && (tabuleiro[1][1] == aux) && (tabuleiro[2][0] == aux)) {
			return true;
		}
		return false;
	}
}