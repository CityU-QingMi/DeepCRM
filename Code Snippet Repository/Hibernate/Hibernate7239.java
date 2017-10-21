	private void verifyModifications(long aId) {
		Session s = openSession();
		s.beginTransaction();

		// retrieve the A object and check it
		A a = (A) s.get( A.class, new Long( aId ) );
		assertEquals( aId, a.getId() );
		assertEquals( "Anthony", a.getData() );
		assertNotNull( a.getG() );
		assertNotNull( a.getHs() );
		assertEquals( 1, a.getHs().size() );

		G gFromA = a.getG();
		H hFromA = (H) a.getHs().iterator().next();

		// check the G object
		assertEquals( "Giovanni", gFromA.getData() );
		assertSame( a, gFromA.getA() );
		assertNotNull( gFromA.getHs() );
		assertEquals( a.getHs(), gFromA.getHs() );
		assertSame( hFromA, gFromA.getHs().iterator().next() );

		// check the H object
		assertEquals( "Hellen", hFromA.getData() );
		assertSame( a, hFromA.getA() );
		assertNotNull( hFromA.getGs() );
		assertEquals( 1, hFromA.getGs().size() );
		assertSame( gFromA, hFromA.getGs().iterator().next() );

		s.getTransaction().commit();
		s.close();
	}
