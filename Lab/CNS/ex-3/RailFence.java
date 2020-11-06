package ex3;

import java.util.*;
import ex3.Helper;
import print.Print;

class RailFence{
	
	static Helper h = new Helper();	
	static StringBuilder sb;
	static int key;
	static Scanner sc;
	static Print p;

	static void init() {
		sc = new Scanner(System.in);
		sb = new StringBuilder("");
		p = new Print();
		key=-1;
	}

	public static void main(String[] args) {

		init();
				
		boolean bool = true;
		while (bool) {

			p.print("\nWelcome to RailFence Cipher!\n" + "1.Read Plain/Encrypted Text and Key\n" + "2.Encrypt\n"
					+ "3.Decrypt\n" + "4.Exit");
			int n = sc.nextInt();

			switch (n) {
			case 1:
				p.println("Enter any String: ");
				sb = new StringBuilder();
				key=-1;
				
				sb.append(sc.next());
				String s = sc.nextLine();			
				sb.append(s.toLowerCase());

				p.println("Enter the key: ");
				key=sc.nextInt();
				h.init(sb, key);

				break;
			case 2:
				h.encrypt();
				p.println("Cipher Text = "+h.res);
				break;

			case 3:
				h.decrypt();
				p.println("Decrypted Text = "+h.res);
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