	@Test
	@SuppressWarnings( {"", "", ""})
	public void testNoExtraUpdatesOnPersistentMergeVersionedWithCollection() throws Exception {
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

		// parent is now detached, but we have made no changes. so attempt to merge it
		// into this new session; this should cause no updates...
		s = openSession();
		s.beginTransaction();
		// load parent so that merge will follow entityIsPersistent path
		VersionedEntity persistentParent = ( VersionedEntity ) s.get( VersionedEntity.class, parent.getId() );
		// load children
		VersionedEntity persistentChild = ( VersionedEntity ) persistentParent.getChildren().iterator().next();
		VersionedEntity mergedParent = ( VersionedEntity ) s.merge( persistentParent ); // <-- This merge leads to failure
		s.getTransaction().commit();
		s.close();

		assertUpdateCount( 0 );
		assertInsertCount( 0 );
		assertEquals( "unexpected parent version increment", parent.getVersion(), mergedParent.getVersion() );
		VersionedEntity mergedChild = ( VersionedEntity ) mergedParent.getChildren().iterator().next();
		assertEquals( "unexpected child version increment", child.getVersion(), mergedChild.getVersion() );

		///////////////////////////////////////////////////////////////////////
		// as a control measure, now update the node once it is loaded and
		// make sure we get an update as a result...
		s = openSession();
		s.beginTransaction();
		persistentParent = ( VersionedEntity ) s.get( VersionedEntity.class, parent.getId() );
		persistentParent.setName( "new name" );
		persistentParent.getChildren().add( new VersionedEntity( "child2", "new child" ) );
		persistentParent = ( VersionedEntity ) s.merge( persistentParent );
		s.getTransaction().commit();
		s.close();
		assertUpdateCount( 1 );
		assertInsertCount( 1 );
		///////////////////////////////////////////////////////////////////////

		// cleanup();
	}
