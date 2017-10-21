	protected ParentWithCollection createParentWithNoChildren(String parentName) {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ParentWithCollection parent = createParent( parentName );
		parent.newChildren( createCollection() );
		s.save( parent );
		tx.commit();
		s.close();
		return parent;
	}
