import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;
import org.testng.annotations.Test;

import static org.testng.TestNGAntTask.Mode.junit;

public class SampleClientTest {


	@Test
	public void testMain() throws IOException {

		SampleClient sampleClient = new SampleClient();
		sampleClient.main(null);
 		Assert.assertEquals(SampleClient.sortedList.get(0).getNameFirstRep().getGivenAsSingleString() , SampleClient.sortedList.get(1).getNameFirstRep().getGivenAsSingleString());
 		Assert.assertEquals(SampleClient.sortedList.get(1).getNameFirstRep().getGivenAsSingleString() , SampleClient.sortedList.get(2).getNameFirstRep().getGivenAsSingleString());
 		Assert.assertEquals(SampleClient.sortedList.get(2).getNameFirstRep().getGivenAsSingleString() , SampleClient.sortedList.get(3).getNameFirstRep().getGivenAsSingleString());

	}

}
