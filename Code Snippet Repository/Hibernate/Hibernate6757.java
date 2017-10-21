	@Test
	public void testVersionUnchangedCharArray() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		VersionedBook b = createBook();
		b.setShortDescription( "Hibernate Bible" );
		b.setCode( new Character[] { 'a', 'b', 'c' } );
		s.persist( b );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		VersionedBook b2 = getBookClass().cast( s.get( getBookClass(), getId( b ) ) );
		assertNotNull( b2 );
		assertEquals( b2.getCode()[1].charValue(), b.getCode()[1].charValue() );
		assertEquals( b2.getVersion(), Integer.valueOf( 0 ) );
		s.flush();
		assertEquals( b2.getVersion(), Integer.valueOf( 0 ) );
		s.delete( b2 );
		tx.commit();
		s.close();
	}
