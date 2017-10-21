	@Test
	public void testConverterMutability() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Account account = new Account();
			account.setId( 1L );
			account.setOwner( "John Doe" );
			account.setBalance( new Money( 250 * 100L ) );

			entityManager.persist( account );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-jpa-convert-money-converter-mutability-plan-example[]
			Account account = entityManager.find( Account.class, 1L );
			account.getBalance().setCents( 150 * 100L );
			entityManager.persist( account );
			//end::basic-jpa-convert-money-converter-mutability-plan-example[]
		} );
	}
