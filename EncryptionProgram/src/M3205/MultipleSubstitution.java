package M3205;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class MultipleSubstitution extends JFrame {

	private JPanel contentPane;
	private static char alphabetBoard[][] = new char[5][5];
	//private static boolean oddFlag = false; //���ڼ� ���
	private static String zCheck ="";
	private String decryption;
	private String encryption;
	private String key;
	private JTextField edtEncryption;
	private JTextField edtDecryption;
	private JTable tablePair;
	private JTextField txtA;
	private JTextField textField;

	public MultipleSubstitution() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("3205");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edtEncryption = new JTextField();
		edtEncryption.setBounds(112, 208, 562, 103);
		edtEncryption.setEditable(false);
		contentPane.add(edtEncryption);
		edtEncryption.setColumns(10);
		
		edtDecryption = new JTextField();
		edtDecryption.setBounds(112, 362, 562, 43);
		edtDecryption.setEditable(false);
		contentPane.add(edtDecryption);
		edtDecryption.setColumns(10);
		
		JLabel lbEncryption = new JLabel("��ȣ��");
		lbEncryption.setBounds(112, 172, 82, 21);
		contentPane.add(lbEncryption);
		
		JLabel lbDecryption = new JLabel("��ȣ��");
		lbDecryption.setBounds(112, 326, 82, 21);
		contentPane.add(lbDecryption);
		
		JButton btnDecode = new JButton("��ȣȭ");
		btnDecode.setBounds(443, 466, 129, 29);
		contentPane.add(btnDecode);
		
		JButton btnReturn = new JButton("�ٽ� �ϱ�");
		btnReturn.setBounds(589, 466, 129, 29);
		contentPane.add(btnReturn);
		
		JPanel panel = new JPanel();
		panel.setBounds(112, 15, 417, 240);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		txtA = new JTextField();
		txtA.setText("A");
		panel.add(txtA);
		txtA.setColumns(10);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
	}

}