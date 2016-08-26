import org.junit.Test;

import junit.framework.Assert;


public class PositiveJUnitSamplerTest {

	@Test
	public void testData()
	{
		for(int i=0;i<500;i=i+2)
		{
//			int number = (int)(Math.random()*100);
			System.out.println("Number is: "+i);
			Assert.assertEquals(0, i%2);
			
		}
	}
}
