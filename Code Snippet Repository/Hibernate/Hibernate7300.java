	@Test
	@TestForIssue( jiraKey = "")
	public void testSimpleAddTransient() {
		// Add a transient Child and commit.
		Session s = openSession();
		s.getTransaction().begin();
		Parent p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		// add transient Child
		p.addChild( new Child( "Darwin" ) );
		// collection should still be uninitialized
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		assertEquals( 3, p.getChildren().size() );
		s.getTransaction().commit();
		s.close();

		// Add another transient Child and commit again.
		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		// add transient Child
		p.addChild( new Child( "Comet" ) );
		// collection should still be uninitialized
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		s.merge( p );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		p = s.get( Parent.class, parentId );
		assertFalse( Hibernate.isInitialized( p.getChildren() ) );
		assertEquals( 4, p.getChildren().size() );
		s.getTransaction().commit();
		s.close();
	}
