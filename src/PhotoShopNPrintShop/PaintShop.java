package PhotoShopNPrintShop;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

class PaintShop extends JFrame {

	private JPanel contentPane;

	// 어떤 버튼이 눌렸는지를 판단하기 위한 불린 값 설정
	private boolean LineState = false;
	private boolean CircleState = false;
	private boolean RectState = false;
	private boolean RRectState = false;
	private boolean OvalState = false;
	private boolean Eraser = false;
	private boolean PictureState = false;

	// 색상 정보 값, 좌표를 위한 변수 설정
	private int num = 0;
	private int x, y, x1, y1;
	private File file;
	private Image image;

	// 그림을 그리기 위한 패널
	private Panel p = new Panel();

	// 선택한 기능과 상황에 대하여 출력해 주는 레벨
	private JLabel Explain_PS = new JLabel();
	private JLabel Explain_Oval = new JLabel();

	// Save 및 Open을 위한 JFileChooser
	JFileChooser fc = new JFileChooser();

	// 그림 정보를 저장하기 위한 벡터 설정
	private Vector<Element> v = new Vector<Element>();

	/**
	 * Create the frame.
	 */
	public PaintShop() {

		this.setVisible(true);
		this.setSize(800, 500);
		this.setLocation(1100, 300);
		this.setTitle("Juyoung's PaintShop");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// 그림을 그리기 위한 패널 설정
		Panel p = new Panel();
		p.setBackground(Color.white);
		contentPane.add(p, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_1.add(panel_2);

		JButton btnNewButton = new JButton("배경 설정");
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("저장");
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("선");
		btnNewButton_2.setBackground(SystemColor.info);
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("원");
		btnNewButton_3.setBackground(SystemColor.info);
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("네모");
		btnNewButton_4.setBackground(SystemColor.info);
		btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("둥근 네모");
		btnNewButton_5.setBackground(SystemColor.info);
		btnNewButton_5.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("색 채우기");
		btnNewButton_6.setBackground(SystemColor.info);
		btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("새 종이");
		btnNewButton_7.setBackground(SystemColor.info);
		btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 12));
		panel_2.add(btnNewButton_7);

		JPanel p3 = new JPanel();

		contentPane.add(p3, BorderLayout.SOUTH);

		Explain_PS.setText("상태 설명줄");
		Explain_PS.setFont(new Font("굴림", Font.BOLD, 12));
		p3.add(Explain_PS);

		Explain_Oval.setText("  색 칠하기 기능 Off");
		Explain_Oval.setFont(new Font("굴림", Font.BOLD, 12));
		panel_1.add(Explain_Oval);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(Color.LIGHT_GRAY);
		panel_1.add(toolBar_1);

		JButton Black = new JButton("    ");
		Black.setBackground(Color.BLACK);
		toolBar_1.add(Black);

		JButton White = new JButton("    ");
		White.setForeground(Color.WHITE);
		toolBar_1.add(White);

		JButton Yellow = new JButton("    ");
		Yellow.setBackground(Color.YELLOW);
		toolBar_1.add(Yellow);

		JButton Orange = new JButton("    ");
		Orange.setBackground(Color.ORANGE);
		toolBar_1.add(Orange);

		JButton Pink = new JButton("    ");
		Pink.setBackground(Color.pink);
		toolBar_1.add(Pink);

		JButton Purple = new JButton("    ");
		Purple.setBackground(Color.CYAN);
		toolBar_1.add(Purple);

		JButton Red = new JButton("    ");
		Red.setBackground(Color.RED);
		toolBar_1.add(Red);

		JButton Green = new JButton("    ");
		Green.setBackground(Color.GREEN);
		toolBar_1.add(Green);

		JButton Blue = new JButton("    ");
		Blue.setBackground(Color.BLUE);
		toolBar_1.add(Blue);

		JButton Blue_2 = new JButton("    ");
		Blue_2.setBackground(new Color(135, 206, 250));
		toolBar_1.add(Blue_2);

		JButton Bora = new JButton("    ");
		Bora.setBackground(new Color(138, 43, 226));
		toolBar_1.add(Bora);

		JButton Brown = new JButton("    ");
		Brown.setBackground(new Color(139, 69, 19));
		toolBar_1.add(Brown);

		// 색상들에 대한 버튼, 색상 클릭 시에 num 값이 설정된다
		Red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 1;
				p3.setBackground(Color.red);
				Explain_PS.setForeground(Color.BLACK);

			}
		});

		Blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				num = 2;
				p3.setBackground(Color.blue);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Pink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				num = 3;
				p3.setBackground(Color.pink);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				num = 4;
				p3.setBackground(Color.black);
				Explain_PS.setForeground(Color.white);
			}
		});

		Green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				num = 5;
				p3.setBackground(Color.green);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Yellow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 6;
				p3.setBackground(Color.YELLOW);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Purple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 7;
				p3.setBackground(Color.cyan);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Orange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 8;
				p3.setBackground(Color.ORANGE);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		White.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 9;
				p3.setBackground(Color.WHITE);
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Blue_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 10;
				p3.setBackground(new Color(135, 206, 250));
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Bora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 11;
				p3.setBackground(new Color(138, 43, 226));
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		Brown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				num = 12;
				p3.setBackground(new Color(139, 69, 19));
				Explain_PS.setForeground(Color.BLACK);
			}
		});

		// 사진을 불러와 배경에 설정하기 위한 버튼
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				PictureState = true;
				LineState = false;
				CircleState = false;
				RectState = false;
				RRectState = false;
				Eraser = false;

				Explain_PS.setText("배경으로 원하는 이미지를 선택하고 패널 위를 클릭하세요");

				int open = fc.showOpenDialog(null);
				if (open == JFileChooser.APPROVE_OPTION) {
					String name;
					name = fc.getSelectedFile().toString();

					// 이미지 자체를 설정할 수 없기 때문에 해당 경로의 이미지 객체를 ImageIcon을 이용하여 생성한다.
					ImageIcon imc = new ImageIcon(name);
					image = imc.getImage();
				}
			}
		});

		// 패널 위의 그린 값을 저장하기 위한 버튼
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				BufferedImage img = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = img.createGraphics();

				p.paint(g2d);

				int save = fc.showSaveDialog(null);
				if (save == JFileChooser.APPROVE_OPTION) {

					String name;
					name = fc.getSelectedFile().toString() + ".jpg";

					try {
						ImageIO.write(img, "jpg", new File(name));

						Explain_PS.setText("저장 완료");

						Explain_PS.setBackground(Color.white);
						Explain_PS.setForeground(Color.BLACK);
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		// 그리려고 하는 타입을 결정, Boolean 값을 통하여 설정할 수 있다.
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setText("선 그리기 선택");

				// Line 클릭 시 Line을 제외한 나머지는 false로 설정
				LineState = true;
				CircleState = false;
				RectState = false;
				RRectState = false;
				Eraser = false;
				PictureState = false;

			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setText("원 그리기 선택");

				LineState = false;
				CircleState = true;
				RectState = false;
				RRectState = false;
				Eraser = false;
				PictureState = false;
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setText("사각형 그리기 선택");

				LineState = false;
				CircleState = false;
				RectState = true;
				RRectState = false;
				Eraser = false;
				PictureState = false;
			}
		});

		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setText("둥근 네모 그리기 선택");
				Explain_PS.setForeground(Color.black);

				LineState = false;
				CircleState = false;
				RectState = false;
				RRectState = true;
				Eraser = false;
				PictureState = false;
			}

		});

		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (OvalState == false) {
					OvalState = true;
					Explain_Oval.setText("색 칠하기 기능 On");
				} else {
					OvalState = false;
					Explain_Oval.setText("색 칠하기 기능 Off");
				}

			}

		});

		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setForeground(Color.BLACK);
				Explain_PS.setText("화면을 지우고 싶으면 화면을 클릭하세요");

				Eraser = true;
				LineState = false;
				CircleState = false;
				RectState = false;
				RRectState = false;
				PictureState = false;

			}
		});
	}

	// 그림을 그리기 위한 패널
	class Panel extends JPanel {

		public Panel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					x = e.getX();
					y = e.getY();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					x1 = e.getX();
					y1 = e.getY();

					// 기본의 타입 값을 0으로 하고 불린 값에 따라 유형을 변경한다.
					int type = 0;

					if (LineState == true) {
						type = 1;
					} else if (CircleState == true) {
						type = 2;
					} else if (RectState == true) {
						type = 3;
					} else if (RRectState == true) {
						type = 4;
					} else if (Eraser == true) {
						type = 5;
					} else if (PictureState == true) {
						type = 6;
					}

					// 좌표, 색상 num 값, 색 채움의 여부, 선택한 타입 값을 저장하기 위한 Element 클래스
					// 호출한다.
					Element setting = new Element();
					setting.Set(x, y, x1, y1, type, num, OvalState);

					// 설정을 완료한 값들을 Vector에 저장한다.
					v.add(setting);

					repaint();
				}
			});
		}

		// 실질적으로 그리는 부분
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// 지금까지 저장되어 있는 벡터값을 불러온다.
			for (int i = 0; i < v.size(); i++) {
				// 그림 정보를 벡터에 담는다.
				Element e = v.elementAt(i);

				g.setColor(e.Color());

				if (e.Type() == 1) {
					// x, y부터 x1, y1까지의 선 그린다.
					g.drawLine(e.X(), e.Y(), e.X1(), e.Y1());
				} else if (e.Type() == 2) {

					// x, y부터 x1-x, y1-y의 크기의 원 그린다.
					if (e.Oval() == false) {
						g.drawOval(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
					} else
						g.fillOval(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
				} else if (e.Type() == 3) {

					// x, y부터 x1-x, y1-y의 크기의 사각형 그린다.
					if (e.Oval() == false) {
						g.drawRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
					} else
						g.fillRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());

				} else if (e.Type() == 4) {

					// x, y부터 x1-x, y1-y의 수평, 수직 반지름을 가진 원형 네모 그린다.
					if (e.Oval() == false) {
						g.drawRoundRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y(), 25, 25);
					} else
						g.fillRoundRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y(), 25, 25);

				} else if (e.Type() == 5) {
					// Clear 버튼을 클릭하면 색상을 하얀색으로 설정하고, 0, 0의 좌표부터 패널의 너비와 높이만큼
					// 사각형으로 채운다.
					g.setColor(Color.white);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());

				} else if (e.Type() == 6) {
					// 배경 설정 버튼을 클릭하면 drawImage 메소드를 이용하여 패널 배경을 image로 그린다.
					g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

				}

			}
		}

	}

	// 그림판 클래스가 호출 되었을 때 그림판을 호출 시킬 수 있도록 하는 메인 메소드
	public static void main(String[] args) {
		new PaintShop();
	}
}

// 저장을 위한 클래스.
class Element {

	// 좌표 저장, 타입 값, 색상, 채움 여부를 위한 변수 설정
	private int x, y, x1, y1, type;
	private Color color;
	private boolean Oval;

	// Set 메소드의 매개변수로 각각 값들을 받아와 해당 클래스에 저장한다.
	void Set(int x, int y, int x1, int y1, int type, int num, boolean Oval) {
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.type = type;
		this.Oval = Oval;

		switch (num) {

		case 1:
			this.color = color.red;
			break;
		case 2:
			this.color = color.blue;
			break;
		case 3:
			this.color = color.pink;
			break;
		case 4:
			this.color = color.BLACK;
			break;
		case 5:
			this.color = color.GREEN;
			break;
		case 6:
			this.color = color.YELLOW;
			break;
		case 7:
			this.color = color.cyan;
			break;
		case 8:
			this.color = color.ORANGE;
			break;
		case 9:
			this.color = color.WHITE;
			break;
		case 10:
			this.color = new Color(135, 206, 250);
			break;
		case 11:
			this.color = new Color(138, 43, 226);
			break;
		case 12:
			this.color = new Color(139, 69, 19);
			break;
		default:
			this.color = color.BLACK;
		}

	}

	// 아래 메소드들을 통하여 저장되어 있는 값들을 반환한다.
	public int X() {
		return x;
	}

	public int Y() {
		return y;
	}

	public int X1() {
		return x1;
	}

	public int Y1() {
		return y1;
	}

	public Color Color() {
		return color;
	}

	public int Type() {
		return type;
	}

	public boolean Oval() {
		return Oval;
	}
}
