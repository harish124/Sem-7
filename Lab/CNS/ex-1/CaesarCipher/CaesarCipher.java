package caesarCipher;

import print.Print;
import java.util.Date;

import java.util.Scanner;
import java.lang.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CaesarCipher {
	//a-97 z-122
	//A-65 Z-90
	public static Print p;

	static String caesarCipher(StringBuilder sb, int key) {
		StringBuilder res=new StringBuilder();
		p.println("Plain Text: "+sb.toString());
		for(int ch :sb.toString().toLowerCase().toCharArray()) {	
			if((char)ch==" ".charAt(0)) {
				res.append(" ");
				continue;
			}
			int newIndex=ch+key;
			if(newIndex>122) {
				newIndex-=26;
				res.append((char)newIndex);
			}			
			else {				
				res.append((char)newIndex);
			}
		}
		p.println("Encrypted Text: "+res.toString());
		return res.toString();
	}
	
	static String decrypt(String sb,int key) {
		
		StringBuilder res=new StringBuilder();
		p.println("Encrypted Text: "+sb);
		
		for(int ch:sb.toCharArray()) {
			if((char)ch==' ') {
				res.append(" ");				
			}
			else {
				int newInd=ch-key;
				
				if(newInd<97) {
					newInd+=26;
				}
				res.append((char)newInd);
			}
		}
		p.println("Decrypted Text: "+res);
		
		return res.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p = new Print();
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb=new StringBuilder("");
		int key=-1;
		//p.print(""+((char) ((int)'a'-2+26)));
		
		boolean bool=true;
		while (bool) {
			
			p.print("\nWelcome to Caesar Cipher!\n" +
					"1.Read Plain/Encrypted Text and Key\n" + 
					"2.Encrypt\n" + "3.Decrypt\n"
					+ "4.Exit");
			int n=sc.nextInt();
			
			switch(n) {
				case 1:
					System.out.print("Enter any String: ");
					sb=new StringBuilder();
					sb.append(sc.next());
					String s=sc.nextLine();
					//p.println("s = "+s);
					sb.append(s.toLowerCase());
					p.println("Enter the key: ");
					key=sc.nextInt();
					if(key<0 || key>25) {
						p.println("Invalid Key");
						p.println("KEY Value range is [0,25]");
						sb=new StringBuilder();
						key=0;	//no encryption
						
					}
					break;
				case 2:
					caesarCipher(sb,key);
					break;
				
				case 3:								
					decrypt(sb.toString(),key);
					break;
					
				default:
					break;
			}
		}

	}

}
