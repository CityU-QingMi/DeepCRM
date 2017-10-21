	@Test
	@FailureExpected( jiraKey = "", message = "" )
	public void testPreserveFilters() {

		doInJPA( this::entityManagerFactory, entityManager -> {
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
		} );

		AccountGroup group = doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.unwrap( Session.class ).enableFilter( "byRegion" ).setParameter( "region", "US" );
			return entityManager.find( AccountGroup.class, 1L );
		} );

		assertEquals(1, group.getAccounts().size());
	}
