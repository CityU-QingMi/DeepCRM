	@Test
	public void testLoadChildCheckParentContainsChildNoCache() {
		Parent parent = new Parent( "p1" );
		Child child = new Child( "c1" );
		parent.getChildren().add( child );
		child.setParent( parent );
		Child otherChild = new Child( "c2" );
		parent.getChildren().add( otherChild );
		otherChild.setParent( parent );

		Session session = openSession();
		session.beginTransaction();
		session.save( parent );
		session.getTransaction().commit();

		session = openSession();
		session.beginTransaction();
		session.setCacheMode( CacheMode.IGNORE );
		parent = ( Parent ) session.get( Parent.class, parent.getName() );
		assertTrue( parent.getChildren().contains( child ) );
		assertTrue( parent.getChildren().contains( otherChild ) );
		session.getTransaction().commit();

		session = openSession();
		session.beginTransaction();
		session.setCacheMode( CacheMode.IGNORE );

		child = ( Child ) session.get( Child.class, child.getName() );
		assertTrue( child.getParent().getChildren().contains( child ) );
		session.clear();

		child = ( Child ) session.createCriteria( Child.class, child.getName() )
				.add( Restrictions.idEq( "c1" ) )
				.uniqueResult();
		assertTrue( child.getParent().getChildren().contains( child ) );
		assertTrue( child.getParent().getChildren().contains( otherChild ) );
		session.clear();

		child = ( Child ) session.createQuery( "from Child where name = 'c1'" ).uniqueResult();
		assertTrue( child.getParent().getChildren().contains( child ) );

		session.delete( child.getParent() );
		session.getTransaction().commit();
		session.close();
	}
