	@Test
	public void testPersistThenMergeInSameTxnWithVersion() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		VersionedEntity entity = new VersionedEntity( "test", "test" );
		s.persist( entity );
		s.merge( new VersionedEntity( "test", "test-2" ) );

		try {
			// control operation...
			s.saveOrUpdate( new VersionedEntity( "test", "test-3" ) );
			fail( "saveOrUpdate() should fail here" );
		}
		catch( NonUniqueObjectException expected ) {
			// expected behavior
		}

		tx.commit();
		s.close();

		cleanup();
	}
