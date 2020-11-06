package ex6;

import java.math.BigInteger;
import java.util.Scanner;

import print.Print;
public class RSA {

	static Print p;
	static StringBuilder sb,res;
	static Scanner sc;
	static BigInteger n,pr,q;	//pub.key(n and e)  //priva.key (d= (1+k*phi)/e) for some integer k
	static BigInteger privateKey;
	static int k=2,e=2;
	static BigInteger phi;
	
	static void init() {		
		p=new Print();
		sc=new Scanner(System.in);
		res=new StringBuilder();
		privateKey=new BigInteger("0");
	}
	 
	static void calcE() {
		BigInteger E=new BigInteger(e+"");
		
		while((E.compareTo(phi))==-1) {
			boolean flag=E.gcd(phi).compareTo(new BigInteger("1"))==0;
			if(flag==true) {
				break;
			}
			else {
				e++;
				E=new BigInteger(e+"");
			}
		}
		
		//p.println("e = "+e);
			
	}
	
	static void generatePubKey() {
		n=pr.multiply(q);		
		calcE();
	}
		
	static void generatePrivKey() {
		
		phi=pr.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));		
		generatePubKey();
		BigInteger E=new BigInteger(e+"");
		privateKey=E.modInverse(phi);				
		
	}
	
	static void encrypt() {
		res=new StringBuilder();
		p.println("Res = "+res);
		BigInteger res1=new BigInteger(sb.toString());
		BigInteger cipher= res1.modPow(new BigInteger(e+""),n);
				
		res=new StringBuilder();
		
		res.append(cipher);
				
		p.println("Cipher Text: "+res);
	}
	
	static void decrypt() {
		//p.println("Cipher = "+sb.toString()+"\npk = "+privateKey);
		BigInteger cipher=new BigInteger(sb.toString());
		BigInteger decryptedText= cipher.modPow(privateKey, new BigInteger(n+""));
		
		//p.println("DT: "+decryptedText+"\nn = "+n);
		res=new StringBuilder();
		
		for(char ch:decryptedText.toString().toCharArray()){
			//p.println("ch = "+(Integer.parseInt(ch+"")+96));
			res.append(
						Integer.parseInt(ch+"")
					);
		}
		
		p.println("Decrypted Text: "+res);
	}
	
	public static void main(String[] args) {
		init();		
		
		boolean bool=true;
		while (bool) {
			
			p.print("\nWelcome to RSA Asymmetric Cipher!\n" +
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
					sb.append(s.toLowerCase().replace(" ",""));								
					
					
					p.println("Enter the first prime: ");
					int prime=sc.nextInt();
					pr=new BigInteger(prime+"");
					p.println("Enter the second prime: ");
					prime=sc.nextInt();
					q=new BigInteger(prime+"");
					//pr=53;
					//q=59;
					generatePrivKey();
					
					
										
					break;
				case 2:
					encrypt();
					break;
				
				case 3:								
					decrypt();
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
