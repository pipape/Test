package game;

/**
 * 模型类.
 * @author thick
 *
 */
public class Model {
    /**
     * 活细胞标记常量.
     */
    public static final int CALIVE = 1;

    /**
     * 死细胞标记常量.
     */
    public static final int CDEAD = 0;

    /**
     * 地图宽度.
     */
    private int mapWid;


    private int mapHgt;

    private int[][] anMap;

    private int literation = 0;

	/**
	 * 构造方法
	 *
	 * @param width
	 * @param height
	 */
	public Model(int width, int height) {
		// 设置边界
		mapWid = width + 2;
		mapHgt = height + 2;
		// 初始化地图数据
		this.anMap = new int[mapHgt][mapWid];
		reset();
	}

	/**
	 * 获取地图数据
	 */
	public final int[][] getMap() {
		return this.anMap;
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
		if (row <= 0 || row >= mapHgt - 1 || col <= 0 || col >= mapWid - 1)
			return false;
		this.anMap[row][col] = status;
		return true;
	}

	/**
	 * 获得一个细胞.
	 * @param row
	 * @param col
	 * @return integer[][]
	 */
	public int getCell(final int row, int col) {
		if (row < 0 || row >= mapHgt || col < 0 || col >= mapWid) {
		    return -1;
		}

		return this.anMap[row][col];
	}

	/**
	 * 随机初始化地图数据
	 */
	public void startRandom() {
		literation = 0;
		for (int i = 0; i < anMap.length; i++) {
			for (int j = 0; j < anMap[i].length; j++) {
				if (j != 0 && j != mapWid - 1 && i != 0 && i != mapHgt - 1) {
					if ((int) (Math.random() * 2) == 1)
						anMap[i][j] = CALIVE;
					else
						anMap[i][j] = CDEAD;
				}
			}
		}
//        anMap[10][10] = CALIVE;
//        anMap[10][9] = CALIVE;
//        anMap[10][11] = CALIVE;
//        anMap[11][10] = CALIVE;
//        anMap[11][11] = CALIVE;
	}

	/**
	 * 更新地图数据
	 */
	public void step() {
		int nCount;
		int[][] backMap = new int[mapHgt][];

		// 获得地图拷贝
		for (int i = 0; i < backMap.length; i++)
			backMap[i] = anMap[i].clone();

		for (int i = 1; i < anMap.length - 1; i++) {
			for (int j = 1; j < anMap[i].length - 1; j++) {
				// 活细胞邻居计数
				nCount = count(i, j);
				if (anMap[i][j] == CALIVE) {
					if (nCount < 2)
						backMap[i][j] = CDEAD;
					else if (nCount < 4)
						backMap[i][j] = CALIVE;
					else
						backMap[i][j] = CDEAD;
				} else {
					if (nCount == 3)
						backMap[i][j] = CALIVE;
				}
			}
		}
		// 更新地图
		for (int i = 0; i < anMap.length; i++)
			anMap[i] = backMap[i].clone();
		// 迭代次数加1
		literation++;
	}

	/**
	 * 计算一个细胞周围的活细胞数
	 */
	protected int count(int row, int col) {
		int[][] map = this.anMap;
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
	public final void reset() {
		literation = 0;
		for (int i = 0; i < anMap.length; i++)
			for (int j = 0; j < anMap[i].length; j++)
				anMap[i][j] = CDEAD;
	}

}

