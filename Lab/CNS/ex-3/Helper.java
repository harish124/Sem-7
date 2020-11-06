package ex3;

import print.Print;

public class Helper {
	private Print p=new Print();
	char mat[][];
	StringBuilder sb,res;
	int key;
	int plainTextLen=0;
	
	void init(StringBuilder sb,int key) {
		this.sb=new StringBuilder(sb.toString().toLowerCase().replace(" ",""));
		this.key=key;
		res=new StringBuilder();
		plainTextLen=this.sb.length();		
		mat=new char[key][plainTextLen];
		
		createMat();
						
	}
	
	void createMat() {
		int i=0,j=0;
		for(i=0;i<key;i++) {			
			for(j=0;j<plainTextLen;j++) {
				mat[i][j]=" ".charAt(0);
			}			
		}
		fillMat(sb.toString());
	}
	
	void displayMat() {
		for(int i=0;i<key;i++) {
			p.print("Row - "+i+" -> ");
			for(int j=0;j<plainTextLen;j++) {
				p.print(mat[i][j]+" ");
			}
			p.println("");
		}
	}
	
	void fillMat(String sb) {
		int i=0,j=0;
		boolean goDown=false;			
		
		while(j<plainTextLen) {		
			if(i==0 || i==(key-1)) {
				goDown=!goDown;
			}
			mat[i][j]=sb.charAt(j++);
			if(goDown) {
				i++;
			}
			else {
				i--;
			}
			
		}
		
		displayMat();
		
	}
	
	void readMat() {
		int i=0,j=0;
		boolean goDown=false;			
		res=new StringBuilder();
		while(j<plainTextLen) {		
			if(i==0 || i==(key-1)) {
				goDown=!goDown;
			}
			res.append(mat[i][j++]);
			//p.print(mat[i][j++]+"");
			if(goDown) {
				i++;
			}
			else {
				i--;
			}
			
		}
		p.println("");
		
		
	}
	
	void encrypt() {
		for(int i=0;i<key;i++) {			
			for(int j=0;j<plainTextLen;j++) {
				if(mat[i][j]==" ".charAt(0)) {
					continue;
				}
				else {
					res.append(mat[i][j]);
				}
			}			
		}				
	}
	
	void decrypt() {
		StringBuilder stars=new StringBuilder();
		for(int i=0;i<plainTextLen;i++) {
			stars.append("*");
		}
		
		fillMat(stars.toString());
		
		int index=0;
		for(int i=0;i<key;i++) {
			for(int j=0;j<plainTextLen && index<plainTextLen;j++) {
				if(mat[i][j]=='*') {
					mat[i][j]=sb.charAt(index++);
				}
			}
		}
		
		displayMat();
		readMat();
		
	}
	
}
