package M3205;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class SingleSubstitution extends JFrame {

	private JPanel contentPane;
	//private static String tableHeader[] = new String[26];
	private static String tableData[][] = new String[1][26];
	private static char alphabetBoard[][] = new char[2][26];
	private static JTextArea edtEncryption;
	private static JTextArea edtDecryption;
	private static JTable table;
	
	//private static char alphabetBoard[] = new char[26];
	//private static char endoingBoard[] = new char[26];
	//private static boolean oddFlag = false; //글자수 출력
	private static String blankCheck="";
	private static int blankCheckCount=0;
	private static String zCheck ="";
	private static String plain; //평문
	private static String encryption; //암호문
	private static String decryption; //복호문
	private static String key;
	
	private static void initialize() {
		encryption = "";
		decryption = "";

		setBoard(key);
		
		for( int i = 0 ; i < plain.length() ; i++ ) {
			if(plain.charAt(i)==' ') {
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
		decryption = "";
		for(int i=0; i<encryption.length(); i++) {
			for(int j=0; j<26; j++) {
				if(encryption.charAt(i) == alphabetBoard[1][j]) {
					decryption += alphabetBoard[0][j];
					break;
				}
			}
		}
		
	}

	private static void setBoard(String key) {
		String keyForSet = "";					// 중복된 문자가 제거된 문자열을 저장할 문자열
		boolean duplicationFlag = false;		// 문자 중복을 체크하기 위한 flag 변수
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가
				
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
			duplicationFlag = false;
		}
		
		for( int i = 0 ; i < alphabetBoard[0].length; i++ ) {
			alphabetBoard[0][i] = (char)(i + 'a');
			alphabetBoard[1][i] = keyForSet.charAt(keyLengthCount++);
			
			//tableHeader[i] = Character.toString(alphabetBoard[0][i]);
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
		edtEncryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		edtEncryption.setEditable(false);
		edtEncryption.setBounds(112, 261, 562, 103);
		contentPane.add(edtEncryption);
		edtEncryption.setColumns(500);
		
		edtDecryption = new JTextArea();
		edtDecryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		edtDecryption.setEditable(false);
		edtDecryption.setBounds(112, 415, 562, 43);
		contentPane.add(edtDecryption);
		edtDecryption.setColumns(500);
		
		JLabel lbTitle = new JLabel("시저치환");
		lbTitle.setFont(new Font("D2Coding", Font.BOLD, 30));
		lbTitle.setBounds(329, 25, 129, 86);
		contentPane.add(lbTitle);
		
		JLabel lbEncryption = new JLabel("암호문");
		lbEncryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		lbEncryption.setBounds(112, 225, 82, 21);
		contentPane.add(lbEncryption);
		
		JLabel lbDecryption = new JLabel("복호문");
		lbDecryption.setFont(new Font("D2Coding", Font.PLAIN, 18));
		lbDecryption.setBounds(112, 379, 82, 21);
		contentPane.add(lbDecryption);
		
		JButton btnDecode = new JButton("복호화");
		btnDecode.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnDecode.setBounds(465, 499, 129, 29);
		contentPane.add(btnDecode);
		
		JButton btnReturn = new JButton("다시 하기");
		btnReturn.setFont(new Font("D2Coding", Font.PLAIN, 18));
		btnReturn.setBounds(611, 499, 129, 29);
		contentPane.add(btnReturn);
		
		JLabel lbBoard = new JLabel("암호판");
		lbBoard.setFont(new Font("D2Coding", Font.PLAIN, 18));
		lbBoard.setBounds(112, 114, 82, 21);
		contentPane.add(lbBoard);
		
		//셋팅
		this.plain = plain;
		this.key = key;
		
		initialize();
		setEncryption();
		System.out.println(encryption);
		edtEncryption.setText(encryption);
		
		//DefaultTableModel model = new DefaultTableModel(tableData, tableHeader);
		
		for (int i = 0; i < 26; i++) {
			tableData[0][i] = Character.toString(alphabetBoard[1][i]);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(113, 140, 562, 70);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable(new DefaultTableModel(
			tableData,
			//tableHeader
			new String[] {
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
			}
		) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			});
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("D2Coding", Font.PLAIN, 18));
		table.setBounds(113, 139, 562, 72);
		table.setRowHeight(35);
		table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setPreferredSize(new Dimension(0, 32));
        
		//가운데 정렬
        TableColumn column;
        DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
        colRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel columnModel = table.getColumnModel();
        
        for (int i = 0; i < 26; i++) {
			column = table.getColumnModel().getColumn(i);
			columnModel.getColumn(i).setCellRenderer(colRenderer);
		}
        
		panel.add(new JScrollPane(table));

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
