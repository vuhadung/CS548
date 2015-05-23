package code.collection.DungVH;

import java.util.*;
import java.io.*;

public class ReverseString {

	public static void main(String[] args) throws IOException {

		System.out.println("Enter a string");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int i = 0, j = s.length() - 1;
		char tmp;

		// Cach 1
		char[] input = s.toCharArray();
		while (i <= j) {
			tmp = input[i];
			input[i] = input[j];
			input[j] = tmp;
			i++;
			j--;
		}
		System.out.println(input);

		// Reset
		i = 0;
		j = s.length() - 1;

		// Cach 2
		StringBuilder sb = new StringBuilder(s);
		while (i <= j) {
			tmp = s.charAt(i);
			sb.setCharAt(i, s.charAt(j));
			sb.setCharAt(j, tmp);
			i++;
			j--;
		}
		System.out.println(sb.toString());

		// Reset
		i = 0;
		j = s.length() - 1;
		while (i <= j) {
			System.out.println("i = " + i + ", j = " + j);
			System.out.println(++i);
			System.out.println(--j);
		}
		/*
		 * ++i vs i++: ++i thi ++ co do uu tien cao, nghia la tang i len 1 don
		 * vi truoc roi moi thuc hien cau lenh. i++ thi ++ co do uu tien thap,
		 * nghia la thuc hien cau lenh xong roi moi tang i len 1 don vi
		 */

	}

}
