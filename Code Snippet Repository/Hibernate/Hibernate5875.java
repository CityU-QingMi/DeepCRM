	@Test
	public void testChangeFilterBeforeInitializeInSameSession() {

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

		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "US" );
			AccountGroup accountGroup = entityManager.find( AccountGroup.class, 1L );
			// Change the filter
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "Europe" );
			Hibernate.initialize( accountGroup.getAccounts() );
			// will contain accounts with regionCode "Europe"
			assertEquals( 2, accountGroup.getAccounts().size() );
			return accountGroup;
		} );
	}
