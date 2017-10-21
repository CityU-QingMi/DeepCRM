	@Test
	public void testLifecycle() {
		//tag::mapping-column-formula-persistence-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-datetime-temporal-date-persist-example[]
			Account account = new Account( );
			account.setId( 1L );
			account.setCredit( 5000d );
			account.setRate( 1.25 / 100 );
			entityManager.persist( account );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Account account = entityManager.find( Account.class, 1L );
			assertEquals( Double.valueOf( 62.5d ), account.getInterest());
		} );
		//end::mapping-column-formula-persistence-example[]
	}
