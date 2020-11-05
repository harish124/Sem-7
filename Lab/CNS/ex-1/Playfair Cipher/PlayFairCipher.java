package playfairCipher;

import java.util.Scanner;

import print.Print;

public class PlayFairCipher {

	static Helper h=new Helper();
	static StringBuilder sb,key;
	static Scanner sc;
	static Print p;
	
	static void init() {
		sc = new Scanner(System.in);
		sb=new StringBuilder("");
		p=new Print();
		key=new StringBuilder();
	}
	public static void main(String[] args) {
		init();		
		//j - 106

		boolean bool=true;
		while (bool) {
			
			p.print("\nWelcome to PlayFair Cipher!\n" +
					"1.Read Plain/Encrypted Text and Key\n" + 
					"2.Encrypt\n" + "3.Decrypt\n"
					+ "4.Exit");
			int n=sc.nextInt();
			
			switch(n) {
				case 1:
					System.out.print("Enter any String: ");
					sb=new StringBuilder();
					key=new StringBuilder();
					sb.append(sc.next());
					String s=sc.nextLine();
					//p.println("s = "+s);
					sb.append(s.toLowerCase());
					sb=h.removeSpaces(sb.toString(), sb.length());	
					sb=h.addX(sb);
					
					p.println("Enter the key: ");
					s=sc.nextLine();
					key.append(s);
					key=h.removeSpaces(key.toString(), key.length());
					key=h.removeDups(key.toString(),key.length());
					
					if(key.length()>25) {
						p.println("Invalid Key");
						p.println("Key should be of length [0-25]");
					}
					else {
						h.createMatrix(key.toString());
					}
					break;
				case 2:
					h.encrypt(sb);
					break;
				
				case 3:								
					h.decrypt(sb);
					break;
				
				case 4:
					bool=false;
					break;
				default:
					bool=false;
					break;
			}
		}

	

	}

}
