	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentOneChildToNoneByRemove() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		assertEquals( 1, parent.getChildren().size() );
		Child child = ( Child ) parent.getChildren().iterator().next();
		listeners.clear();
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		if ( child instanceof Entity ) {
			child = ( Child ) s.get( child.getClass(), ( ( Entity ) child ).getId() );
		}
		parent.removeChild( child );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) parent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, index++ );
		}
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			ChildWithBidirectionalManyToMany childWithManyToMany = ( ChildWithBidirectionalManyToMany ) child;
			if ( ( ( PersistentCollection ) childWithManyToMany.getParents( ) ).wasInitialized() ) {
				checkResult( listeners, listeners.getInitializeCollectionListener(), childWithManyToMany, index++ );
			}
		}
		checkResult( listeners, listeners.getPreCollectionUpdateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionUpdateListener(), parent, index++ );
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
		}
		checkNumberOfResults( listeners, index );
	}
