	@Test
	public void testVersionUnchangedString() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		VersionedBook b = createBook();
		b.setShortDescription( "Hibernate Bible" );
		b.setFullText( "Hibernate in Action aims to..." );
		s.persist( b );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		VersionedBook b2 = getBookClass().cast( s.get( getBookClass(), getId( b ) ) );
		assertNotNull( b2 );
		assertEquals( b2.getFullText(), b.getFullText() );
		assertEquals( b2.getVersion(), Integer.valueOf( 0 ) );
		s.flush();
		assertEquals( b2.getVersion(), Integer.valueOf( 0 ) );
		s.delete( b2 );
		tx.commit();
		s.close();
	}
