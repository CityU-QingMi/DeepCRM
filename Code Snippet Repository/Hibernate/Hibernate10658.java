	@Test
	@Priority(10)
	public void initData() {
		// revision 1
		this.typeId = TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			SalaryEmployeeType type = new SalaryEmployeeType();
			type.setData( "salaried" );
			entityManager.persist( type );
			return type.getId();
		} );
		// revision 2
		this.employeeId = TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			EmployeeType type = entityManager.find( EmployeeType.class, typeId );
			Employee employee = new Employee();
			employee.setType( type );
			entityManager.persist( employee );
			return employee.getId();
		} );
		// revision 3
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Employee employee = entityManager.find( Employee.class, employeeId );
			entityManager.remove( employee );
		} );
	}
