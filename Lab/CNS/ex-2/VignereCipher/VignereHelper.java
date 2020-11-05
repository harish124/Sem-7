package ex2;

import print.Print;

public class VignereHelper {
	Print p=new Print();
	StringBuilder sb,key,res;
	int plainTextLen=0,keyLen=0;
	
	void init(StringBuilder sb,StringBuilder key) {
		this.sb=new StringBuilder(sb.toString().toUpperCase().replace(" ",""));
		this.key=new StringBuilder(key.toString().toUpperCase().replace(" ",""));
		res=new StringBuilder();
		plainTextLen=this.sb.length();
		keyLen=this.key.length();
		
		
		processKey();
	}
	
	void processKey() {
		if(keyLen<plainTextLen) {
			int diff=plainTextLen-keyLen;
			
			for(int i=0;i<diff;i++) {
				key.append(key.charAt(i%keyLen));
			}
		}
	}
	
	void encrypt(){
		p.println("Text: "+sb+"\nKey: "+key);
		for(int i=0;i<plainTextLen;i++) {
			int x=(sb.charAt(i)+key.charAt(i))%26;
			x+=97;
			
			res.append(
					(char)x	 
					);
		}
		p.println("Cipher Text = "+res);
	}
	
	void decrypt(){
		for(int i=0;i<plainTextLen;i++) {
			res.append(
						(char)(((sb.charAt(i)-key.charAt(i))+26)%26 + 97)
					);
		}
		p.println("Decrypted Text = "+res);
	}

}
