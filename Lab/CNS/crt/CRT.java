package crt;

import java.util.Scanner;

import print.Print;
import modularInverse.ModularInverse;;

public class CRT {

	// x=b1(mod n1) //x=b2(mod n2) //x=b3(mod n3)
	// N=n1*n2*n3
	// N1= n2*n3 or N/n1

	// x= sum(bi*Ni*xi) (mod N)

	static int b[], n[],x[],N[];
	static Print p;

	static void init() {
		b = new int[3];
		n = new int[3];
		x = new int[3];
		N = new int[3];
		p = new Print();
	}
	
	
	static void solve() {
		int prod=n[0]*n[1]*n[2];
		
		for(int i=0;i<3;i++) {
			N[i]=prod/n[i];
		}
		
		ModularInverse mi=new ModularInverse();
		for(int i=0;i<3;i++) {
			x[i]=mi.modInverse(N[i], n[i]);
			p.println("\nx"+i+" ---> "+x[i]);
		}
		
		int ans=0;
		for(int i=0;i<3;i++) {
			ans+=(b[i]*x[i]*N[i]);
		}
		ans%=prod;
		
		p.println("\n-------------------------------");
		p.println("X = "+ans);
		p.println("\n-------------------------------");
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();

		boolean flag = true;
		Scanner sc=new Scanner(System.in);

		while (flag) {
			p.println("Welcome to Chinese Remainder Theorem\n" + "Press:\n" + "1.To enter the bi:\n" + "2.To enter ni:\n"
					+ "3.To solve\n"
					+ "Any other to exit\n");
			int ch=sc.nextInt();
			
			switch(ch) {
			case 1:
				p.println("\nEnter b1: ");
				b[0]=sc.nextInt();
				p.println("\nEnter b2: ");
				b[1]=sc.nextInt();
				p.println("\nEnter b3: ");
				b[2]=sc.nextInt();
				
				break;
				
			case 2:
				p.println("\nEnter b1: ");
				n[0]=sc.nextInt();
				p.println("\nEnter b2: ");
				n[1]=sc.nextInt();
				p.println("\nEnter b3: ");
				n[2]=sc.nextInt();
				
				break;
			
			case 3:
				solve();
				break;
				
			default:
				flag=false;
			}
		}
	}

}
