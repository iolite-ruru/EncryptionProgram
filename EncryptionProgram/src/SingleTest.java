import java.util.ArrayList;
import java.util.Scanner;

public class SingleTest {
	public static char alphabetBoard[][] = new char[2][26];
	public static boolean oddFlag = false; //글자수 출력
	public static String zCheck ="";

	public static void main(String[] args) {
		String decryption; //복호문
		String encryption; //암호문
		
		Scanner sc = new Scanner(System.in); 	//입력을 위한 Scanner 정의
		System.out.print("암호화에 쓰일 키를 입력하세요 : ");
		String key = sc.nextLine();				//key 입력
		System.out.print("암호화할 문자열을 입력하세요 : ");
		String str =  sc.nextLine();				//문자열 입력(평문)
		String blankCheck="";
		int blankCheckCount=0;

		setBoard(key);							//암호화에 쓰일 암호판 세팅
		
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			if(str.charAt(i)==' ') //공백제거
			{
				str = str.substring(0,i)+str.substring(i+1,str.length());
				blankCheck+=10;
			}
			else
			{
				blankCheck+=0;
			}
			if(str.charAt(i)=='z') //z를 q로 바꿔줘서 처리함.
			{
				str = str.substring(0,i)+'q'+str.substring(i+1,str.length());
				zCheck+=1;
			}
			else 
			{
				zCheck+=0;
			}
		}
		
		encryption = strEncryption(key, str); //암호화
		
		//출력부분
		System.out.println("암호화된 문자열 : " + encryption);
	}

	
	
	private static String strEncryption(String key, String str){
		String encStr ="";
		
		for(int i=0; i<str.length(); i++) {
			for(int j=0; j<26; j++) {
				//System.out.println(alphabetBoard[0][j]);
				if(str.charAt(i) == alphabetBoard[0][j]) { //둘이 같은지를 인식 못하고 있음 => 대소문자 구별
					encStr += alphabetBoard[1][j];
					break;
				}
			}
			//System.out.println("----------");
		}
		return encStr;
	}

	private static void setBoard(String key) {
		String keyForSet = "";					// 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false;		// 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수.
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가.

				
		// 중복처리
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ ) //길이 유동적
			{
				if(key.charAt(i)==keyForSet.charAt(j))
				{
					duplicationFlag = true;
					break;
				}
			}
			if(!(duplicationFlag)) keyForSet += key.charAt(i); //중복이 없다면, 현 알파벳 추가
			duplicationFlag = false; //다시 false로 설정해줌
		}
		
		//배열에 대입
		for( int i = 0 ; i < alphabetBoard[0].length; i++ )
		{
			alphabetBoard[0][i] = (char)(i + 'a');
			alphabetBoard[1][i] = keyForSet.charAt(keyLengthCount++);
		}
		
		//출력
		for( int i = 0 ; i < alphabetBoard[0].length; i++ )
		{
			System.out.print(alphabetBoard[0][i]+"-");
		}		
		System.out.println();
		//출력
		for( int i = 0 ; i < alphabetBoard[0].length; i++ )
		{
			System.out.print(alphabetBoard[1][i]+"-");
		}
		System.out.println();
	}

}
