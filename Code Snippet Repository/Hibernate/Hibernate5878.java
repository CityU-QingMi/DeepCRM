	@Test
	public void testSaveOrUpdateNoFilterThenInitializeTempSession() {

		doInJPA(
				this::entityManagerFactory, entityManager -> {
					AccountGroup accountGroup = new AccountGroup();
					accountGroup.setId( 1L );
					entityManager.persist( accountGroup );

					Account account = new Account();
					account.setName( "A1" );
					account.setRegionCode( "Europe" );
					entityManager.persist( account );
					accountGroup.getAccounts().add( account );

					account = new Account();
					account.setName( "A2" );
					account.setRegionCode( "Europe" );
					entityManager.persist( account );
					accountGroup.getAccounts().add( account );

					account = new Account();
					account.setName( "A3" );
					account.setRegionCode( "US" );
					entityManager.persist( account );
					accountGroup.getAccounts().add( account );
				}
		);

		final AccountGroup group = doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "US" );
			return entityManager.find( AccountGroup.class, 1L );
		} );

		final AccountGroup savedGroup = doInJPA( this::entityManagerFactory, entityManager -> {
			// saveOrUpdate adds the PersistenceCollection to the session "as is"
			return (AccountGroup) entityManager.unwrap( Session.class ).merge( group );
		} );

		Hibernate.initialize( savedGroup.getAccounts() );
		// group.getAccounts() should not be filtered.
		// the following fails because AbstractCollectionPersister#enabledFilters is still intact.
		assertEquals(3, savedGroup.getAccounts().size());
	}
