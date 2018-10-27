public class Model {
	public static final int CALIVE = 1;
	public static final int CDEAD = 0;

	private int m_mapWid;
	private int m_mapHgt;
	private int[][] m_anMap;
	private int literation = 0;

	/**
	 * 构造方法
	 *
	 * @param width
	 * @param height
	 */
	public Model(int width, int height) {
		// 设置边界
		m_mapWid = width + 2;
		m_mapHgt = height + 2;
		// 初始化地图数据
		this.m_anMap = new int[m_mapHgt][m_mapWid];
		reset();
	}

	/**
	 * 获取地图数据
	 */
	public final int[][] getMap() {
		return this.m_anMap;
	}

	/**
	 * 获得迭代次数
	 */
	public int getLiteraion() {
		return this.literation;
	}

	/**
	 * 设置地图一点的细胞
	 */
	public boolean setCell(int status, int row, int col) {
		if (row <= 0 || row >= m_mapHgt - 1 || col <= 0 || col >= m_mapWid - 1)
			return false;
		this.m_anMap[row][col] = status;
		return true;
	}

	/**
	 * 获得一个细胞
	 */
	public int getCell(int row, int col) {
		if (row < 0 || row >= m_mapHgt || col < 0 || col >= m_mapWid)
			return -1;

		return this.m_anMap[row][col];
	}

	/**
	 * 随机初始化地图数据
	 */
	public void startRandom() {
		literation = 0;
		for (int i = 0; i < m_anMap.length; i++) {
			for (int j = 0; j < m_anMap[i].length; j++) {
				if (j != 0 && j != m_mapWid - 1 && i != 0 && i != m_mapHgt - 1) {
					if ((int) (Math.random() * 2) == 1)
						m_anMap[i][j] = CALIVE;
					else
						m_anMap[i][j] = CDEAD;
				}
			}
		}
//        m_anMap[10][10] = CALIVE;
//        m_anMap[10][9] = CALIVE;
//        m_anMap[10][11] = CALIVE;
//        m_anMap[11][10] = CALIVE;
//        m_anMap[11][11] = CALIVE;
	}

	/**
	 * 更新地图数据
	 */
	public void step() {
		int nCount;
		int[][] back_map = new int[m_mapHgt][];

		// 获得地图拷贝
		for (int i = 0; i < back_map.length; i++)
			back_map[i] = m_anMap[i].clone();

		for (int i = 1; i < m_anMap.length - 1; i++) {
			for (int j = 1; j < m_anMap[i].length - 1; j++) {
				// 活细胞邻居计数
				nCount = count(i, j);
				if (m_anMap[i][j] == CALIVE) {
					if (nCount < 2)
						back_map[i][j] = CDEAD;
					else if (nCount < 4)
						back_map[i][j] = CALIVE;
					else
						back_map[i][j] = CDEAD;
				} else {
					if (nCount == 3)
						back_map[i][j] = CALIVE;
				}
			}
		}
		// 更新地图
		for (int i = 0; i < m_anMap.length; i++)
			m_anMap[i] = back_map[i].clone();
		// 迭代次数加1
		literation++;
	}

	/**
	 * 计算一个细胞周围的活细胞数
	 */
	protected int count(int row, int col) {
		int[][] map = this.m_anMap;
		int nCount = 0;

		if (map[row - 1][col - 1] == CALIVE)
			nCount++;
		if (map[row - 1][col] == CALIVE)
			nCount++;
		if (map[row - 1][col + 1] == CALIVE)
			nCount++;
		if (map[row][col - 1] == CALIVE)
			nCount++;
		if (map[row][col + 1] == CALIVE)
			nCount++;
		if (map[row + 1][col - 1] == CALIVE)
			nCount++;
		if (map[row + 1][col] == CALIVE)
			nCount++;
		if (map[row + 1][col + 1] == CALIVE)
			nCount++;

		return nCount;
	}

	/**
	 * 重置地图
	 */
	public void reset() {
		literation = 0;
		for (int i = 0; i < m_anMap.length; i++)
			for (int j = 0; j < m_anMap[i].length; j++)
				m_anMap[i][j] = CDEAD;
	}

}