import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.util.StopWatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Patient;

public class SampleClient {

	static IGenericClient client;
	static ArrayList<Patient> sortedList;
	public static void main(String[] theArgs) throws IOException {

		// Create a FHIR client
		FhirContext fhirContext = FhirContext.forR4();
		client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
		client.registerInterceptor(new LoggingInterceptor(false));
		client.registerInterceptor(new ClientInterceptor());
		
		// Search for Patient resources
		Bundle response = client
				.search()
				.forResource("Patient")
				.where(Patient.FAMILY.matches().value("SMITH"))
				.returnBundle(Bundle.class)
				.execute();

		// Basic Tasks:
		// 1) Modify SampleClient so that it prints the first and last name, and birth date of each Patient to the screen

		List<BundleEntryComponent> patientList = response.getEntry();
		patientList.stream()
				.forEach(patient -> System.out.println("firstName: "
													+ ((Patient) patient.getResource()).getNameFirstRep().getGivenAsSingleString() + "\n"
													+ "lastName: " + ((Patient) patient.getResource()).getNameFirstRep().getFamily() + "\n"
													+ "BirthDate: " + ((Patient) patient.getResource()).getBirthDate() + "\n" + "Name: "
													+ ((Patient) patient.getResource()).getNameFirstRep().getNameAsSingleString() + "\n\n"));

		// 2) Sort the output so that the results are ordered by the patient's first name
		  sortedList = new ArrayList<>();

		patientList.stream().
		forEach(patient -> sortedList.add((Patient) patient.getResource()) );
		
		Collections.sort(sortedList, new PatientComparator());
		System.out.println("after Sort \n\n");
		sortedList.stream()
		.forEach(patient -> System.out.println("firstName: "
											+ patient.getNameFirstRep().getGivenAsSingleString() + "\n"
											+ "lastName: " + patient.getNameFirstRep().getFamily() + "\n"
											+ "BirthDate: " + patient.getBirthDate() + "\n" + "Name: "
											+ patient.getNameFirstRep().getNameAsSingleString() + "\n\n"));
		
		// 3) Commit your work
			//this task will be done manually
	
		
		//Intermediate Tasks:
				// for task1,2,3 go inside the function loopTwo()
			// 4) Run this loop three times, printing the average response time for each loop. The first two times the loop should run as described above. The third time the loop of 20 searches is run, the searches should be performed with caching disabled.
			loopTwo();
			loopTwo();
			loopTwo();


			// 5) If there is enough time between runs, you should expect to see loop 2 with a shorter average response time than loop 1 and 3.

			// 6) Please include unit tests for your work

			// 7) Commit your work
		
		
	}
	
	private static void loopTwo() throws FileNotFoundException {
		// 1) Create a text file containing 20 different last names
		
	//done
	
		// 2) Modify 'SampleClient' so that instead of searching for patients with last name 'SMITH', it reads in the contents of this file and for each last name queries for patients with that last name
		StopWatch stopWatch = new StopWatch(new Date());
		stopWatch.startTask("loop-2");
		File myObj = new File("C:\\Users\\Mehmood\\git\\playground-basic\\src\\main\\java\\patientfile.txt"); // Specify the filename
		
		Scanner read = new Scanner(myObj);
		while(read.hasNext()) {
			
			String data = read.nextLine();
			String[] d = data.split(",");
			if(d.length >= 2) {
				
				//System.out.print(data);				
				d[1].equalsIgnoreCase("smith");
			}	
		} stopWatch.endCurrentTask(); 
		System.out.print("\nLoop-2 "+ stopWatch.getMillis() + "ms \n"); 
		
		// 3) Print the average response time for these 20 searches by implementing an IClientInterceptor that uses the requestStopWatch to determine the response time of each request.

		ClientInterceptor interceptor = (ClientInterceptor ) client.getInterceptorService().getAllRegisteredInterceptors().get(1);	
	//	System.out.println("\n loop-1 " + interceptor.stopWatch.getMillis());		
		 
	}

}
