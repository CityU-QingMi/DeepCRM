	@Test
	public void testUserSynchronizationExceptions() {
		// exception in beforeCompletion
		SynchronizationRegistryStandardImpl registry = new SynchronizationRegistryStandardImpl();
		Synchronization synchronization = SynchronizationErrorImpl.forBefore();
		registry.registerSynchronization( synchronization );
		try {
			registry.notifySynchronizationsBeforeTransactionCompletion();
			fail( "Expecting LocalSynchronizationException, but call succeeded" );
		}
		catch (LocalSynchronizationException expected) {
			// expected
		}
		catch (Exception e) {
			fail( "Was expecting LocalSynchronizationException, but got " + e.getClass().getName() );
		}


		// exception in beforeCompletion
		registry.clearSynchronizations();
		registry = new SynchronizationRegistryStandardImpl();
		synchronization = SynchronizationErrorImpl.forAfter();
		registry.registerSynchronization( synchronization );
		try {
			registry.notifySynchronizationsAfterTransactionCompletion( Status.STATUS_COMMITTED );
			fail( "Expecting LocalSynchronizationException, but call succeeded" );
		}
		catch (LocalSynchronizationException expected) {
			// expected
		}
		catch (Exception e) {
			fail( "Was expecting LocalSynchronizationException, but got " + e.getClass().getName() );
		}

	}
