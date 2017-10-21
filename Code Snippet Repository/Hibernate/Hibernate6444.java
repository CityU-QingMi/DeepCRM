	@Test
	public void testOneToOne() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "ExclusiveDependent", "FK", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "ExclusiveDependent", "empPK", metadata() ) );

		Employee e = new Employee();
		e.empId = 1;
		e.empName = "Emmanuel";
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		ExclusiveDependent d = new ExclusiveDependent();
		d.emp = e;
		d.id = new DependentId();
		d.id.name = "Doggy";
		//d.id.empPK = e.empId; //FIXME not needed when foreign is enabled
		s.persist( d );
		s.flush();
		s.clear();
		d = (ExclusiveDependent) s.get( ExclusiveDependent.class, d.id );
		assertEquals( d.id.empPK, d.emp.empId );
		s.getTransaction().rollback();
		s.close();
	}
