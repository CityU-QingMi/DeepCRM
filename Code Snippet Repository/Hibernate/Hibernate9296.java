	@Test
	public void testInaccessibleTransactionManagerHandling() {
		// first, have JtaPlatform throw an exception
		try {
			final JtaPlatformInaccessibleImpl jtaPlatform = new JtaPlatformInaccessibleImpl( true );
			final TransactionCoordinator transactionCoordinator = new JtaTransactionCoordinatorImpl(
					transactionCoordinatorBuilder,
					owner,
					true,
					jtaPlatform,
					false,
					false
			);

			transactionCoordinator.getTransactionDriverControl().begin();

			fail( "Expecting JtaPlatformInaccessibleException, but call succeeded" );
		}
		catch (JtaPlatformInaccessibleException expected) {
			// expected condition
		}
		catch (Exception e) {
			fail( "Expecting JtaPlatformInaccessibleException, but got " + e.getClass().getName() );
		}


		// then, have it return null
		try {
			final JtaPlatformInaccessibleImpl jtaPlatform = new JtaPlatformInaccessibleImpl( false );
			final TransactionCoordinator transactionCoordinator = new JtaTransactionCoordinatorImpl(
					transactionCoordinatorBuilder,
					owner,
					true,
					jtaPlatform,
					false,
					false
			);

			transactionCoordinator.getTransactionDriverControl().begin();

			fail( "Expecting JtaPlatformInaccessibleException, but call succeeded" );
		}
		catch (JtaPlatformInaccessibleException expected) {
			// expected condition
		}
		catch (Exception e) {
			fail( "Expecting JtaPlatformInaccessibleException, but got " + e.getClass().getName() );
		}
	}
