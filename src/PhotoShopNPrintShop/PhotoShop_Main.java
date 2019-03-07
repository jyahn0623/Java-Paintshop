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

	// ���� ������ ���� ������ �г� ����
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();

	// �׼��� �߻��� ������ ������ �ϱ� ���� �� ����
	JLabel Explain = new JLabel("���� ������");

	// ��� ó���� On/Off�� ������ �Ҹ� ���� ����
	boolean WB = false;
	boolean SaveState = false;

	JLabel jl = new JLabel();

	// ȸ����, ����/����, ȭ�� Ȯ��/��Ҹ� ���� ���� ����
	int count = 1;
	int visible_count = 0;
	double Scale = 1.0;

	// �̹��� ��ü�� ����� �׷��� ��ü�� ����� ���� ����
	// �̹��� Ŭ������ �׷��� �̹����� ǥ���ϴ� �߻� Ŭ����
	Image image;
	BufferedImage img;
	Graphics2D g2d;

	// JFileChooser ����� ���� ����
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
		jl.setFont(new Font("����", Font.BOLD, 12));
		p2.add(jl);

		getContentPane().add(p3, BorderLayout.SOUTH);
		p3.setBackground(Color.orange);
		Explain.setFont(new Font("���� ���", Font.BOLD, 12));
		p3.add(Explain);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.setBackground(SystemColor.controlHighlight);
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);

		// GUI ����
		JMenu File = new JMenu("����");
		File.setFont(new Font("���� ���", Font.BOLD, 12));
		menuBar.add(File);

		JMenuItem Open = new JMenuItem("���� ����(O)");
		Open.setFont(new Font("���� ���", Font.BOLD, 12));
		Open.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		File.add(Open);

		JMenuItem Save = new JMenuItem("���� ����(S)");
		Save.setFont(new Font("���� ���", Font.BOLD, 12));
		Save.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		File.add(Save);

		JMenu mnNewMenu = new JMenu("�̹��� ȿ��");
		mnNewMenu.setFont(new Font("���� ���", Font.BOLD, 12));
		menuBar.add(mnNewMenu);

		JMenuItem WnB = new JMenuItem("��� ȿ��(G)");
		WnB.setFont(new Font("���� ���", Font.BOLD, 12));
		WnB.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeLeaf.gif")));
		mnNewMenu.add(WnB);

		JMenuItem Scaling_b = new JMenuItem("ũ�� ����(=)");
		Scaling_b.setFont(new Font("���� ���", Font.BOLD, 12));
		Scaling_b.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/maximize.gif")));
		mnNewMenu.add(Scaling_b);

		JMenuItem Scaling_s = new JMenuItem("ũ�� ����(-)");
		Scaling_s.setFont(new Font("���� ���", Font.BOLD, 12));
		Scaling_s.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/iconify.gif")));
		mnNewMenu.add(Scaling_s);

		JMenuItem Rotate = new JMenuItem("ȸ��(R)");
		Rotate.setFont(new Font("���� ���", Font.BOLD, 12));
		Rotate.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		mnNewMenu.add(Rotate);

		JMenuItem Reset = new JMenuItem("�ʱ�ȭ(F5)");
		Reset.setFont(new Font("���� ���", Font.BOLD, 12));
		Reset.setIcon(new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		mnNewMenu.add(Reset);

		JMenuItem Disappear = new JMenuItem("����/����( ` )");
		Disappear.setFont(new Font("���� ���", Font.BOLD, 12));
		Disappear.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/motif/icons/Error.gif")));
		mnNewMenu.add(Disappear);

		JMenu mnNewMenu_1 = new JMenu("�׸���");
		mnNewMenu_1.setFont(new Font("���� ���", Font.BOLD, 12));
		menuBar.add(mnNewMenu_1);

		JMenuItem PaintShop = new JMenuItem("�׸��� ����");
		PaintShop.setFont(new Font("���� ���", Font.BOLD, 12));
		PaintShop.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		mnNewMenu_1.add(PaintShop);

		JMenu Help = new JMenu("����");
		Help.setFont(new Font("���� ���", Font.BOLD, 12));
		menuBar.add(Help);

		JMenuItem MadeBy = new JMenuItem("\uB3C4\uC6C0\uB9D0(F1)");
		MadeBy.setFont(new Font("���� ���", Font.BOLD, 12));
		MadeBy.setIcon(
				new ImageIcon(PhotoShop_Main.class.getResource("/com/sun/java/swing/plaf/motif/icons/Question.gif")));
		Help.add(MadeBy);

		// ����Ű �ɼ��� �߰��ϱ� ���� Ű���� ������ ����
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

		// ���� ���� ��ư
		Open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub

				// ������ ���� �� ���� ���� Reset �޼ҵ� ����
				Reset();

				Explain.setText("���� ���ϴ� ������ �����ϼ���.");
				
				JFileChooser fc = new JFileChooser();
				int open = fc.showOpenDialog(null);
				if (open == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						jl.setIcon(new ImageIcon(ImageIO.read(file)));
						image = ImageIO.read(file);

						img = new BufferedImage(image.getWidth(null), image.getHeight(null),
								BufferedImage.TYPE_INT_RGB);

						Explain.setText("���� ���� �Ϸ�");

					} catch (IOException e) {
						System.out.println(e);
					}
				} else
					System.out.println("������ ���� �ʾҽ��ϴ�.");
			}
		});

		// ���� ���� ��ư
		Save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// JFileChooser�� �̿��Ͽ� �����Ѵ�.
				int save = fc.showSaveDialog(null);
				if (save == JFileChooser.APPROVE_OPTION) {

					// ���� ������ ��ο� �̸��� name�� �����Ѵ�.
					String name;
					name = fc.getSelectedFile().toString() + ".jpg";

					try {
						ImageIO.write(img, "jpg", new File(name));
						Explain.setText("���� �Ϸ�");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		// ��� ó�� ��ư
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

		// ȭ�� Ȯ�� ó�� ��ư
		Scaling_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				count = 1;

				Scale *= 1.02;

				img = Scaling(img, WB);

				// �޾ƿ� ���� �̹����� �̹��� ������ Ŭ������ ���Ͽ� �̹��� ��ü�� ����� ������ ���δ�
				jl.setIcon(new ImageIcon(img));

				Explain.setText("ȭ�� Ȯ�� (+)");

				repaint();
			}
		});

		// ȭ�� ��� ó�� ��ư
		Scaling_s.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				count = 1;

				Scale /= 1.02;

				img = Scaling(img, WB);

				jl.setIcon(new ImageIcon(img));

				Explain.setText("ȭ�� ��� (+)");

				repaint();
			}
		});

		// ȭ�� ȸ�� ó�� ��ư
		Rotate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				img = rotate(img, WB);

				jl.setIcon(new ImageIcon(img));

				repaint();
			}
		});

		// ȭ�� ���� ó�� ��ư
		Disappear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				visible_count++;

				if (visible_count % 2 == 0) {
					jl.setVisible(true);
					visible_count = 0;
					Explain.setText("ȭ�� ���̱�");
				} else {
					jl.setVisible(false);
					Explain.setText("ȭ�� ����");
				}
			}
		});

		// �ʱ�ȭ ó�� ��ư
		Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// �ʱ�ȭ �޼ҵ带 ���Ͽ� ��� ���� ���� �ʱ�ȭ �Ѵ�
				Reset();

				jl.setIcon(new ImageIcon(image));

				repaint();

				Explain.setText("�ʱ�ȭ �Ϸ�");
			}
		});

		// �׸��� ������ ȣ�� ��ư
		PaintShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// JOptionPane�� ���̾Ʒα׸� �̿��Ͽ� Ȯ������ ���� ������� ���� ó���Ѵ�
				int result = javax.swing.JOptionPane.showConfirmDialog(null, "�׸����� �����Ͻðڽ��ϱ�?",
						"PhotoShop's PaintShop & PhotoShop", javax.swing.JOptionPane.YES_NO_OPTION);

				if (result == javax.swing.JOptionPane.CLOSED_OPTION) {
					Explain.setText("�׸��� ���� ���");
				} else if (result == javax.swing.JOptionPane.YES_OPTION) {
					PaintShop ps2 = new PaintShop();
				}

				Explain.setText("�׸��� ����");
			}
		});

		// ���̵� ���� ��ư
		MadeBy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// �ش� ��ư�� Ŭ������ �� ���� ������ ȣ���Ѵ�.
				new HelpFrame();
			}

		});

	}

	// ȭ�� Ȯ��/��� �޼ҵ�
	public BufferedImage Scaling(BufferedImage img, boolean WBState) {

		// ���� �̹����� �̹��� ��ü�� �׸� �� ����. ����, �̸� ���Ͽ� �����Ѵ�.
		if (WBState == true) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_BYTE_GRAY);
		} else if (WBState == false) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_INT_RGB);

		}

		// Graphics2d�� 2d�� ����, �ؽ�Ʈ, �̹��� ǥ���� ���� �⺻ Ŭ����
		g2d = img.createGraphics();
		g2d.setTransform(AffineTransform.getScaleInstance(Scale, Scale));
		g2d.drawImage(image, 0, 0, null);

		return img;

	}

	// ȭ�� ��� ó�� �޼ҵ�
	public BufferedImage WnB(BufferedImage img, boolean WBState) {

		if (WBState == true) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_BYTE_GRAY);

			Explain.setText("��� ȿ��  On");
		} else if (WBState == false) {
			img = new BufferedImage((int) (image.getWidth(null) * Scale), (int) (image.getHeight(null) * Scale),
					BufferedImage.TYPE_INT_RGB);

			Explain.setText("��� ȿ�� Off");
		}

		g2d = img.createGraphics();
		g2d.setTransform(AffineTransform.getScaleInstance(Scale, Scale));
		g2d.drawImage(image, 0, 0, null);

		return img;
	}

	// ȭ�� ȸ�� ó�� �޼ҵ�
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

		// ȸ������ �����ϱ� ���Ͽ� if���� ����Ѵ�
		if (count < 4) {
			Explain.setText(count * 90 + "��" + " " + "ȸ��");
			count++;

		} else {
			count = 1;
			Explain.setText("���� ������");
		}

		g2d.drawImage(image, 0, 0, null);

		return img;
	}

	// �ʱ�ȭ �޼ҵ�
	public void Reset() {

		// �ʱ�ȭ �޼ҵ尡 ���� �Ǿ��� �� ��� ������ �ʱ� ������ �ٲ۴�
		WB = false;

		count = 1;
		visible_count = 0;
		Scale = 1.0;
	}
}
