import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




public class NewleiTest {
	static boolean[]  para= {true,true,true,false};
	private static doc Doc=new doc("test.c","this is the test!\r\nthe second line!",para);

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void CalTest(){
		Doc.cal();
	}
	
	@Test
	public void ResultTest(){
		assertEquals(7, Doc.wordcount);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
