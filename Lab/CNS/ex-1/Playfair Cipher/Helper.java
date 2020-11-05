package playfairCipher;

import java.util.HashMap;
import java.util.HashSet;

import print.Print;

class Helper{
	int mat[][]=new int[5][5];
	Print p=new Print();
	public void displayMat() {

		for(int i=0;i<5;i++) {
			p.print("Row "+i+" -> ");
			for(int j=0;j<5;j++) {
				p.print((char)mat[i][j]+" ");
			}
			p.println("");
		}
	}
	public StringBuilder removeDups(String s,int l) {		
		StringBuilder res=new StringBuilder();
		HashSet<Integer> set=new HashSet<Integer>();		
		
		for(int i=0;i<l;i++) {
			if(set.contains((int)s.charAt(i))) {
				continue;
			}
			else {
				set.add((int)s.charAt(i));
				if(s.charAt(i)=='j') {
					res.append('i');
				}
				else{
					res.append(s.charAt(i));
				}
			}
		}
		return res;
	}
	
	public StringBuilder removeSpaces(String s,int l) {		
		StringBuilder res=new StringBuilder();
		for(int i=0;i<l;i++) {
			if(s.charAt(i)==' ') {
				continue;
			}
			else {
				res.append(s.charAt(i));
			}
		}
		return res;
	}
	public void createMatrix(String key) {
		//j-> 106
		p.println("The key is : "+key);
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		
		//add key characters to a hash set
		//at the same time add key chars to the mat
		int i=0,j=0,counter=0;
		
		for(int ch:key.toCharArray()) {
			map.put(ch,counter++);
			mat[i][j]=ch;
			if(j==4) {
				j=0;
				i++;
			}
			else {
				j++;
			}
		}
				
		int ch=97;
		for(;i<5;i++) {
			
			for(;j<5;j++) {				
				while(map.containsKey(ch)) {
					ch++;
				}
				if(ch==106) {
					//p.println("ch = "+ch+" "+(char)ch);
					ch++;					
				}
				mat[i][j]=ch++;				
			}
			j=0;
			
		}
		
		displayMat();
		
		
	}

	public String encryptHelper(int elm1,int elm2) {
		//displayMat();
		p.println("Incoming Elements: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		if(elm1==(int)'j') {
			elm1=(int)'i';
		}
		
		if(elm2==(int)'j') {
			elm2=(int)'i';
		}
		
		int indFound[][]=new int[2][2];
		
		for(int i=0;i<5;i++) {			
			for(int j=0;j<5;j++) {
				if(mat[i][j]==elm1) {
					indFound[0][0]=i;
					indFound[0][1]=j;
				}
				else if(mat[i][j]==elm2) {
					indFound[1][0]=i;
					indFound[1][1]=j;
				}
			}
		}		
		
		//check if they are in same row
		if(indFound[0][0]==indFound[1][0]) {
			elm1=mat[indFound[0][0]][((indFound[0][1])+1)%5];
			elm2=mat[indFound[0][0]][((indFound[1][1])+1)%5];
			p.println("Same Row: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		
		//check if they are in same column
		else if(indFound[0][1]==indFound[1][1]) {
			elm1=mat[((indFound[0][0])+1)%5][indFound[0][1]];
			elm2=mat[((indFound[1][0])+1)%5][indFound[0][1]];
			p.println("Same Col: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		else {
			elm1=mat[indFound[0][0]][indFound[1][1]];
			elm2=mat[indFound[1][0]][indFound[0][1]];
			p.println("Rectangle: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		
		StringBuilder res=new StringBuilder();
		res.append((char)elm1);
		res.append((char)elm2);
		return res.toString();
	}
	public StringBuilder addX(StringBuilder sb) {
		String s=sb.toString();
		int l=sb.length();
		StringBuilder res=new StringBuilder();
		
		char elm1=0,elm2=0;
		int i=0;
		while(i<l-1) {
			elm1=s.toLowerCase().charAt(i);
			elm2=s.toLowerCase().charAt(i+1);
			
			if(elm1==elm2) {
				if(elm1=='x') {
					res.append(elm1);
					res.append('q');
				}
				else{
					res.append(elm1);
					res.append('x');
				}
				
				i+=1;
			}
			else {
				res.append(elm1);
				res.append(elm2);
				i+=2;
			}
		}
		//p.println("Input String: "+s);
		//p.println("Processed String: "+res.toString());
		
		return res;
	}
	public void encrypt(StringBuilder text) {
		int l=text.length();
		
		if((l&1)==1) {
			//odd case
			text.append('z');
			l+=1;
		}
		
		StringBuilder res=new StringBuilder();
		
		for(int i=0;i<l;i+=2) {
			res.append(
					encryptHelper(
							(int)text.toString().toLowerCase().charAt(i)
							,(int)text.toString().toLowerCase().charAt(i+1)
					)
				);
		}
		
		p.println("Res = "+res.toString());
	}
	
	public String decryptHelper(int elm1,int elm2) {
		//displayMat();
		
		if(elm1==(int)'j') {
			elm1=(int)'i';
		}
		
		if(elm2==(int)'j') {
			elm2=(int)'i';
		}
		
		int indFound[][]=new int[2][2];
		
		for(int i=0;i<5;i++) {			
			for(int j=0;j<5;j++) {
				if(mat[i][j]==elm1) {
					indFound[0][0]=i;
					indFound[0][1]=j;
				}
				else if(mat[i][j]==elm2) {
					indFound[1][0]=i;
					indFound[1][1]=j;
				}
			}
		}		
		
		int i1=indFound[0][0],i2=indFound[1][0]
				,j1=indFound[0][1],j2=indFound[1][1];
		//check if they are in same row
		if(indFound[0][0]==indFound[1][0]) {
			j1-=1;
			j2-=1;
			if(j1<0) {
				j1=4;
			}
			if(j2<0) {
				j2=4;
			}
			elm1=mat[i1][j1];
			elm2=mat[i1][j2];
			p.println("Same Row: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		
		//check if they are in same column
		else if(indFound[0][1]==indFound[1][1]) {
			i1-=1;
			i2-=1;
			if(i1<0) {
				i1=4;
			}
			if(i2<0) {
				i2=4;
			}
			elm1=mat[i1][j1];
			elm2=mat[i2][j2];
			p.println("Same Col: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		else {
			elm1=mat[indFound[0][0]][indFound[1][1]];
			elm2=mat[indFound[1][0]][indFound[0][1]];
			p.println("Rectangle: elm1 = "+(char)elm1+" elm2 = "+(char)elm2);
		}
		
		StringBuilder res=new StringBuilder();
		res.append((char)elm1);
		res.append((char)elm2);
		return res.toString();
	}
	
	public void decrypt(StringBuilder text) {
		displayMat();
		int l=text.length();
		
		if((l&1)==1) {
			//odd case
			text.append('z');
			l+=1;
		}
		
		StringBuilder res=new StringBuilder();
		
		for(int i=0;i<l;i+=2) {
			res.append(
					decryptHelper(
							(int)text.toString().charAt(i)
							,(int)text.toString().charAt(i+1)
					)
				);
		}
		
		p.println("Res = "+res.toString());
	}
}

