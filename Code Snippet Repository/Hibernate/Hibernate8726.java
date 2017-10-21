	@Test
	public void testMergingClobData() throws Exception {
		final String original = ClobLocatorTest.buildString( LOB_SIZE, 'a' );
		final String updated = ClobLocatorTest.buildString( LOB_SIZE, 'z' );

		Session s = openSession();
		s.beginTransaction();

		LobHolder entity = new LobHolder();
		entity.setClobLocator( s.getLobHelper().createClob( original ) );
		s.save( entity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		// entity still detached...
		entity.setClobLocator( s.getLobHelper().createClob( updated ) );
		entity = (LobHolder) s.merge( entity );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		entity = (LobHolder) s.get( LobHolder.class, entity.getId() );
		assertEquals( "clob sizes did not match after merge", LOB_SIZE, entity.getClobLocator().length() );
		assertEquals(
				"clob contents did not match after merge",
				updated,
				ClobLocatorTest.extractData( entity.getClobLocator() )
		);
		s.delete( entity );
		s.getTransaction().commit();
		s.close();
	}
