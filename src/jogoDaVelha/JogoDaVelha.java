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
			System.out.print("linha: ");
			int linha = sc.nextInt();
			System.out.print("coluna: ");
			int coluna = sc.nextInt();
			jogador = (jogador == 1) ? 2: 1;
			if (jogador == 2) {
				tabuleiro[linha][coluna] = "x";	
			}
			else {
				tabuleiro[linha][coluna] = "o";
			}
			System.out.println();
			printBoard(tabuleiro);
			System.out.println();
			if (testVitoria(tabuleiro, jogador)) {
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
	}
	
	public static boolean testVitoria(String[][] tabuleiro, int jogador) {
		String aux = (jogador == 2) ? "x" : "o";
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