	@Test
	@FailureExpected( jiraKey = "", message = "" )
	public void testChangeFilterBeforeInitializeInTempSession() {

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

		AccountGroup group = doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "US" );
			AccountGroup accountGroup = entityManager.find( AccountGroup.class, 1L );
			// Change the filter.
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "Europe" );
			return accountGroup;
		} );

		log.info( "Initialize accounts collection" );
		// What should group.getAccounts() contain? Should it be accounts with regionCode "Europe"
		// because that was the most recent filter used in the session?
		Hibernate.initialize( group.getAccounts() );
		// The following will fail because the collection will only contain accounts with regionCode "US"
		assertEquals(2, group.getAccounts().size());
	}
