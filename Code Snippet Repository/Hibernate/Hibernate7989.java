	@Test
	public void testInsertWithGeneratedVersionAndId() {
		// Make sure the env supports bulk inserts with generated ids...
		if ( !supportsBulkInsertIdGeneration( IntegerVersioned.class ) ) {
			SkipLog.reportSkip(
					"bulk id generation not supported",
					"test bulk inserts with generated id and generated timestamp"
			);
			return;
		}

		Session s = openSession();
		Transaction t = s.beginTransaction();

		IntegerVersioned entity = new IntegerVersioned( "int-vers" );
		s.save( entity );
		s.createQuery( "select id, name, version from IntegerVersioned" ).list();
		t.commit();
		s.close();

		Long initialId = entity.getId();
		int initialVersion = entity.getVersion();

		s = openSession();
		t = s.beginTransaction();
		int count = s.createQuery( "insert into IntegerVersioned ( name ) select name from IntegerVersioned" ).executeUpdate();
		t.commit();
		s.close();

		assertEquals( "unexpected insertion count", 1, count );

		s = openSession();
		t = s.beginTransaction();
		IntegerVersioned created = ( IntegerVersioned ) s.createQuery( "from IntegerVersioned where id <> :initialId" )
				.setLong( "initialId", initialId.longValue() )
				.uniqueResult();
		t.commit();
		s.close();

		assertEquals( "version was not seeded", initialVersion, created.getVersion() );

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete IntegerVersioned" ).executeUpdate();
		t.commit();
		s.close();
	}
