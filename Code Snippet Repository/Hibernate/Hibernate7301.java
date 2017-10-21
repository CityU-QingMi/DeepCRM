	@Test
	@TestForIssue( jiraKey = "")
	public void testSimpleRemoveManaged() {
		// Remove a managed entity element and commit
		Session s = openSession();
		s.getTransaction().begin();
		Parent p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		// get c1 so it is managed, then remove and commit
		p.removeChild( s.get( Child.class, childId1 ) );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		assertEquals( 1, p.getChildren().size() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		// get c1 so it is managed, then remove, merge and commit
		p.removeChild( s.get( Child.class, childId2 ) );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		s.merge( p );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		assertEquals( 0, p.getChildren().size() );
		s.getTransaction().commit();
		s.close();
	}
