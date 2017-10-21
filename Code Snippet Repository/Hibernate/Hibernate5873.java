	@Before
	public void createData() {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		
		Manager manager1 = new Manager();
		entityManager.persist( manager1 );
		
		Manager manager2 = new Manager();
		manager2.managers.add( manager1 );
		entityManager.persist( manager2 );
		
		Employee employee = new Employee();
		employee.managers.add( manager1 );
		entityManager.persist( employee );
		
		Location location = new Location();
		location.address = "123 somewhere";
		location.zip = 12345;
		entityManager.persist( location );
		
		Company company = new Company();
		company.employees.add( employee );
		company.employees.add( manager1 );
		company.employees.add( manager2 );
		company.location = location;
		company.markets.add( Market.SERVICES );
		company.markets.add( Market.TECHNOLOGY );
		company.phoneNumbers.add( "012-345-6789" );
		company.phoneNumbers.add( "987-654-3210" );
		entityManager.persist( company );
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
