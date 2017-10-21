	@Override
	protected void cleanupTest() {
		ParentWithCollection dummyParent = createParent( "dummyParent" );
		dummyParent.newChildren( createCollection() );
		Child dummyChild = dummyParent.addChild( "dummyChild" );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		List children = s.createCriteria( dummyChild.getClass() ).list();
		List parents = s.createCriteria( dummyParent.getClass() ).list();
		for ( Iterator it = parents.iterator(); it.hasNext(); ) {
			ParentWithCollection parent = ( ParentWithCollection ) it.next();
			parent.clearChildren();
			s.delete( parent );
		}
		for ( Iterator it = children.iterator(); it.hasNext(); ) {
			s.delete( it.next() );
		}
		tx.commit();
		s.close();
	}
