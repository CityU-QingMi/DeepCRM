	@Test
	@TestForIssue(jiraKey = "")
	public void testLazyCollectionLoadingAfterEndTransaction() {
		Session s = openSession();
		s.beginTransaction();
		Parent loadedParent = (Parent) s.load( Parent.class, parentID );
		s.getTransaction().commit();
		s.close();

		assertFalse( Hibernate.isInitialized( loadedParent.getChildren() ) );

		int i = 0;
		for ( Child child : loadedParent.getChildren() ) {
			i++;
			assertNotNull( child );
		}

		assertEquals( CHILDREN_SIZE, i );

		s = openSession();
		s.beginTransaction();
		Child loadedChild = (Child) s.load( Child.class, lastChildID );
		s.getTransaction().commit();
		s.close();

		Parent p = loadedChild.getParent();
		int j = 0;
		for ( Child child : p.getChildren() ) {
			j++;
			assertNotNull( child );
		}

		assertEquals( CHILDREN_SIZE, j );
	}
