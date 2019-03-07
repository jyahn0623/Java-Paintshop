package PhotoShopNPrintShop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.SystemColor;

public class PhotoShop_Main extends JFrame {

	// 메인 프레임 위에 설정할 패널 생성
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();

	// 액션이 발생할 때마다 설명을 하기 위한 라벨 설정
	JLabel Explain = new JLabel("상태 설명줄");

	// 흑백 처리의 On/Off를 도와줄 불린 변수 설정
	boolean WB = false;
	boolean SaveState = false;

	JLabel jl = new JLabel();

	// 회전수, 숨김/보임, 화면 확대/축소를 위한 변수 설정
	int count = 1;
	int visible_count = 0;
	double Scale = 1.0;

	// 이미지 객체를 만들고 그래픽 객체를 만들기 위한 설정
	// 이미지 클래스는 그래픽 이미지를 표현하는 추상 클래스
	Image image;
	BufferedImage img;
	Graphics2D g2d;

	// JFileChooser 사용을 위한 설정
	JFileChooser fc = new JFileChooser();
	File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhotoShop_Main frame = new PhotoShop_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhotoShop_Main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("Juyoung's PhotoShop");
		this.setLocation(600, 300);

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(p2, BorderLayout.CENTER);
		p2.setBackground(Color.WHITE);
		jl.setFont(new Font("굴림", Font.BOLD, 12));
		p2.add(jl);

		getContentPane().add(p3, BorderLayout.SOUTH);
		p3.setBackground(Color.orange);
		Explain.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		p3.add(Explain);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		menuBar.setBackground(SystemColor.controlHighlight);
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);

		// GUI 설정
		JMenu File = new JMenu("파일");
		File.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		menuBar.add(File);

		JMenuItem Open = new JMenuItem("파일 열기(O)");
		Open.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Open.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		File.add(Open);

		JMenuItem Save = new JMenuItem("파일 저장(S)");
		Save.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Save.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		File.add(Save);

		JMenu mnNewMenu = new JMenu("이미지 효과");
		mnNewMenu.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		menuBar.add(mnNewMenu);

		JMenuItem WnB = new JMenuItem("흑백 효과(G)");
		WnB.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		WnB.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeLeaf.gif")));
		mnNewMenu.add(WnB);

		JMenuItem Scaling_b = new JMenuItem("크기 증가(=)");
		Scaling_b.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Scaling_b.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/maximize.gif")));
		mnNewMenu.add(Scaling_b);

		JMenuItem Scaling_s = new JMenuItem("크기 감소(-)");
		Scaling_s.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Scaling_s.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/iconify.gif")));
		mnNewMenu.add(Scaling_s);

		JMenuItem Rotate = new JMenuItem("회전(R)");
		Rotate.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Rotate.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		mnNewMenu.add(Rotate);

		JMenuItem Reset = new JMenuItem("초기화(F5)");
		Reset.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Reset.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		mnNewMenu.add(Reset);

		JMenuItem Disappear = new JMenuItem("숨김/보임( ` )");
		Disappear.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Disappear.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/motif/icons/Error.gif")));
		mnNewMenu.add(Disappear);

		JMenu mnNewMenu_1 = new JMenu("그림판");
		mnNewMenu_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		menuBar.add(mnNewMenu_1);

		JMenuItem PaintShop = new JMenuItem("그림판 실행");
		PaintShop.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		PaintShop.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		mnNewMenu_1.add(PaintShop);

		JMenu Help = new JMenu("도움말");
		Help.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		menuBar.add(Help);

		JMenuItem MadeBy = new JMenuItem("\uB3C4\uC6C0\uB9D0(F1)");
		MadeBy.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		MadeBy.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/motif/icons/Question.gif")));
		Help.add(MadeBy);

		// 단축키 옵션을 추가하기 위한 키보드 리스너 설정
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
					Scaling_b.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
					Scaling_s.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_G) {
					WnB.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_F5) {
					Reset.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					Save.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_O) {
					Open.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_R) {
					Rotate.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_BACK_QUOTE) {
					Disappear.doClick();

				} else if (e.getKeyCode() == KeyEvent.VK_F1) {
					MadeBy.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// 사진 열기 버튼
		Open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub

				// 사진을 새로 열 때를 위한 Reset 메소드 실행
				Reset();

				Explain.setText("열기 원하는 파일을 선택하세요.");
				
				JFileChooser fc = new JFileChooser();
				int open = fc.showOpenDialog(null);
				if (open == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						jl.setIcon(new ImageIcon(ImageIO.read(file)));
						image = ImageIO.read(file);

						img = new BufferedImage(image.getWidth(null), image.getHeight(null),
								BufferedImage.TYPE_INT_RGB);

						Explain.setText("파일 열기 완료");

					} catch (IOException e) {
						System.out.println(e);
					}
				} else
					System.out.println("선택이 되지 않았습니다.");
			}
		});

		// 사진 저장 버튼
		Save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// JFileChooser를 이용하여 저장한다.
				int save = fc.showSaveDialog(null);
				if (save == JFileChooser.APPROVE_OPTION) {

					// 저장 파일의 경로와 이름을 name에 저장한다.
					String name;
					name = fc.getSelectedFile().toString() + ".jpg";

					try {
						ImageIO.write(img, "jpg", new File(name));
						Explain.setText("저장 완료");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		// 흑백 처리 버튼
		WnB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				count = 1;

				if (WB == false) {
					WB = true;
				} else
					WB = false;

				img = WnB(img, WB);

				jl.setIcon(new ImageIcon(img));

				repaint();
			}
		});

		// 화면 확대 처리 버튼
		Scaling_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				count = 1;

				Scale *= 1.02;

				img = Scaling(img, WB);

				// 받아온 버퍼 이미지를 이미지 아이콘 클래스를 통하여 이미지 객체로 만들고 레벨에 붙인다
				jl.setIcon(new ImageIcon(img));

				Explain.setText("화면 확대 (+)");

				repaint();
			}
		});

		// 화면 축소 처리 버튼
		Scaling_s.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				count = 1;

				Scale /= 1.02;

				img = Scaling(img, WB);

				jl.setIcon(new ImageIcon(img));

				Explain.setText("화면 축소 (+)");

				repaint();
			}
		});

		// 화면 회전 처리 버튼
		Rotate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				img = rotate(img, WB);

				jl.setIcon(new ImageIcon(img));

				repaint();
			}
		});

		// 화면 숨김 처리 버튼
		Disappear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				visible_count++;

				if (visible_count % 2 == 0) {
					jl.setVisible(true);
					visible_count = 0;
					Explain.setText("화면 보이기");
				} else {
					jl.setVisible(false);
					Explain.setText("화면 숨김");
				}
			}
		});

		// 초기화 처리 버튼
		Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// 초기화 메소드를 통하여 모든 정수 값을 초기화 한다
				Reset();

				jl.setIcon(new ImageIcon(image));

				repaint();

				Explain.setText("초기화 완료");
			}
		});

		// 그림판 프레임 호출 버튼
		PaintShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// JOptionPane의 다이아로그를 이용하여 확인했을 때와 취소했을 때를 처리한다
				int result = javax.swing.JOptionPane.showConfirmDialog(null, "그림판을 실행하시겠습니까?",
						"PhotoShop's PaintShop & PhotoShop", javax.swing.JOptionPane.YES_NO_OPTION);

				if (result == javax.swing.JOptionPane.CLOSED_OPTION) {
					Explain.setText("그림판 실행 취소");
				} else if (result == javax.swing.JOptionPane.YES_OPTION) {
					PaintShop ps2 = new PaintShop();
				}

				Explain.setText("그림판 실행");
			}
		});

		// 메이드 바이 버튼
		MadeBy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// 해당 버튼을 클릭했을 때 도움말 프레임 호출한다.
				new HelpFrame();
			}

		});

	}

	// 화면 확대/축소 메소드
	public BufferedImage Scaling(BufferedImage img, boolean WBState) {

		// 버퍼 이미지는 이미지 자체에 그릴 수 없다. 따라서, 이를 위하여 설정한다.
		if (WBState == true) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_BYTE_GRAY);
		} else if (WBState == false) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_INT_RGB);

		}

		// Graphics2d는 2d의 도형, 텍스트, 이미지 표현을 위한 기본 클래스
		g2d = img.createGraphics();
		g2d.setTransform(AffineTransform.getScaleInstance(Scale, Scale));
		g2d.drawImage(image, 0, 0, null);

		return img;

	}

	// 화면 흑백 처리 메소드
	public BufferedImage WnB(BufferedImage img, boolean WBState) {

		if (WBState == true) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_BYTE_GRAY);

			Explain.setText("흑백 효과  On");
		} else if (WBState == false) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_INT_RGB);

			Explain.setText("흑백 효과 Off");
		}

		g2d = img.createGraphics();
		g2d.setTransform(AffineTransform.getScaleInstance(Scale, Scale));
		g2d.drawImage(image, 0, 0, null);

		return img;
	}

	// 화면 회전 처리 메소드
	public BufferedImage rotate(BufferedImage img, boolean WBState) {

		if (WBState == true) {
			img = new BufferedImage((int) (image.getWidth(null)), (int) (image.getHeight(null)),
					BufferedImage.TYPE_BYTE_GRAY);

		} else if (WBState == false) {
			img = new BufferedImage((int) (image.getWidth(null)), (int) (image.getHeight(null)),
					BufferedImage.TYPE_INT_RGB);

		}

		g2d = img.createGraphics();

		for (int i = 0; i < count; i++) {
			g2d.rotate(Math.toRadians(90), img.getWidth() / 2, img.getHeight() / 2);
		}

		// 회전수를 관리하기 위하여 if문을 사용한다
		if (count < 4) {
			Explain.setText(count * 90 + "도" + " " + "회전");
			count++;

		} else {
			count = 1;
			Explain.setText("상태 설명줄");
		}

		g2d.drawImage(image, 0, 0, null);

		return img;
	}

	// 초기화 메소드
	public void Reset() {

		// 초기화 메소드가 실행 되었을 때 모든 값들을 초기 값으로 바꾼다
		WB = false;

		count = 1;
		visible_count = 0;
		Scale = 1.0;
	}
}
