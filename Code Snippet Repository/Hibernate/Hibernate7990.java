	public void testInsertWithGeneratedTimestampVersion() {
		// Make sure the env supports bulk inserts with generated ids...
		if ( !supportsBulkInsertIdGeneration( TimestampVersioned.class ) ) {
			SkipLog.reportSkip(
					"bulk id generation not supported",
					"test bulk inserts with generated id and generated timestamp"
			);
			return;
		}

		Session s = openSession();
		Transaction t = s.beginTransaction();

		TimestampVersioned entity = new TimestampVersioned( "int-vers" );
		s.save( entity );
		s.createQuery( "select id, name, version from TimestampVersioned" ).list();
		t.commit();
		s.close();

		Long initialId = entity.getId();
		//Date initialVersion = entity.getVersion();

		s = openSession();
		t = s.beginTransaction();
		int count = s.createQuery( "insert into TimestampVersioned ( name ) select name from TimestampVersioned" ).executeUpdate();
		t.commit();
		s.close();

		assertEquals( "unexpected insertion count", 1, count );

		s = openSession();
		t = s.beginTransaction();
		TimestampVersioned created = ( TimestampVersioned ) s.createQuery( "from TimestampVersioned where id <> :initialId" )
				.setLong( "initialId", initialId.longValue() )
				.uniqueResult();
		t.commit();
		s.close();

		assertNotNull( created.getVersion() );
		//assertEquals( "version was not seeded", initialVersion, created.getVersion() );

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete TimestampVersioned" ).executeUpdate();
		t.commit();
		s.close();
	}
