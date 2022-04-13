package M3205;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class MainClass extends JFrame {

	private JPanel contentPane;
	private JTextField edtPlain;
	private JTextField edtKey;
	
	private String plain;
	private String key;
	
	public MainClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("3205");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnSingle = new JButton("단일치환");
		btnSingle.setBounds(456, 448, 105, 29);
		btnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.setLayout(null);
		
		edtPlain = new JTextField();
		edtPlain.setToolTipText("암호화 할 평문을 입력해주세요.\r\n" + "영문자 입력만을 허용합니다.");
		edtPlain.setBounds(172, 171, 409, 90);
		contentPane.add(edtPlain);
		edtPlain.setColumns(10);
		
		edtKey = new JTextField();
		edtKey.setToolTipText("암호/복호화에 사용될 Key 값을 입력해주세요.\r\n" + "영문자 입력만을 허용합니다.");
		edtKey.setBounds(172, 315, 417, 46);
		contentPane.add(edtKey);
		edtKey.setColumns(10);
		contentPane.add(btnSingle);
		
		JButton btnMultiple = new JButton("다중치환");
		btnMultiple.setBounds(569, 448, 105, 29);
		contentPane.add(btnMultiple);
		
		JLabel lbPlain = new JLabel("평문");
		lbPlain.setBounds(172, 135, 82, 21);
		contentPane.add(lbPlain);
		
		JLabel lbKey = new JLabel("암호키");
		lbKey.setBounds(172, 291, 82, 21);
		contentPane.add(lbKey);
		
		JLabel lbTitle = new JLabel("암호/복호화 프로그램");
		lbTitle.setFont(new Font("굴림", Font.PLAIN, 30));
		lbTitle.setBounds(224, 27, 337, 55);
		contentPane.add(lbTitle);
		
		JButton btnClear = new JButton("초기화");
		btnClear.setBounds(97, 448, 129, 29);
		contentPane.add(btnClear);
		
		JLabel lbType = new JLabel("암호화 방식");
		lbType.setBounds(456, 412, 129, 21);
		contentPane.add(lbType);
		
		btnSingle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				plain = edtPlain.getText();
				key = edtKey.getText();
				SingleSubstitution subFrame = new SingleSubstitution();
				subFrame.setVisible(true);
				dispose();
			}
		});
		
		btnMultiple.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				plain = edtPlain.getText();
				key = edtKey.getText();
				MultipleSubstitution subFrame = new MultipleSubstitution();
				subFrame.setVisible(true);
				dispose();
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edtPlain.setText(null);
				edtKey.setText(null);
				
			}
		});
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass frame = new MainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
