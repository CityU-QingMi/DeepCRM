	@Ignore
	@Test
	public void testManyUsers() throws Throwable {
		try {
			// setup - create users
			for ( int i = 0; i < USER_COUNT; i++ ) {
				Customer customer = createCustomer( 0 );
				getCustomerIDs().add( customer.getId() );
			}
			assertEquals( "failed to create enough Customers", USER_COUNT, getCustomerIDs().size() );

			final ExecutorService executor = Executors.newFixedThreadPool( USER_COUNT );

			CyclicBarrier barrier = new CyclicBarrier( USER_COUNT + 1 );
			List<Future<Void>> futures = new ArrayList<Future<Void>>( USER_COUNT );
			for ( Integer customerId : getCustomerIDs() ) {
				Future<Void> future = executor.submit( new UserRunner( customerId, barrier ) );
				futures.add( future );
				Thread.sleep( LAUNCH_INTERVAL_MILLIS ); // rampup
			}
			barrier.await( 2, TimeUnit.MINUTES ); // wait for all threads to finish
			log.info( "All threads finished, let's shutdown the executor and check whether any exceptions were reported" );
			for ( Future<Void> future : futures ) {
				future.get();
			}
			executor.shutdown();
			log.info( "All future gets checked" );
		}
		catch (Throwable t) {
			log.error( "Error running test", t );
			throw t;
		}
	}
