	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentNullToOneChild() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithNullChildren( "parent" );
		listeners.clear();
		assertNull( parent.getChildren() );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		assertNotNull( parent.getChildren() );
		Child newChild = parent.addChild( "new" );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) parent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionUpdateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionUpdateListener(), parent, index++ );
		if ( newChild instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionRecreateListener(), ( ChildWithBidirectionalManyToMany ) newChild, index++ );
			checkResult( listeners, listeners.getPostCollectionRecreateListener(), ( ChildWithBidirectionalManyToMany ) newChild, index++ );
		}
		checkNumberOfResults( listeners, index );
	}
