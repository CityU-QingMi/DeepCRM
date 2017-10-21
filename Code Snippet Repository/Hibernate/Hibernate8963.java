	@Test
    @SuppressWarnings( {""})
	public void testNoExtraUpdatesOnMergeVersioned() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		VersionedEntity entity = new VersionedEntity( "entity", "entity" );
		s.persist( entity );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		// entity is now detached, but we have made no changes.  so attempt to merge it
		// into this new session; this should cause no updates...
		s = openSession();
		s.beginTransaction();
		VersionedEntity mergedEntity = ( VersionedEntity ) s.merge( entity );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 0 );
        assertEquals( "unexpected version increment", entity.getVersion(), mergedEntity.getVersion() );


		///////////////////////////////////////////////////////////////////////
		// as a control measure, now update the node while it is detached and
		// make sure we get an update as a result...
		entity.setName( "new name" );
		s = openSession();
		s.beginTransaction();
		entity = ( VersionedEntity ) s.merge( entity );
		s.getTransaction().commit();
		s.close();
		assertUpdateCount( 1 );
		assertInsertCount( 0 );
		///////////////////////////////////////////////////////////////////////

		cleanup();
    }
