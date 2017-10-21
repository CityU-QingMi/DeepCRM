	@Test
	public void testBidirectionalAssociation() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "Dependent", "emp_empId", metadata() ) );
		assertTrue( !SchemaUtil.isColumnPresent( "Dependent", "empPK", metadata() ) );
		Employee e = new Employee();
		e.empId = 1;
		e.empName = "Emmanuel";

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( e );
		Dependent d = new Dependent();
		d.emp = e;
		s.persist( d );
		s.flush();
		s.clear();
		d = getDerivedClassById( e, s, Dependent.class );
		assertEquals( e.empId, d.emp.empId );
		s.getTransaction().rollback();
		s.close();
	}
