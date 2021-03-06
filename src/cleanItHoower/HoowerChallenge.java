package cleanItHoower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class HoowerChallenge {
	public static void hoowerAndClean(int[][] grid, int[] initPos,		//returns the final hoower position and number of patches cleaned
			Queue<Character> instrQ) {
		int cleanCounter = 0;
		int currI = initPos[0];											//current x co-ordinate
		int currJ = initPos[1];											//current y co-ordinate
		int currPos = grid[initPos[0]][initPos[1]];
		if (currPos == -1) {
			cleanCounter += 1;
			grid[currI][currJ] = 0;
		}
		while (!instrQ.isEmpty()) {										//Hoower moves based on Instructions
			char instruction = instrQ.poll();
			switch (instruction) {
			case 'N':
				if (currJ + 1 <= 4)
					currJ += 1;
				currPos = grid[currI][currJ];
				if (currPos == -1) {
					cleanCounter += 1;
					grid[currI][currJ] = 0;
				}
				break;
			case 'E':
				if (currI + 1 <= 4)
					currI += 1;
				currPos = grid[currI][currJ];
				if (currPos == -1) {
					cleanCounter += 1;
					grid[currI][currJ] = 0;
				}
				break;
			case 'S':
				if (currJ - 1 >= 0)
					currJ -= 1;
				currPos = grid[currI][currJ];
				if (currPos == -1) {
					cleanCounter += 1;
					grid[currI][currJ] = 0;
				}
				break;
			case 'W':
				if (currI - 1 >= 0)
					currI -= 1;
				currPos = grid[currI][currJ];
				if (currPos == -1) {
					cleanCounter += 1;
					grid[currI][currJ] = 0;
				}
				break;
			default:
				System.out.println("Invalid Instruction");
				break;
			}
		}
		System.out.println(currI + " " + currJ);
		System.out.println(cleanCounter);
	}

	public static int[] returnIntValues(String[] tempArr) {		//returns int array
		int[] intArr = new int[tempArr.length];
		intArr[0] = Integer.parseInt(tempArr[0]);
		intArr[1] = Integer.parseInt(tempArr[1]);
		return intArr;
	}

	public static String[] splitIntoArray(String str) {			//returns line as String Array
		String[] tempArr = str.split("\\s+");
		return tempArr;
	}

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("input.txt"));
			String[] gridArr = splitIntoArray(br.readLine());
			int[][] grid = new int[Integer.parseInt(gridArr[0])][Integer
					.parseInt(gridArr[1])];
			Queue<Character> instrQ = new LinkedList<Character>();
			int[] initPos = returnIntValues(splitIntoArray(br.readLine()));
			String line = br.readLine();
			while (line != null) {
				String[] tempArr = splitIntoArray(line);
				if (tempArr.length > 1) {						//the array is split into 2 on the basis of space
					int[] dirtPoint = returnIntValues(tempArr);
					grid[dirtPoint[0]][dirtPoint[1]] = -1;
				} else {
					String strInstr = line;						//No spaces in line leads to only 1 split array -> Instruction set has started
					for (int i = 0; i < strInstr.length(); i++)
						instrQ.add(strInstr.charAt(i));
				}
				line = br.readLine();
			}
			hoowerAndClean(grid, initPos, instrQ);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
