import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JPanel implements KeyListener, MouseListener {
	// 游戏状态
	protected boolean GamePause = true;
	// 地图块大小
	protected int pic_wid = 22;
	protected int pic_hgt = 22;
	protected int xTop = 25;
	protected int yTop = 15;
	// 计时器
	Timer tm;
	// 模型成员
	protected Model model = new Model(25, 25);

	/**
	 * 视图初始化
	 */
	public View() {
		super();
		// 设置计时器
		tm = new Timer(250, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 初始化模型数据
				model.step();
				// 重绘界面
				repaint();
			}
		});
//        tm.start();
	}

	/**
	 * 重绘地图
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 获取地图数据
		int[][] map = model.getMap();

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == Model.CALIVE)
					// 活细胞绘制黑色方块
					g.fillRect(j * pic_wid, yTop + i * pic_hgt, pic_wid, pic_hgt);
				else
					// 死细胞绘制白色方块
					g.drawRect(j * pic_wid, yTop + i * pic_hgt, pic_wid, pic_hgt);
			}
		g.drawString("当前迭代次数: " + model.getLiteraion(), 5, 10);
	}

	public void keyTyped(KeyEvent e) {
	}

	/**
	 * 响应键盘事件
	 *
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (GamePause)
				tm.start();
			else
				tm.stop();
			GamePause = !GamePause;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			model.startRandom();
			tm.stop();
			GamePause = true;
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			model.reset();
			GamePause = true;
			tm.stop();
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	/**
	 * 响应鼠标点击
	 */
	public void mouseClicked(MouseEvent e) {
		// JOptionPane.showMessageDialog(null, "x: " + e.getX() + "y: " + e.getY());
	}

	public void mousePressed(MouseEvent e) {
		int x, y;
//        int left_size = getInsets().left, top_size = getInsets().top;

		x = (e.getX() - 3) / pic_wid;
		y = (e.getY() - 32 - yTop) / pic_hgt;
		if (model.getCell(y, x) == Model.CDEAD)
			model.setCell(Model.CALIVE, y, x);
		else
			model.setCell(Model.CDEAD, y, x);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	/**
	 * 程序入口及初始化
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		View jp = new View();

		// jf.setUndecorated(true);
		jf.setSize(800, 800);
		jf.setTitle("生命游戏(空格键开始,回车键随机填充,R建清空)");
		jf.addKeyListener(jp);
		jf.addMouseListener(jp);
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}

}
