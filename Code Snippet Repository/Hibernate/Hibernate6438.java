	@Test
	public void testQueryNewEntityInPC() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Employee e = new Employee( 1L, "Paula", "P" );
		Dependent d = new Dependent( "LittleP", e );
		d.setEmp(e);
		s.persist( d );
		s.persist( e );

		// find the entity added above
		Query query = s.createQuery("Select d from Dependent d where d.name='LittleP' and d.emp.empName='Paula'");
		List depList = query.list();
		assertEquals( 1, depList.size() );
		Object newDependent = depList.get(0);
		assertSame( d, newDependent );
		s.getTransaction().rollback();
		s.close();
	}
