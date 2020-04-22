package db.inteface;

public interface DBManagerInterface {
	public void connect();
	public void disconnect();
	public void createTables();
	public void deleteTables();
	public void initializeDoctors(String name, String telephone, String department, int hospital_id);
	
	public DoctorManagerInterface getDoctorManager();
	public HospitalManagerInterface getHospitalManager();
	public PatientManagerInterface getPatientManager();
	public BEManagerInterface getBiomedManager();

}
