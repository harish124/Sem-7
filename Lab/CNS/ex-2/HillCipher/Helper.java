package ex2;

import print.Print;


public class Helper {
	int plainMat[],resMat[];
	int keyMat[][];
	int plainTextLen=0;
	int keyLen=0;
	Print p=new Print();
	
	void init(int plainTextLen,StringBuilder sb,StringBuilder key) {
		plainMat=new int[plainTextLen];
		resMat=new int[plainTextLen];
		keyMat=new int[plainTextLen][plainTextLen];
		this.plainTextLen=plainTextLen;
		this.keyLen=key.length();
		
		int diff= Math.abs(
				(int)Math.pow(plainTextLen,2) - key.length()
				);
		if(diff!=0) {
			//making key a square matrix of order n*n ; n->length of plainText
			for(int i=0;i<diff;i++) {
				key.append(key.charAt(i%key.length()));
			}
		}
		
		int cnt=0;
		for(int i=0;i<plainTextLen;i++) {			
			plainMat[i]=(int)sb.charAt(i)-97;
			for(int j=0;j<plainTextLen;j++) {
				keyMat[i][j]=(int)key.charAt(cnt++)-97;
			}
		}
		displayMat(plainTextLen);
	}
	public void displayMat(int n) {
		for(int i=0;i<n;i++) {
			p.print("Row "+i+" -> ");
			for(int j=0;j<n;j++) {
				p.print((char)(keyMat[i][j]+97)+" ");
			}
			p.println("");
		}
	}
	
	public void encrypt() {
		for(int i=0;i<plainTextLen;i++) {						
			for(int k=0;k<plainTextLen;k++) {
				resMat[i]+=keyMat[i][k]*plainMat[k];
			}
			resMat[i]%=26;							
		}
		
		for(int i=0;i<plainTextLen;i++) {
			p.println("Row - "+i+"-> "+(char)(resMat[i]+97));
		}
	}
}
