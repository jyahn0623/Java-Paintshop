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
	
	//JList의 목록들 설정
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
		this.setTitle("도움말");
		
		List list = new List();
		list.setFont(new Font("Dialog", Font.BOLD, 13));
		list.setBounds(10, 10, 124, 363);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("도 움 말");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(295, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("굴림", Font.BOLD, 13));
		textPane.setEditable(false);
		textPane.setBounds(143, 34, 374, 339);
		contentPane.add(textPane);
		
		str[0]="포토샵 도움말";
		str[1]="그림판 도움말";
		str[2]="기타";
		
		for(int i=0; i<4; i++){
			list.add(str[i]);
		}
		
		// list를 눌렀을 때의 액션 이벤트
		list.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//str[0](도움말 포토샵) 클릭
				if(e.getActionCommand()==str[0]){
					textPane.setText(
							"포토샵은 총 여덟 가지 기능이 있다. \n"
							+ "\n"
							+ "·파일 열기 :이미지 효과를 주기 위한 이미지를 불러온다. \n"
							+ "·파일 저장 :이미지 효과를 주고 나서 이미지를 저장한다. \n"
							+ "·흑백 효과 :불러온 이미지에 흑백 효과를 부여한다. \n"
							+ "·크기 증가 :불러온 이미지의 크기를 키운다.\n"
							+ "·크기 감소 :불러온 이미지의 크기를 줄인다.\n"
							+ "·회　 전    :불러온 이미지의 크기를 회전한다.\n"
							+ "·초  기  화 :초기화를 수행한다.\n"
							+ "·숨김/보임 :작업 중인 화면을 숨기거나 보이게 한다."
							);
				//str[1](도움말 포토샵) 클릭	
				}else if(e.getActionCommand()==str[1]){
					textPane.setText(
							"그림판은 총 여덟 가지 기능이 있다. \n"
							+ "\n"
							+ "·배경 설정 :이미지를 불러와 패널에 설정할 수 있게 해준다. \n"
							+ "·저　 장    :그림 작업을 수행하고 난 뒤에 저장한다. \n"
							+ "·선   　     :선 도구를 선택한다. \n"
							+ "·원   　     :원 도구를 선택한다.\n"
							+ "·네    모    :네모 도구를 선택한다.\n"
							+ "·둥근 네모 :둥근 네모를 선택한다.\n"
							+ "·색 채우기 :원, 네모, 둥근 네모에 색을 덧칠한다. \n"
							+ "·새 종이    :화면 전체를 지운다."
							);
				//str[2](도움말 포토샵) 클릭	
				}else if(e.getActionCommand()==str[2]){
					textPane.setText(
							"·프로그램 목적:이미지의 처리와 이미지 위에 그림을 그릴 수 있게 하고 "
							+ "처리한 그림을 또한 다시 이미지 처리를 가능하게 한다."
							+ "\n \n"
							+ "·만든 사람:안 주 영"
							
							);
				}
			}
			
		});
	}
}
