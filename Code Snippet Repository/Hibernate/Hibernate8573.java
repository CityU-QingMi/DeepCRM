	@Test
	public void testCriteriaCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz bb = (Baz) s.createCriteria(Baz.class).uniqueResult();
		assertTrue( bb == null );
		Baz baz = new Baz();
		s.save( baz );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Baz b = (Baz) s.createCriteria(Baz.class).uniqueResult();
		assertTrue( Hibernate.isInitialized( b.getTopGlarchez() ) );
		assertTrue( b.getTopGlarchez().size() == 0 );
		s.delete( b );
		s.getTransaction().commit();
		s.close();
	}
