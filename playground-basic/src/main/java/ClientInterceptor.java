import java.io.IOException;
import java.util.Date;

import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.util.StopWatch;

public class ClientInterceptor implements IClientInterceptor {

	public StopWatch stopWatch;
	// public StopWatch endStopWatch;

	@Override
	public void interceptRequest(IHttpRequest theRequest) {
		// TODO Auto-generated method stub

		stopWatch = new StopWatch(new Date());
		stopWatch.startTask("requestTimer");

		System.out.println("Inside Loop-1 started ");

	}

	@Override
	public void interceptResponse(IHttpResponse theResponse) throws IOException {
		// TODO Auto-generated method stub

//		stopWatch = new StopWatch(new Date());
		stopWatch.endCurrentTask();

		System.out.println("Inside Loop-1 end " + stopWatch.getMillis());

	}

}
