	@Test
	@SuppressWarnings( {"", ""})
	public void testNoExtraUpdatesOnMergeVersionedWithCollection() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		VersionedEntity parent = new VersionedEntity( "parent", "parent" );
		VersionedEntity child = new VersionedEntity( "child", "child" );
		parent.getChildren().add( child );
		child.setParent( parent );
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		// parent is now detached, but we have made no changes.  so attempt to merge it
		// into this new session; this should cause no updates...
		s = openSession();
		s.beginTransaction();
		VersionedEntity mergedParent = ( VersionedEntity ) s.merge( parent );
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 0 );
		assertEquals( "unexpected parent version increment", parent.getVersion(), mergedParent.getVersion() );
		VersionedEntity mergedChild = ( VersionedEntity ) mergedParent.getChildren().iterator().next();
		assertEquals( "unexpected child version increment", child.getVersion(), mergedChild.getVersion() );

		///////////////////////////////////////////////////////////////////////
		// as a control measure, now update the node while it is detached and
		// make sure we get an update as a result...
		mergedParent.setName( "new name" );
		mergedParent.getChildren().add( new VersionedEntity( "child2", "new child" ) );
		s = openSession();
		s.beginTransaction();
		parent = ( VersionedEntity ) s.merge( mergedParent );
		s.getTransaction().commit();
		s.close();
		assertUpdateCount( 1 );
		assertInsertCount( 1 );
		///////////////////////////////////////////////////////////////////////

		cleanup();
    }
