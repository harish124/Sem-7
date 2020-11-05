package ex2;

import java.util.Scanner;


import print.Print;

public class VignereCipher {
	static VignereHelper h = new VignereHelper();	
	static StringBuilder sb, key;
	static Scanner sc;
	static Print p;

	static void init() {
		sc = new Scanner(System.in);
		sb = new StringBuilder("");
		p = new Print();
		key = new StringBuilder();
	}

	public static void main(String[] args) {

		init();
		// j - 106
		
		
		boolean bool = true;
		while (bool) {

			p.print("\nWelcome to VignereCipher!\n" + "1.Read Plain/Encrypted Text and Key\n" + "2.Encrypt\n"
					+ "3.Decrypt\n" + "4.Exit");
			int n = sc.nextInt();

			switch (n) {
			case 1:
				p.println("Enter any String: ");
				sb = new StringBuilder();
				key = new StringBuilder();
				sb.append(sc.next());
				String s = sc.nextLine();
				// p.println("s = "+s);
				sb.append(s.toLowerCase());

				p.println("Enter the key: ");
				s = sc.nextLine();
				key.append(s);
				h.init(sb, key);

				break;
			case 2:
				h.encrypt();
				break;

			case 3:
				h.decrypt();
				break;

			case 4:
				bool = false;
				break;
			default:
				bool = false;
				break;
			}
		}

	}
}