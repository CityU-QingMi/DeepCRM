	@Test
	public void testManyToOne() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "FIRSTNAME", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "LASTNAME", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "name", metadata() ) );

		assertTrue( SchemaUtil.isColumnPresent( "Policy", "FIRSTNAME", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Policy", "LASTNAME", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Policy", "NAME", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "Policy", "type", metadata() ) );


		final Employee e = new Employee();
		e.empId = new EmployeeId();
		e.empId.firstName = "Emmanuel";
		e.empId.lastName = "Bernard";
		final Session s = openSession();
		s.getTransaction().begin();
		s.persist( e );
		final Dependent d = new Dependent();
		d.emp = e;
		d.id = new DependentId();
		d.id.name = "Doggy";
		s.persist( d );
		Policy p = new Policy();
		p.dep = d;
		p.id = new PolicyId();
		p.id.type = "Vet Insurance";
		s.persist( p );

		s.flush();
		s.clear();
		p = (Policy) s.get( Policy.class, p.id );
		assertNotNull( p.dep );
		assertEquals( e.empId.firstName, p.dep.emp.empId.firstName );
		s.getTransaction().rollback();
		s.close();
	}
