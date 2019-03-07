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

	// � ��ư�� ���ȴ����� �Ǵ��ϱ� ���� �Ҹ� �� ����
	private boolean LineState = false;
	private boolean CircleState = false;
	private boolean RectState = false;
	private boolean RRectState = false;
	private boolean OvalState = false;
	private boolean Eraser = false;
	private boolean PictureState = false;

	// ���� ���� ��, ��ǥ�� ���� ���� ����
	private int num = 0;
	private int x, y, x1, y1;
	private File file;
	private Image image;

	// �׸��� �׸��� ���� �г�
	private Panel p = new Panel();

	// ������ ��ɰ� ��Ȳ�� ���Ͽ� ����� �ִ� ����
	private JLabel Explain_PS = new JLabel();
	private JLabel Explain_Oval = new JLabel();

	// Save �� Open�� ���� JFileChooser
	JFileChooser fc = new JFileChooser();

	// �׸� ������ �����ϱ� ���� ���� ����
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

		// �׸��� �׸��� ���� �г� ����
		Panel p = new Panel();
		p.setBackground(Color.white);
		contentPane.add(p, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_1.add(panel_2);

		JButton btnNewButton = new JButton("��� ����");
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("��");
		btnNewButton_2.setBackground(SystemColor.info);
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("��");
		btnNewButton_3.setBackground(SystemColor.info);
		btnNewButton_3.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("�׸�");
		btnNewButton_4.setBackground(SystemColor.info);
		btnNewButton_4.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("�ձ� �׸�");
		btnNewButton_5.setBackground(SystemColor.info);
		btnNewButton_5.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("�� ä���");
		btnNewButton_6.setBackground(SystemColor.info);
		btnNewButton_6.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("�� ����");
		btnNewButton_7.setBackground(SystemColor.info);
		btnNewButton_7.setFont(new Font("����", Font.BOLD, 12));
		panel_2.add(btnNewButton_7);

		JPanel p3 = new JPanel();

		contentPane.add(p3, BorderLayout.SOUTH);

		Explain_PS.setText("���� ������");
		Explain_PS.setFont(new Font("����", Font.BOLD, 12));
		p3.add(Explain_PS);

		Explain_Oval.setText("  �� ĥ�ϱ� ��� Off");
		Explain_Oval.setFont(new Font("����", Font.BOLD, 12));
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

		// ����鿡 ���� ��ư, ���� Ŭ�� �ÿ� num ���� �����ȴ�
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

		// ������ �ҷ��� ��濡 �����ϱ� ���� ��ư
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

				Explain_PS.setText("������� ���ϴ� �̹����� �����ϰ� �г� ���� Ŭ���ϼ���");

				int open = fc.showOpenDialog(null);
				if (open == JFileChooser.APPROVE_OPTION) {
					String name;
					name = fc.getSelectedFile().toString();

					// �̹��� ��ü�� ������ �� ���� ������ �ش� ����� �̹��� ��ü�� ImageIcon�� �̿��Ͽ� �����Ѵ�.
					ImageIcon imc = new ImageIcon(name);
					image = imc.getImage();
				}
			}
		});

		// �г� ���� �׸� ���� �����ϱ� ���� ��ư
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

						Explain_PS.setText("���� �Ϸ�");

						Explain_PS.setBackground(Color.white);
						Explain_PS.setForeground(Color.BLACK);
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		// �׸����� �ϴ� Ÿ���� ����, Boolean ���� ���Ͽ� ������ �� �ִ�.
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setText("�� �׸��� ����");

				// Line Ŭ�� �� Line�� ������ �������� false�� ����
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

				Explain_PS.setText("�� �׸��� ����");

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

				Explain_PS.setText("�簢�� �׸��� ����");

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

				Explain_PS.setText("�ձ� �׸� �׸��� ����");
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
					Explain_Oval.setText("�� ĥ�ϱ� ��� On");
				} else {
					OvalState = false;
					Explain_Oval.setText("�� ĥ�ϱ� ��� Off");
				}

			}

		});

		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Explain_PS.setForeground(Color.BLACK);
				Explain_PS.setText("ȭ���� ����� ������ ȭ���� Ŭ���ϼ���");

				Eraser = true;
				LineState = false;
				CircleState = false;
				RectState = false;
				RRectState = false;
				PictureState = false;

			}
		});
	}

	// �׸��� �׸��� ���� �г�
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

					// �⺻�� Ÿ�� ���� 0���� �ϰ� �Ҹ� ���� ���� ������ �����Ѵ�.
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

					// ��ǥ, ���� num ��, �� ä���� ����, ������ Ÿ�� ���� �����ϱ� ���� Element Ŭ����
					// ȣ���Ѵ�.
					Element setting = new Element();
					setting.Set(x, y, x1, y1, type, num, OvalState);

					// ������ �Ϸ��� ������ Vector�� �����Ѵ�.
					v.add(setting);

					repaint();
				}
			});
		}

		// ���������� �׸��� �κ�
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// ���ݱ��� ����Ǿ� �ִ� ���Ͱ��� �ҷ��´�.
			for (int i = 0; i < v.size(); i++) {
				// �׸� ������ ���Ϳ� ��´�.
				Element e = v.elementAt(i);

				g.setColor(e.Color());

				if (e.Type() == 1) {
					// x, y���� x1, y1������ �� �׸���.
					g.drawLine(e.X(), e.Y(), e.X1(), e.Y1());
				} else if (e.Type() == 2) {

					// x, y���� x1-x, y1-y�� ũ���� �� �׸���.
					if (e.Oval() == false) {
						g.drawOval(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
					} else
						g.fillOval(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
				} else if (e.Type() == 3) {

					// x, y���� x1-x, y1-y�� ũ���� �簢�� �׸���.
					if (e.Oval() == false) {
						g.drawRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());
					} else
						g.fillRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y());

				} else if (e.Type() == 4) {

					// x, y���� x1-x, y1-y�� ����, ���� �������� ���� ���� �׸� �׸���.
					if (e.Oval() == false) {
						g.drawRoundRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y(), 25, 25);
					} else
						g.fillRoundRect(e.X(), e.Y(), e.X1() - e.X(), e.Y1() - e.Y(), 25, 25);

				} else if (e.Type() == 5) {
					// Clear ��ư�� Ŭ���ϸ� ������ �Ͼ������ �����ϰ�, 0, 0�� ��ǥ���� �г��� �ʺ�� ���̸�ŭ
					// �簢������ ä���.
					g.setColor(Color.white);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());

				} else if (e.Type() == 6) {
					// ��� ���� ��ư�� Ŭ���ϸ� drawImage �޼ҵ带 �̿��Ͽ� �г� ����� image�� �׸���.
					g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

				}

			}
		}

	}

	// �׸��� Ŭ������ ȣ�� �Ǿ��� �� �׸����� ȣ�� ��ų �� �ֵ��� �ϴ� ���� �޼ҵ�
	public static void main(String[] args) {
		new PaintShop();
	}
}

// ������ ���� Ŭ����.
class Element {

	// ��ǥ ����, Ÿ�� ��, ����, ä�� ���θ� ���� ���� ����
	private int x, y, x1, y1, type;
	private Color color;
	private boolean Oval;

	// Set �޼ҵ��� �Ű������� ���� ������ �޾ƿ� �ش� Ŭ������ �����Ѵ�.
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

	// �Ʒ� �޼ҵ���� ���Ͽ� ����Ǿ� �ִ� ������ ��ȯ�Ѵ�.
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
