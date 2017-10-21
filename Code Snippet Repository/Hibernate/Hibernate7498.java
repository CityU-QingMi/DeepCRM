	@Test
	@TestForIssue( jiraKey = "" )
	@SuppressWarnings("")
	public void basicTest() {

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// test during store...
		PostalAreaConverter.clearCounts();

		Session session = openSession();
		session.getTransaction().begin();
		session.save( new Address( 1, "123 Main St.", null, PostalArea._78729 ) );
		session.getTransaction().commit();
		session.close();

		assertThat( PostalAreaConverter.toDatabaseCallCount, is(1) );
		assertThat( PostalAreaConverter.toDomainCallCount, is(0) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// test during load...
		PostalAreaConverter.clearCounts();

		session = openSession();
		session.getTransaction().begin();
		Address address = session.get( Address.class, 1 );
		session.getTransaction().commit();
		session.close();

		assertThat( PostalAreaConverter.toDatabaseCallCount, is(0) );
		assertThat( PostalAreaConverter.toDomainCallCount, is(1) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// cleanup
		session = openSession();
		session.getTransaction().begin();
		session.delete( address );
		session.getTransaction().commit();
		session.close();
	}
