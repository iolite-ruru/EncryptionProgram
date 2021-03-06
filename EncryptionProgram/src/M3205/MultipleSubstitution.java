package M3205;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class MultipleSubstitution extends JFrame {

	private JPanel contentPane;
	private JTextArea edtEncryption;
	private JTextArea edtDecryption;
	private JTable tablePair;
	
	//private static String tableHeader[] = new String[26];
	private static String tableData[][] = new String[5][5];
	private static char alphabetBoard[][] = new char[5][5];
	private static boolean oddFlag = false; //짝수 홀수 판별
	private static String zCheck ="";
	
	private static String plain; //평문
	private static String encryption; //암호문
	private static String decryption; //복호문
	private static String key; //암호키
	private JTable table;
	private static String blankCheck="";
	private static int blankCheckCount=0;
	
	private static void initialize() {
		encryption = "";
		decryption = "";
		
		setBoard();
		
		for( int i = 0 ; i < plain.length() ; i++ ) {
			if(plain.charAt(i)==' ') { //공백제거 
				plain = plain.substring(0,i)+plain.substring(i+1,plain.length());
				blankCheck+=10;
			} else {
				blankCheck += 0;
			}
			
			if(plain.charAt(i)=='z') { //Z->Q
				plain = plain.substring(0,i)+'q'+plain.substring(i+1,plain.length());
				zCheck += 1;
			} else {
				zCheck += 0;
			}
		}
		


		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ')
				encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
		}
	}
	
	private static void setEncryption(){
		ArrayList<char[]> playFair = new ArrayList<char[]>();
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
		String encStr ="";
		
		for( int i = 0 ; i < plain.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = plain.charAt(i);
			try{
				if( plain.charAt(i) == plain.charAt(i+1))
				{
					tmpArr[1] = 'x';
					i--;
				}else{
					tmpArr[1] = plain.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e)
			{
				tmpArr[1] = 'x'; 
				oddFlag = true;
			}
			playFair.add(tmpArr);
		}
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			System.out.print(playFair.get(i)[0]+""+playFair.get(i)[1]+" ");
		}
		System.out.println();
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ ) //쌍자암호의 각각 위치체크
			{
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					if(alphabetBoard[j][k] == playFair.get(i)[0])
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1])
					{
						x2 = j;
						y2 = k;
					}
				}
			}
			
			//당연히 else if문 사용할 수밖에 없음. 행과 열이 동시에 같다면? 결국 같은 위치이기 때문에...
			if(x1 == x2) //행이 같은경우
			{
				tmpArr[0] = alphabetBoard[x1][(y1+1)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+1)%5];
			}
			else if(y1 == y2) //열이 같은 경우
			{
				tmpArr[0] = alphabetBoard[(x1+1)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+1)%5][y2];
			} 
			else //행, 열 모두 다른경우
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}
			
			encPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}
		
		
		encryption = encStr;
	}

	private static String setDecryption(String key, String str, String zCheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); //바꾸기 전 쌍자암호를 저장할 곳
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); //바꾼 후의 쌍자암호 저장할 곳
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //쌍자 암호 두 글자의 각각의 행,열 값
		String decStr ="";

		int lengthOddFlag = 1;
		
		
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i+1);
			playFair.add(tmpArr);
		}
		
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ )
			{
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					if(alphabetBoard[j][k] == playFair.get(i)[0])
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1])
					{
						x2 = j;
						y2 = k;
					}
				}
			}
			
			if(x1 == x2) //행이 같은 경우 각각 바로 아래열 대입
			{
				tmpArr[0] = alphabetBoard[x1][(y1+4)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+4)%5];
			}
			else if(y1 == y2) //열이 같은 경우 각각 바로 옆 열 대입
			{
				tmpArr[0] = alphabetBoard[(x1+4)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+4)%5][y2];
			}
			else //행, 열 다른경우 각자 대각선에 있는 곳.
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}
			
			decPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < decPlayFair.size() ; i++) //중복 문자열 돌려놓음
		{
			if(i!=decPlayFair.size()-1 && decPlayFair.get(i)[1]=='x' 
					&& decPlayFair.get(i)[0]==decPlayFair.get(i+1)[0])
			{	
				decStr += decPlayFair.get(i)[0];
			}
			else
			{
				decStr += decPlayFair.get(i)[0]+""+decPlayFair.get(i)[1];
			}
		}
		
		
		
		for(int i = 0 ; i < zCheck.length() ; i++ ) //z -> q
		{
			if( zCheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());
			
		}
		
		
		
		if(oddFlag) decStr = decStr.substring(0,decStr.length()-1);
		
		return decStr;
	}
	
	private static void setBoard() {
		String keyForSet = ""; //중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false; //문자 중복 체크
		int keyLengthCount = 0; //alphabetBoard에 keyForSet을 넣기 위한 count변수
		
		key += "abcdefghijklmnopqrstuvwxyz"; //키에 모든 알파벳이 추가되어 있어야 함.
		
		// 중복처리
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ )
			{
				if(key.charAt(i)==keyForSet.charAt(j))
				{
					duplicationFlag = true;
					break;
				}
			}
			if(!(duplicationFlag)) keyForSet += key.charAt(i);
			duplicationFlag = false;
		}
		
		//배열에 대입
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
				tableData[i][j] = Character.toString(alphabetBoard[i][j]);
			}
		}		
		
		//출력(Debug)
		/*
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				System.out.print(alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}		
		*/
	}
	
	public MultipleSubstitution(String plain, String key) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("3205");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitle = new JLabel("다중치환");
		lbTitle.setFont(new Font("D2Coding", Font.BOLD, 30));
		lbTitle.setBounds(329, 25, 129, 86);
		contentPane.add(lbTitle);
		
		edtEncryption = new JTextArea();
		edtEncryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		edtEncryption.setEditable(false);
		edtEncryption.setBounds(346, 170, 398, 111);
		contentPane.add(edtEncryption);
		edtEncryption.setColumns(500);
		
		edtDecryption = new JTextArea();
		edtDecryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		edtDecryption.setEditable(false);
		edtDecryption.setColumns(500);
		edtDecryption.setBounds(346, 343, 398, 108);
		contentPane.add(edtDecryption);
		
		JLabel lbEncryption = new JLabel("암호문");
		lbEncryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		lbEncryption.setBounds(339, 134, 82, 21);
		contentPane.add(lbEncryption);
		
		JLabel lbDecryption = new JLabel("복호문");
		lbDecryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		lbDecryption.setBounds(339, 307, 82, 21);
		contentPane.add(lbDecryption);
		
		JButton btnDecode = new JButton("복호화");
		btnDecode.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnDecode.setBounds(465, 499, 129, 29);
		contentPane.add(btnDecode);
		
		JButton btnReturn = new JButton("다시 하기");
		btnReturn.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnReturn.setBounds(611, 499, 129, 29);
		contentPane.add(btnReturn);
		
		JPanel panel = new JPanel();
		panel.setBounds(40, 170, 289, 281);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label = new JLabel("암호판");
		label.setFont(new Font("D2Coding", Font.PLAIN, 18));
		label.setBounds(40, 130, 80, 20);
		contentPane.add(label);
		
		//셋팅
		this.plain = plain;
		this.key = key;
		
		initialize();
		setEncryption();
		System.out.println(encryption);
		edtEncryption.setText(encryption);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("D2Coding", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			tableData,
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			});
		table.setTableHeader(null);
		table.setRowHeight(57);
		
		//가운데 정렬
        TableColumn column;
        DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
        colRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel columnModel = table.getColumnModel();
        
        for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
			columnModel.getColumn(i).setCellRenderer(colRenderer);
		}
		
		panel.add(table);
			
		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ')
				encryption = encryption.substring(0, i)+encryption.substring(i+1, encryption.length());
		}
		
		btnDecode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decryption = setDecryption(key, encryption, zCheck);
				for( int i = 0; i < decryption.length(); i++)
				{
					if(blankCheck.charAt(i) == '1')
					{
						decryption = decryption.substring(0, i)+" "+decryption.substring(i, decryption.length());
					}
				}
				System.out.println(decryption);
				edtDecryption.setText(decryption);
			}
		});
		
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IntroScreen subFrame = new IntroScreen();
				subFrame.setVisible(true);
				dispose();
			}
		});
	}
}
