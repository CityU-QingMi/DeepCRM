	@Test
	public void testManytoOne() {
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "FK1", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "FK2", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "name", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "Dependent", "firstName", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "Dependent", "lastName", metadata() ) );

		Employee e = new Employee();
		e.firstName = "Emmanuel";
		e.lastName = "Bernard";
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		Dependent d = new Dependent();
		d.emp = e;
		d.name = "Doggy";
		s.persist( d );
		s.flush();
		s.clear();
		DependentId dId = new DependentId();
		EmployeeId eId = new EmployeeId();
		dId.name = d.name;
		dId.emp = eId;
		eId.firstName = e.firstName;
		eId.lastName = e.lastName;
		d = (Dependent) s.get( Dependent.class, dId );
		assertNotNull( d.emp );
		assertEquals( e.firstName, d.emp.firstName );
		s.delete( d );
		s.delete( d.emp );
		s.getTransaction().commit();
		s.close();
	}
