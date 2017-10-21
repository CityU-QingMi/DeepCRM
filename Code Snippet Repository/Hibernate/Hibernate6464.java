	@Test
	public void testManyToOne() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "FK1", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "FK2", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "dep_name", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "Dependent", "firstName", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "Dependent", "lastName", metadata() ) );
		Employee e = new Employee();
		e.empId = new EmployeeId();
		e.empId.firstName = "Emmanuel";
		e.empId.lastName = "Bernard";
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		Dependent d = new Dependent();
		d.emp = e;
		d.id = new DependentId();
		d.id.name = "Doggy";
		s.persist( d );
		s.flush();
		s.clear();
		d = (Dependent) s.get( Dependent.class, d.id );
		assertNotNull( d.emp );
		assertEquals( e.empId.firstName, d.emp.empId.firstName );
		s.getTransaction().rollback();
		s.close();
	}
