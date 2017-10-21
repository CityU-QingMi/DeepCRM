	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentNullToOneChildDiffCollection() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithNullChildren( "parent" );
		listeners.clear();
		assertNull( parent.getChildren() );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		Collection collectionOrig = parent.getChildren();
		parent.newChildren( createCollection() );
		Child newChild = parent.addChild( "new" );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) collectionOrig ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, collectionOrig, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionRemoveListener(), parent, collectionOrig, index++ );
		checkResult( listeners, listeners.getPostCollectionRemoveListener(), parent, collectionOrig, index++ );
		if ( newChild instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionRecreateListener(), ( ChildWithBidirectionalManyToMany ) newChild, index++ );
			checkResult( listeners, listeners.getPostCollectionRecreateListener(), ( ChildWithBidirectionalManyToMany ) newChild, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionRecreateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionRecreateListener(), parent, index++ );
		checkNumberOfResults( listeners, index );
	}
