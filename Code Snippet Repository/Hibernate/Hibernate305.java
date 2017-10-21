	@Test
	public void testLifecycle() {
		//tag::mapping-column-read-and-write-composite-type-persistence-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-datetime-temporal-date-persist-example[]
			Savings savings = new Savings( );
			savings.setId( 1L );
			savings.setWallet( new MonetaryAmount( BigDecimal.TEN, Currency.getInstance( Locale.US ) ) );
			entityManager.persist( savings );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Savings savings = entityManager.find( Savings.class, 1L );
			assertEquals( 10, savings.getWallet().getAmount().intValue());
		} );
		//end::mapping-column-read-and-write-composite-type-persistence-example[]
	}
