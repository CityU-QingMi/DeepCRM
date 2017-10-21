	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentOneChildDiffCollectionSameChild() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		Child child = ( Child ) parent.getChildren().iterator().next();
		listeners.clear();
		assertEquals( 1, parent.getChildren().size() );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		if ( child instanceof Entity ) {
			child = ( Child ) s.get( child.getClass(), ( ( Entity ) child).getId() );
		}
		Collection oldCollection = parent.getChildren();
		parent.newChildren( createCollection() );
		parent.addChild( child );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) oldCollection ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, oldCollection, index++ );
		}
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			ChildWithBidirectionalManyToMany childWithManyToMany = ( ChildWithBidirectionalManyToMany ) child;
			if ( ( ( PersistentCollection ) childWithManyToMany.getParents() ).wasInitialized() ) {
				checkResult( listeners, listeners.getInitializeCollectionListener(), childWithManyToMany, index++ );
			}
		}
		checkResult( listeners, listeners.getPreCollectionRemoveListener(), parent, oldCollection, index++ );
		checkResult( listeners, listeners.getPostCollectionRemoveListener(), parent, oldCollection, index++ );
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			// hmmm, the same parent was removed and re-added to the child's collection;
			// should this be considered an update?
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), ( ChildWithBidirectionalManyToMany ) child, index++ );
		}
		checkResult( listeners, listeners.getPreCollectionRecreateListener(), parent, index++ );
		checkResult( listeners, listeners.getPostCollectionRecreateListener(), parent, index++ );
		checkNumberOfResults( listeners, index );
	}
