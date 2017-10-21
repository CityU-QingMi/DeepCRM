	@Test
	public void testEntityName() throws Exception {
		assertEquals( "Corporation", metadata().getEntityBinding( Company.class.getName() ).getTable().getName() );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Company comp = new Company();
		s.persist( comp );
		comp.setName( "JBoss Inc" );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		List result = s.createQuery( "from Corporation" ).list();
		assertNotNull( result );
		assertEquals( 1, result.size() );
		tx.commit();
		s.close();

	}
