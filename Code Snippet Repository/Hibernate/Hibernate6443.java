	@Test
	public void testManyToOne() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "emp_empId", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "Dependent", "empPK", metadata() ) );

		Employee e = new Employee();
		e.empId = 1;
		e.empName = "Emmanuel";
		Session s = openSession(  );
		s.getTransaction().begin();

		Dependent d = new Dependent();
		d.emp = e;
		d.id = new DependentId();
		d.id.name = "Doggy";
		s.persist( d );
		s.persist( e );
		s.flush();
		s.clear();
		d = (Dependent) s.get( Dependent.class, d.id );
		assertEquals( d.id.empPK, d.emp.empId );
		s.getTransaction().rollback();
		s.close();
	}
