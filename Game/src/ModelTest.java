import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	private static Model model = new Model(5, 5);

	@Before
	public void setUp() throws Exception {
		model.reset();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test()
	public void testGetMap() {
        int expect[][] = new int[7][7];
        for (int i = 0; i < expect.length; i++)
            for (int j = 0; j < expect[i].length; j++)
                expect[i][j] = Model.CDEAD;
        assertArrayEquals(expect, model.getMap());
	}

	@Test
	public void testGetLiteraion() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, model.getLiteraion());
            model.step();
        }
	}

	@Test
	public void testSetCell() {
        boolean actual;

        actual = model.setCell(Model.CALIVE, -2, -3);
        assertEquals(false, actual);
        actual = model.setCell(Model.CALIVE, 0, 5);
        assertEquals(false, actual);
        actual = model.setCell(Model.CALIVE, 2, 4);
        assertEquals(true, actual);
        actual = model.setCell(Model.CALIVE, 2, 7);
        assertEquals(false, actual);
	}

	@Test
	public void testGetCell() {
        model.setCell(Model.CALIVE, 7, 2);
        assertEquals(-1, model.getCell(7, 2));
	}

	@Test
	public void testStep() {
        final int alive = Model.CALIVE;
        final int dead = Model.CDEAD;
        int initial[][] = {
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, alive, alive, alive, dead},
                {dead, dead, dead, dead, alive, alive, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead}
        };
        int expect[][] = {
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, alive, dead, dead},
                {dead, dead, dead, alive, dead, alive, dead},
                {dead, dead, dead, alive, dead, alive, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead}
        };
        int expect1[][] = {
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, alive, dead, dead},
                {dead, dead, dead, alive, dead, alive, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead},
                {dead, dead, dead, dead, dead, dead, dead}
        };
        model.setCell(alive, 3, 3);
        model.setCell(alive, 3, 4);
        model.setCell(alive, 3, 5);
        model.setCell(alive, 4, 4);
        model.setCell(alive, 4, 5);
        model.step();
        assertArrayEquals(expect, model.getMap());
        model.step();
        assertArrayEquals(expect1, model.getMap());
	}

	@Test
	public void testCount() {
        final int alive = Model.CALIVE;
        model.setCell(alive, 3, 3);
        model.setCell(alive, 3, 4);
        model.setCell(alive, 3, 5);
        model.setCell(alive, 4, 4);
        model.setCell(alive, 4, 5);
        assertEquals(2, model.count(3, 3));
	}

}
