	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryStream() {
		Session session = openSession();
		try {

			session.getTransaction().begin();
			MyEntity e= new MyEntity();
			e.id = 1;
			e.name = "Test";
			session.persist( e );
			session.getTransaction().commit();
			session.clear();

			// Test stream query without type.
			Object result = session.createQuery( "From MyEntity" ).stream().findFirst().orElse( null );
			assertTyping( MyEntity.class, result );

			// Test stream query with type.
			result = session.createQuery( "From MyEntity", MyEntity.class ).stream().findFirst().orElse( null );
			assertTyping( MyEntity.class, result );

			// Test stream query using forEach
			session.createQuery( "From MyEntity", MyEntity.class ).stream().forEach( i -> {
				assertTyping( MyEntity.class, i );
			} );

			Stream<Object[]> data = session.createQuery( "SELECT me.id, me.name FROM MyEntity me" ).stream();
			data.forEach( i -> {
				assertTyping( Integer.class, i[0] );
				assertTyping( String.class, i[1] );
			});

		}
		finally {
			session.close();
		}
	}
