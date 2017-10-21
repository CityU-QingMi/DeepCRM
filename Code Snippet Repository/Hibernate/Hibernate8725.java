	@Test
	public void testMergingBlobData() throws Exception {
		final byte[] original = BlobLocatorTest.buildByteArray( LOB_SIZE, true );
		final byte[] updated = BlobLocatorTest.buildByteArray( LOB_SIZE, false );

		Session s = openSession();
		s.beginTransaction();

		LobHolder entity = new LobHolder();
		entity.setBlobLocator( s.getLobHelper().createBlob( original ) );
		s.save( entity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		// entity still detached...
		entity.setBlobLocator( s.getLobHelper().createBlob( updated ) );
		entity = (LobHolder) s.merge( entity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		entity = (LobHolder) s.get( LobHolder.class, entity.getId() );
		assertEquals( "blob sizes did not match after merge", LOB_SIZE, entity.getBlobLocator().length() );
		assertTrue(
				"blob contents did not match after merge",
				Arrays.equals( updated, BlobLocatorTest.extractData( entity.getBlobLocator() ) )
		);
		s.delete( entity );
		s.getTransaction().commit();
		s.close();
	}
