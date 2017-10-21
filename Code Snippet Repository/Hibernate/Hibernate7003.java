	@Test
	public void testOptimisticLockExcludeOnNameProperty() throws Exception {
		Conductor c = new Conductor();
		c.setName( "Bob" );
		Session s = openSession();
		s.getTransaction().begin();
		s.persist( c );
		s.flush();

		s.clear();

		c = ( Conductor ) s.get( Conductor.class, c.getId() );
		Long version = c.getVersion();
		c.setName( "Don" );
		s.flush();

		s.clear();

		c = ( Conductor ) s.get( Conductor.class, c.getId() );
		assertEquals( version, c.getVersion() );

		s.getTransaction().rollback();
		s.close();
	}
