	@Test
	@TestForIssue( jiraKey = "" )
	public void testIt() {
		// set up test data
		Session session = openSession();
		session.beginTransaction();
		session.persist( new TheEntity(1) );
		session.getTransaction().commit();
		session.close();

		// assertions based on the persist call
		assertThat( mutableToDomainCallCount, is(1) );  			// 1 instead of 0 because of the deep copy call
		assertThat( mutableToDatabaseCallCount, is(2) );  			// 2 instead of 1 because of the deep copy call

		assertThat( immutableToDomainCallCount, is(0) );			// logical
		assertThat( immutableToDatabaseCallCount, is(1) );			// logical

		assertThat( immutableMutableToDomainCallCount, is(0) );		// was 1 (like mutable) before the JavaTypeDescriptor registration
		assertThat( immutableMutableToDatabaseCallCount, is(1) );	// was 2 (like mutable) before the JavaTypeDescriptor registration

		// clean up test data
		session = openSession();
		session.beginTransaction();
		session.delete( session.byId( TheEntity.class ).getReference( 1 ) );
		session.getTransaction().commit();
		session.close();
	}
