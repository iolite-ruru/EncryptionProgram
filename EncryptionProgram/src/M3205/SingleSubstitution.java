package M3205;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SingleSubstitution extends JFrame {

	private JPanel contentPane;
	private static String tableHeader[] = new String[26];
	private static String tableData[][] = new String[1][26];
	private static char alphabetBoard[][] = new char[2][26];
	private static JTextArea edtEncryption;
	private static JTextArea edtDecryption;
	private static JTable table;
	
	//private static char alphabetBoard[] = new char[26];
	//private static char endoingBoard[] = new char[26];
	//private static boolean oddFlag = false; //글자수 출력
	
	private static String zCheck ="";
	private static String plain; //평문
	private static String encryption; //암호문
	private static String decryption; //복호문
	private static String key;
	
	private static void initialize() {
		encryption = "";
		decryption = "";
		String blankCheck="";
		int blankCheckCount=0;
		
		setBoard(key);
		
		for( int i = 0 ; i < plain.length() ; i++ ) {
			if(plain.charAt(i)==' ') { //공백제거 
				plain = plain.substring(0,i)+plain.substring(i+1,plain.length());
				blankCheck+=10;
			} else {
				blankCheck+=0;
			}
			
			if(plain.charAt(i)=='z') { //Z->Q
				plain = plain.substring(0,i)+'q'+plain.substring(i+1,plain.length());
				zCheck+=1;
			} else {
				zCheck+=0;
			}
		}
	}
	
	private static void setEncryption(){
		
		for(int i=0; i<plain.length(); i++) {
			for(int j=0; j<26; j++) {
				if(plain.charAt(i) == alphabetBoard[0][j]) { //둘이 같은지를 인식 못하고 있음 => 대소문자 구별
					encryption += alphabetBoard[1][j];
					break;
				}
			}
			//System.out.println("----------");
		}
		
	}
	
	private static void setDecryption(){
		
		for(int i=0; i<encryption.length(); i++) {
			for(int j=0; j<26; j++) {
				if(encryption.charAt(i) == alphabetBoard[1][j]) { //둘이 같은지를 인식 못하고 있음 => 대소문자 구별
					decryption += alphabetBoard[0][j];
					break;
				}
			}
		}
		
	}

	private static void setBoard(String key) {
		String keyForSet = "";					// 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false;		// 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수.
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가.
				
		//중복문자 처리
		for( int i = 0 ; i < key.length() ; i++ ) {
			for( int j = 0 ; j < keyForSet.length(); j++ ) { //길이 유동적
				if(key.charAt(i)==keyForSet.charAt(j)) {
					duplicationFlag = true;
					break;
				}
			}
			if(!(duplicationFlag))
				keyForSet += key.charAt(i); //중복이 없다면, 현 알파벳 추가
			duplicationFlag = false; //다시 false로 설정해줌
		}
		
		for( int i = 0 ; i < alphabetBoard[0].length; i++ ) {
			alphabetBoard[0][i] = (char)(i + 'a');
			alphabetBoard[1][i] = keyForSet.charAt(keyLengthCount++);
			
			tableHeader[i] = Character.toString(alphabetBoard[0][i]);
			tableData[0][i] = Character.toString(alphabetBoard[1][i]);
		}
	}

	public SingleSubstitution(String plain, String key) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("3205");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edtEncryption = new JTextArea();
		edtEncryption.setBounds(112, 208, 562, 103);
		edtEncryption.setEditable(false);
		contentPane.add(edtEncryption);
		edtEncryption.setColumns(500);
		
		edtDecryption = new JTextArea();
		edtDecryption.setBounds(112, 362, 562, 43);
		edtDecryption.setEditable(false);
		contentPane.add(edtDecryption);
		edtDecryption.setColumns(500);
		
		JLabel lbEncryption = new JLabel("암호문");
		lbEncryption.setBounds(112, 172, 82, 21);
		contentPane.add(lbEncryption);
		
		JLabel lbDecryption = new JLabel("복호문");
		lbDecryption.setBounds(112, 326, 82, 21);
		contentPane.add(lbDecryption);
		
		JButton btnDecode = new JButton("복호화");
		btnDecode.setBounds(443, 466, 129, 29);
		contentPane.add(btnDecode);
		
		JButton btnReturn = new JButton("다시 하기");
		btnReturn.setBounds(589, 466, 129, 29);
		contentPane.add(btnReturn);
		
		JLabel lbBoard = new JLabel("암호판");
		lbBoard.setBounds(112, 61, 82, 21);
		contentPane.add(lbBoard);
		
		this.plain = plain;
		this.key = key;
		
		initialize();
		setEncryption();
		System.out.println(encryption); //nullapppp
		edtEncryption.setText(encryption);
		
		//DefaultTableModel model = new DefaultTableModel(tableData, tableHeader);
		
		for (int i = 0; i < 26; i++) {
			tableData[0][i] = Character.toString(alphabetBoard[1][i]);
		}
		
		table = new JTable(new DefaultTableModel(
			tableData, tableHeader
//			new String[] {
//				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
//			}
		));
		table.setFillsViewportHeight(true);
		table.setBounds(35, 97, 742, 60);
		//table.setTableHeader(null);
		contentPane.add(table);
		
		btnDecode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDecryption();
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
