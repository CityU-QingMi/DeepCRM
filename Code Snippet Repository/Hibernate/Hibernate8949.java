	@Test
	public void testPersistThenMergeInSameTxnWithTimestamp() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		TimestampedEntity entity = new TimestampedEntity( "test", "test" );
		s.persist( entity );
		s.merge( new TimestampedEntity( "test", "test-2" ) );

		try {
			// control operation...
			s.saveOrUpdate( new TimestampedEntity( "test", "test-3" ) );
			fail( "saveOrUpdate() should fail here" );
		}
		catch( NonUniqueObjectException expected ) {
			// expected behavior
		}

		tx.commit();
		s.close();

		cleanup();
	}
