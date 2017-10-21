	@Test
	public void testMergeNoFilterThenInitializeTempSession() {

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

		final AccountGroup mergedGroup = doInJPA( this::entityManagerFactory, entityManager -> {
			return entityManager.merge( group );
		} );

		// group.getAccounts() will be unfiltered because merge cleared AbstractCollectionPersister#enabledFilters
		Hibernate.initialize( mergedGroup.getAccounts() );
		assertEquals(3, mergedGroup.getAccounts().size());
	}
