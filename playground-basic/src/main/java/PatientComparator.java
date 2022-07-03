import java.util.Comparator;

import org.hl7.fhir.r4.model.Patient;

public class PatientComparator implements Comparator<Patient>{

	@Override
	public int compare(Patient p1, Patient p2) {
		// TODO Auto-generated method stub
 
		return p1.getNameFirstRep().getGivenAsSingleString().compareTo( p2.getNameFirstRep().getGivenAsSingleString());
		}

}