package org.sm.jdsa.string;

public class LongestCommonSubsequence {
	
	public int run(String string1, String string2) {
		char[] charArray1 = string1.toCharArray();
		char[] charArray2 = string2.toCharArray();
		int[][] lengthTable = new int[charArray1.length][charArray2.length];
		
		for (int i = 0; i < lengthTable.length; i++) {
			for (int j = 0; j < lengthTable[i].length; j++) {
				if (i == 0 || j == 0)
					lengthTable[i][j] = 0;
				else if (charArray1[i] == charArray2[j])
					lengthTable[i][j] = lengthTable[i - 1][j - 1] + 1;
				else
					lengthTable[i][j] = Math.max(lengthTable[i - 1][j], lengthTable[i][j - 1]);
			}
		}
		
		return lengthTable[charArray1.length - 1][charArray2.length - 1];
	}

}
