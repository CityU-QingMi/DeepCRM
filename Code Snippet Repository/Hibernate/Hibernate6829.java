	@Test
	public void testGetOnIntermediateMappedSuperclass() {
		final BigDecimal withdrawalLimit = new BigDecimal( 1000.00 ).setScale( 2 );
		Session session = openSession();
		session.beginTransaction();
		SavingsAccount savingsAccount = new SavingsAccount( "123", withdrawalLimit );
		session.save( savingsAccount );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		Account account = (Account) session.get( Account.class, savingsAccount.getId() );
		// Oracle returns the BigDecimal with scale=0, which is equal to 1000 (not 1000.00);
		// compare using BigDecimal.doubleValue;
		assertEquals(
				withdrawalLimit.doubleValue(),
				( (SavingsAccount) account ).getWithdrawalLimit().doubleValue(),
				0.001);
		session.delete( account );
		session.getTransaction().commit();
		session.close();
	}
