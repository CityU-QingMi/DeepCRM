	@Test
	@TestForIssue(jiraKey = "")
	public void testDetachedAssociationOnPersisting() {
		sessionFactory().getStatistics().clear();

		Address loadedAddress = doInHibernate(
				this::sessionFactory,
				session -> {
					// first load the address
					Address _loadedAddress = session.load(
							Address.class,
							1L
					);
					assertNotNull( _loadedAddress );
					return _loadedAddress;
				}
		);

		doInHibernate( this::sessionFactory, session -> {
			session.get( Address.class, 1L );

			Person person = new Person();
			person.setId( 1L );
			person.setName( "Johnny Depp" );
			person.setAddress( loadedAddress );

			session.persist( person );
		} );
	}
