	@Test
	public void testTransactionTimeoutFailure() throws InterruptedException {
		Session session = openSession();
		try {
			Transaction transaction = session.getTransaction();
			transaction.setTimeout( 1 );
			assertEquals( -1,
						  ( (SessionImplementor) session ).getJdbcCoordinator().determineRemainingTransactionTimeOutPeriod()
			);
			transaction.begin();
			Thread.sleep( 1000 );
			session.persist( new Person( "Lukasz", "Antoniak" ) );
			transaction.commit();
		}
		catch (TransactionException e) {
			// expected
		}
		catch (PersistenceException e) {
			assertTyping( TransactionException.class, e.getCause() );
		}
		finally {
			session.close();
		}
	}
