	@Test
	@RequiresDialect({PostgreSQL81Dialect.class})
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			DebitAccount debitAccount = new DebitAccount( "123-debit" );
			debitAccount.setId( 1L );
			debitAccount.setOwner( "John Doe" );
			debitAccount.setBalance( BigDecimal.valueOf( 100 ) );
			debitAccount.setInterestRate( BigDecimal.valueOf( 1.5d ) );
			debitAccount.setOverdraftFee( BigDecimal.valueOf( 25 ) );

			CreditAccount creditAccount = new CreditAccount( "456-credit" );
			creditAccount.setId( 2L );
			creditAccount.setOwner( "John Doe" );
			creditAccount.setBalance( BigDecimal.valueOf( 1000 ) );
			creditAccount.setInterestRate( BigDecimal.valueOf( 1.9d ) );
			creditAccount.setCreditLimit( BigDecimal.valueOf( 5000 ) );

			entityManager.persist( debitAccount );
			entityManager.persist( creditAccount );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Account> accounts =
					entityManager.createQuery( "select a from Account a" ).getResultList();
			Assert.assertEquals( 2, accounts.size() );
		} );
	}
