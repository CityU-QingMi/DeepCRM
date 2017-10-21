	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Account account1 = new Account( );
			account1.setId( 1L );
			account1.setAmount( 5000d );
			account1.setRate( 1.25 / 100 );
			account1.setActive( true );
			entityManager.persist( account1 );

			Account account2 = new Account( );
			account2.setId( 2L );
			account2.setAmount( 0d );
			account2.setRate( 1.05 / 100 );
			account2.setActive( false );
			entityManager.persist( account2 );

			Account account3 = new Account( );
			account3.setId( 3L );
			account3.setAmount( 250d );
			account3.setRate( 1.05 / 100 );
			account3.setActive( true );
			entityManager.persist( account3 );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			log.infof( "Activate filter [%s]", "activeAccount");

			//tag::mapping-filter-sql-fragment-alias-query-example[]
			entityManager
				.unwrap( Session.class )
				.enableFilter( "activeAccount" )
				.setParameter( "active", true);

			List<Account> accounts = entityManager.createQuery(
				"select a from Account a", Account.class)
			.getResultList();
			//end::mapping-filter-sql-fragment-alias-query-example[]
			assertEquals( 2, accounts.size());
		} );
	}
