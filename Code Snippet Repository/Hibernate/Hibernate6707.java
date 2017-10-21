	@Test
	public void test2ndLevelSubClass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		A320b a = new A320b();
		a.setJavaEmbeddedVersion( "Elephant" );
		a.setNbrOfSeats( 300 );
		s.persist( a );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "from " + A320.class.getName() + " as a where a.javaEmbeddedVersion = :version" );
		q.setString( "version", "Elephant" );
		List a320s = q.list();
		assertNotNull( a320s );
		assertEquals( 1, a320s.size() );
		tx.commit();
		s.close();
	}
