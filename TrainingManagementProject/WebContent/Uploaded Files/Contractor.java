class Contractor extends Employee{
	private int empId=10;
	
	public Contractor(){
		//Employee emp1=new Employee(1001);
		//Employee emp2=new Employee();
		//emp2.setEmpId(1002);
		//System.out.println(emp2.getEmpId());
		//Employee emp3=new Employee();
		//System.out.println("Employee id: "+emp2.getEmpId());
		//System.out.println("Contractor id: "+empId);
		Employee emp4=new Employee(1004,"Aish");
		Employee emp5=new Employee(1004,"Aish");
		System.out.println(emp4.equals(emp5));
		
	}
}