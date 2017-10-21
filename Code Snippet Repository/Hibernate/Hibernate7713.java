	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testUpdateParentOneToTwoSameChildren() {
		CollectionListeners listeners = new CollectionListeners( sessionFactory() );
		ParentWithCollection parent = createParentWithOneChild( "parent", "child" );
		Child child = ( Child ) parent.getChildren().iterator().next();
		assertEquals( 1, parent.getChildren().size() );
		listeners.clear();
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		parent = ( ParentWithCollection ) s.get( parent.getClass(), parent.getId() );
		if ( child instanceof Entity ) {
			child = ( Child ) s.get( child.getClass(), ( ( Entity ) child ).getId() );
		}
		parent.addChild( child );
		tx.commit();
		s.close();
		int index = 0;
		if ( ( ( PersistentCollection ) parent.getChildren() ).wasInitialized() ) {
			checkResult( listeners, listeners.getInitializeCollectionListener(), parent, index++ );
		}
		ChildWithBidirectionalManyToMany childWithManyToMany = null;
		if ( child instanceof ChildWithBidirectionalManyToMany ) {
			childWithManyToMany = ( ChildWithBidirectionalManyToMany ) child;
			if ( ( ( PersistentCollection ) childWithManyToMany.getParents() ).wasInitialized() ) {
				checkResult( listeners, listeners.getInitializeCollectionListener(), childWithManyToMany, index++ );
			}
		}
		if ( !( parent.getChildren() instanceof PersistentSet ) ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), parent, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), parent, index++ );
		}
		if ( childWithManyToMany != null && !( childWithManyToMany.getParents() instanceof PersistentSet ) ) {
			checkResult( listeners, listeners.getPreCollectionUpdateListener(), childWithManyToMany, index++ );
			checkResult( listeners, listeners.getPostCollectionUpdateListener(), childWithManyToMany, index++ );
		}
		checkNumberOfResults( listeners, index );
	}
