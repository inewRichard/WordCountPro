import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class doc {
	static String name;
	static String text;
	static String word="";
	static String[] stoplist;
	public static int linecount =1,allwordcount = 0,wordcount =0,symbolcount=0,codeline=0,nullline = 0,balaline =666;
	static boolean[] temp ={};
	public doc(){}
	public doc(String n,String t,boolean[] para){
		name = n;
		text = t;
		temp = para;
		cal();
	}
	public String  mes(){
		String message= "name:"+name+"\n";
		if(temp[2] ==true)
			message +="\tsymbolcount:"+symbolcount;
		
		if(temp[1] == true)
			message +="\twordcount:"+wordcount;
		
		if(temp[0]==true)
			message +="\tlinecount:"+linecount;
		if(temp[3] == true)
			message +="\tcodeline:"+codeline+"\tnullline:"+nullline+"\tbalaline:"+balaline;
		return message;
	}
	public static boolean isChinese(char c) {  
	    return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断  
	}  	
	public static boolean isword(char c) {   
	    return (c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9');  
	}  
	public static boolean isnum(char c) {  
	    return (c>='0'&&c<='9');  
	}
	public static boolean issymbol(char c){
		return !(isChinese(c)||isword(c));
	}
	private  static boolean isStop(String s){
		word = "";
		boolean isstop = false;
		if(doc.stoplist == null)
			return false;
		for(String sp:doc.stoplist){
			if(isequal(sp,s)){
				isstop = true;
				break;
			}		
		}
		return isstop;
	}
	private static boolean isequal(String sp, String s) {
		// TODO Auto-generated method stub
		if(sp.length() != s.length())
			return false;
		
			for(int i=0;i<sp.length();i++){
				if(sp.charAt(i)!=s.charAt(i))
					return false;
			}
		return true;
	}
	public static void cal(){
		ArrayList linewords = new ArrayList();
		ArrayList<String> line = new ArrayList<String>();
		String ts ="";
		int lineb=0;
		boolean isaword = false;
			//多少行是指的多少回车吗
			//单词数 ，如果是汉语呢，那应该是一个字是一个词咯？
		for(int i =0;i<text.length();++i){
			char temp = text.charAt(i);
			ts+=temp;
			if(temp=='\n'){
				linewords.add(allwordcount-lineb);
				lineb =allwordcount;
				linecount +=1;
				line.add(ts);
				ts="";
			}
			else if(isword(temp)&&!isnum(temp)){
				isaword = true;
			}
			else if(isChinese(temp)){
				allwordcount+=1;
				if(!isStop(""+temp));
					wordcount+=1;
				if(isaword)
					allwordcount +=1;
				if(isaword&&!isStop(word))
					wordcount +=1;
				isaword = false;
			}
			else if(issymbol(temp))
			{
				if(isaword)
					allwordcount +=1;
				if(isaword&&!isStop(word))
					wordcount +=1;
				isaword = false;
				if(issymbol(temp)){
					symbolcount +=1;
				}
			}
			if(isaword)
				word +=temp;
		}
		line.add(ts);
		linewords.add(allwordcount-lineb);
		allwordcount = isaword?allwordcount+1:allwordcount;
		if(isaword&&!isStop(word))
			wordcount++;
		/*for(int i=0;i<line.size();++i){			//solve the line problem
			System.out.println(line.get(i));
		}*/
		for(int i =0;i<linewords.size();i++){			//codeline,line and line you ***
			if((int)linewords.get(i)>1){
				codeline ++;
			}else if(isnullline(line.get(i)))
				nullline ++;
			else if(iszhushi(line.get(i))){
				balaline ++;
			}
				
		}
		
	}
	
	private static boolean iszhushi(String string) {
		// TODO Auto-generated method stub
		return false;
	}
	private static boolean isnullline(String str) {
		// TODO Auto-generated method stub
		boolean flag = false;
		for(int i=0;i<str.length();i++){
			if((!(str.charAt(i)==' '||str.charAt(i) =='\r'||str.charAt(i) =='\t'||str.charAt(i)=='\n'))&&!flag){
				flag = true;
			}else if((!(str.charAt(i)==' '||str.charAt(i) =='\r'||str.charAt(i) =='\t'||str.charAt(i)=='\n'))&&flag){
				return false;
			}
		}
		return true;
	}

}