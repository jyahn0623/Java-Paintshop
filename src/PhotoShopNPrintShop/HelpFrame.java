package PhotoShopNPrintShop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import java.awt.Font;

public class HelpFrame extends JFrame {

	private JPanel contentPane;
	
	//JList�� ��ϵ� ����
	private String str[]=new String[4];
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new HelpFrame();
	}

	/**
	 * Create the frame.
	 */
	public HelpFrame() {
		setResizable(false);
		setBounds(100, 100, 545, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setTitle("����");
		
		List list = new List();
		list.setFont(new Font("Dialog", Font.BOLD, 13));
		list.setBounds(10, 10, 124, 363);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("�� �� ��");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(295, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("����", Font.BOLD, 13));
		textPane.setEditable(false);
		textPane.setBounds(143, 34, 374, 339);
		contentPane.add(textPane);
		
		str[0]="���伥 ����";
		str[1]="�׸��� ����";
		str[2]="��Ÿ";
		
		for(int i=0; i<4; i++){
			list.add(str[i]);
		}
		
		// list�� ������ ���� �׼� �̺�Ʈ
		list.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//str[0](���� ���伥) Ŭ��
				if(e.getActionCommand()==str[0]){
					textPane.setText(
							"���伥�� �� ���� ���� ����� �ִ�. \n"
							+ "\n"
							+ "������ ���� :�̹��� ȿ���� �ֱ� ���� �̹����� �ҷ��´�. \n"
							+ "������ ���� :�̹��� ȿ���� �ְ� ���� �̹����� �����Ѵ�. \n"
							+ "����� ȿ�� :�ҷ��� �̹����� ��� ȿ���� �ο��Ѵ�. \n"
							+ "��ũ�� ���� :�ҷ��� �̹����� ũ�⸦ Ű���.\n"
							+ "��ũ�� ���� :�ҷ��� �̹����� ũ�⸦ ���δ�.\n"
							+ "��ȸ�� ��    :�ҷ��� �̹����� ũ�⸦ ȸ���Ѵ�.\n"
							+ "����  ��  ȭ :�ʱ�ȭ�� �����Ѵ�.\n"
							+ "������/���� :�۾� ���� ȭ���� ����ų� ���̰� �Ѵ�."
							);
				//str[1](���� ���伥) Ŭ��	
				}else if(e.getActionCommand()==str[1]){
					textPane.setText(
							"�׸����� �� ���� ���� ����� �ִ�. \n"
							+ "\n"
							+ "����� ���� :�̹����� �ҷ��� �гο� ������ �� �ְ� ���ش�. \n"
							+ "������ ��    :�׸� �۾��� �����ϰ� �� �ڿ� �����Ѵ�. \n"
							+ "����   ��     :�� ������ �����Ѵ�. \n"
							+ "����   ��     :�� ������ �����Ѵ�.\n"
							+ "����    ��    :�׸� ������ �����Ѵ�.\n"
							+ "���ձ� �׸� :�ձ� �׸� �����Ѵ�.\n"
							+ "���� ä��� :��, �׸�, �ձ� �׸� ���� ��ĥ�Ѵ�. \n"
							+ "���� ����    :ȭ�� ��ü�� �����."
							);
				//str[2](���� ���伥) Ŭ��	
				}else if(e.getActionCommand()==str[2]){
					textPane.setText(
							"�����α׷� ����:�̹����� ó���� �̹��� ���� �׸��� �׸� �� �ְ� �ϰ� "
							+ "ó���� �׸��� ���� �ٽ� �̹��� ó���� �����ϰ� �Ѵ�."
							+ "\n \n"
							+ "������ ���:�� �� ��"
							
							);
				}
			}
			
		});
	}
}
