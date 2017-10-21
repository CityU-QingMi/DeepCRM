	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryStream() {
		doInHibernate( this::sessionFactory, session -> {
			MyEntity e= new MyEntity();
			e.id = 1;
			e.name = "Test";
			session.persist( e );
		} );

		doInHibernate( this::sessionFactory, session -> {
			// Test stream query without type.
			Object result = session.createQuery( "From MyEntity" ).getResultStream().findFirst().orElse( null );
			assertTyping( MyEntity.class, result );

			// Test stream query with type.
			result = session.createQuery( "From MyEntity", MyEntity.class ).getResultStream().findFirst().orElse( null );
			assertTyping( MyEntity.class, result );

			// Test stream query using forEach
			session.createQuery( "From MyEntity", MyEntity.class ).getResultStream().forEach( i -> {
				assertTyping( MyEntity.class, i );
			} );

			Stream<Object[]> data = session.createQuery( "SELECT me.id, me.name FROM MyEntity me" ).getResultStream();
			data.forEach( i -> {
				assertTyping( Integer.class, i[0] );
				assertTyping( String.class, i[1] );
			});
		} );
	}
