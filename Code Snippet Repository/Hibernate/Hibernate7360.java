	@Test
	public void testCompositeElementWriteMethodDirtying() {
		Container container = new Container( "p1" );
		Container.Content c1 = new Container.Content( "c1" );
		container.getContents().add( c1 );
		Container.Content c2 = new Container.Content( "c2" );

		Session session = openSession();
		session.beginTransaction();
		session.save( container );
		session.flush();
		// at this point, the set on container has now been replaced with a PersistentSet...
		PersistentSet children = (PersistentSet) container.getContents();

		assertFalse( children.add( c1 ) );
		assertFalse( children.isDirty() );

		assertFalse( children.remove( c2 ) );
		assertFalse( children.isDirty() );

		HashSet otherSet = new HashSet();
		otherSet.add( c1 );
		assertFalse( children.addAll( otherSet ) );
		assertFalse( children.isDirty() );

		assertFalse( children.retainAll( otherSet ) );
		assertFalse( children.isDirty() );

		otherSet = new HashSet();
		otherSet.add( c2 );
		assertFalse( children.removeAll( otherSet ) );
		assertFalse( children.isDirty() );

		assertTrue( children.retainAll( otherSet ));
		assertTrue( children.isDirty() );
		assertTrue( children.isEmpty() );

		children.clear();
		assertTrue( children.isDirty() );

		session.flush();

		children.clear();
		assertFalse( children.isDirty() );

		session.delete( container );
		session.getTransaction().commit();
		session.close();
	}
