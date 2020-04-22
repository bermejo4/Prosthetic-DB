package db.inteface;

public interface DBManagerInterface {
	public void connect();
	public void disconnect();
	public void createTables();
	public void deleteTables();
	public void initializeDoctors(String name, String telephone, String department, int hospital_id);
	public void initializeHospitals(String name, String location, String telephone);
	
	public DoctorManagerInterface getDoctorManager();
	public HospitalManagerInterface getHospitalManager();
	public PatientManagerInterface getPatientManager();
	public BEManagerInterface getBiomedManager();

}
