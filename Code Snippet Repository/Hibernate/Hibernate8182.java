	@Test
	@TestForIssue( jiraKey = "")
	public void testExplicitJoinBetweenFetchJoins() {

		Session s = openSession();
		s.getTransaction().begin();

		Parent p =
				(Parent) s.createQuery(
						"select p from Parent p inner join fetch p.children c inner join p.children cRestrict inner join fetch c.grandChildren where cRestrict.value = 'c1'" )
						.uniqueResult();

		assertEquals( "p", p.getValue() );
		assertTrue( Hibernate.isInitialized( p.getChildren() ) );
		assertEquals( 2, p.getChildren().size() );
		Iterator<Child> iterator = p.getChildren().iterator();
		Child cA = iterator.next();
		assertTrue( Hibernate.isInitialized( cA.getGrandChildren() ) );
		if ( cA.getValue().equals( "c1" ) ) {
			assertEquals( 2, cA.getGrandChildren().size() );
			Child cB = iterator.next();
			assertTrue( Hibernate.isInitialized( cB.getGrandChildren() ) );
			assertEquals( 3, cB.getGrandChildren().size() );
		}
		else if ( cA.getValue().equals( "c2" ) ) {
			assertEquals( 3, cA.getGrandChildren().size() );
			Child cB = iterator.next();
			assertTrue( Hibernate.isInitialized( cB.getGrandChildren() ) );
			assertEquals( 2, cB.getGrandChildren().size() );
		}
		else {
			fail( "unexpected value" );
		}

		s.getTransaction().commit();
		s.close();
	}
