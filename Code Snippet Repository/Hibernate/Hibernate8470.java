	protected void prepareTest()
			throws Exception {
		Session s = openSession();
		s.beginTransaction();

		Parent p = new Parent();
		for ( int i = 0; i < CHILDREN_SIZE; i++ ) {
			final Child child = p.makeChild();
			s.persist( child );
			lastChildID = child.getId();
		}
		s.persist( p );
		parentID = p.getId();

		s.getTransaction().commit();
		s.clear();
		s.close();
	}
