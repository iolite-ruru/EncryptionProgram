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

public class M32052 extends JFrame {

	private JPanel contentPane;
	private JTextField edtPlain;
	private JTextField edtKey;
	
	public M32052() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("3205");
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JButton btnSingle = new JButton("단일치환");
		btnSingle.setBounds(413, 374, 105, 29);
		btnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.setLayout(null);
		
		edtPlain = new JTextField();
		edtPlain.setBounds(134, 149, 409, 90);
		contentPane.add(edtPlain);
		edtPlain.setColumns(10);
		
		edtKey = new JTextField();
		edtKey.setBounds(134, 293, 417, 29);
		contentPane.add(edtKey);
		edtKey.setColumns(10);
		contentPane.add(btnSingle);
		
		JButton btnMultiple = new JButton("다중치환");
		btnMultiple.setBounds(520, 374, 105, 29);
		contentPane.add(btnMultiple);
		
		JLabel lbPlain = new JLabel("평문");
		lbPlain.setBounds(134, 113, 82, 21);
		contentPane.add(lbPlain);
		
		JLabel lbKey = new JLabel("암호키");
		lbKey.setBounds(134, 269, 82, 21);
		contentPane.add(lbKey);
		
		JLabel lbTitle = new JLabel("암호/복호화 프로그램");
		lbTitle.setFont(new Font("굴림", Font.PLAIN, 30));
		lbTitle.setBounds(187, 32, 337, 55);
		contentPane.add(lbTitle);
		
		JButton btnClear = new JButton("\uCD08\uAE30\uD654");
		btnClear.setBounds(64, 374, 129, 29);
		contentPane.add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		contentPane.add(panel);
		
		btnSingle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnMultiple.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
	
	public static void setSingleFrame() {
		
	}
	
	public static void setMultipleFrame() {
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M32052 frame = new M32052();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
