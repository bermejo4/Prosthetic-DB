package db.inteface;

public interface DBManagerInterface {
	public void connect();
	public void disconnect();
	public void createTables();
	
	public DoctorManagerInterface getDoctorManager();
	public HospitalManagerInterface getHospitalManager();
	public PatientManagerInterface getPatientManager();
}
