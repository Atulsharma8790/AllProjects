import org.junit.Test;

import junit.framework.Assert;


public class JUnitSamplerTest {

	@Test
	public void testData()
	{
		for(int i=0;i<50;i++)
		{
			int number = (int)(Math.random()*100);
			System.out.println("Number is: "+number);
			Assert.assertEquals(0, number%2);
			
		}
	}
}
