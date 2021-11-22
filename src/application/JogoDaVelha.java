package application;

import java.util.Scanner;

import model.exceptions.GameExceptions;

public class JogoDaVelha {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] tabuleiro = new int[3][3];
		
		int rodada = 1, jogador = 1;
		boolean vitoria = false;
		
		System.out.println("-----JOGO INICIADO-----");
		while (rodada < 10 && !vitoria) {
			try {
				System.out.println("Rodada: " + rodada + "   Jogador: " + jogador);
				System.out.println();
				printBoard(tabuleiro);
				System.out.print("Digite a coluna e linha: ");
				String s = convertePosicao(sc);
				int linha = Integer.parseInt(s.substring(0, 1));
				int coluna = Integer.parseInt(s.substring(1));
				jogador = (jogador == 1) ? 2: 1;
				marcandoTabuleiro(jogador, linha, coluna, tabuleiro);
				System.out.println();
				if (testVitoria(tabuleiro, jogador)) {
					printBoard(tabuleiro);
					vitoria = true;
					int jogadorR = (jogador == 2) ? 1: 2;
					System.out.println("-----O JOGADOR "+ jogadorR +" GANHOU-----");
				}
				rodada++;
				if (rodada == 10 && !vitoria) {
					System.out.println("Empate!");
				}
			}
			catch (GameExceptions e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
			}
			catch (NumberFormatException e) {
				System.out.println();
				System.out.println("Erro: Digite novamente");
				System.out.println();
			}
		}
		sc.close();
	}
	
	public static void printBoard(int[][] mat) {
		
		for (int i=0; i<mat.length; i++) {
			System.out.print((3 - i) + " ");
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j] == 0) {
					System.out.print("-");;
				}
				if (mat[i][j] == 1) {
					System.out.print("O");;
				}
				if (mat[i][j] == 2) {
					System.out.print("X");;
				}
				if (j<2) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
		System.out.println("  a   b   c");
		System.out.println();
	}
	
	public static void marcandoTabuleiro(int jogador, int linha, int coluna, int[][] mat) {
		if (mat[linha][coluna] != 0) {
			throw new GameExceptions("Erro! Escolha uma posição vazia");
		}
		if (jogador == 2) {
			mat[linha][coluna] = 2;	
		}
		else {
			mat[linha][coluna] = 1;
		}
	}
	
	public static String convertePosicao(Scanner sc) {
		String s = sc.next();
		if (s.charAt(0) != 'a' && s.charAt(0) != 'b' && s.charAt(0) != 'c') {
			throw new GameExceptions("Opção de coluna invalida! Digite apenas: 'a', 'b' ou 'c'");
		}
		char column = s.charAt(0);
		if (Integer.parseInt(s.substring(1)) != 1 && Integer.parseInt(s.substring(1)) != 2 && Integer.parseInt(s.substring(1)) != 3) {
			throw new GameExceptions("Opção de linha invalida! Digite apenas: 1, 2 ou 3");
		}
		int row = Integer.parseInt(s.substring(1));
		String linha = String.valueOf(3 - row);
		String coluna = String.valueOf(column - 'a');
		return linha + coluna;
	}
	
	public static boolean testVitoria(int[][] tabuleiro, int jogador) {
		int aux = (jogador == 2) ? 2 : 1;
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