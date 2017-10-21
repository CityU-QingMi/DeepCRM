	@Override
	public void notifySynchronizationsBeforeTransactionCompletion() {
		log.trace( "SynchronizationRegistryStandardImpl.notifySynchronizationsBeforeTransactionCompletion" );

		if ( synchronizations != null ) {
			for ( Synchronization synchronization : synchronizations ) {
				try {
					synchronization.beforeCompletion();
				}
				catch (Throwable t) {
					log.synchronizationFailed( synchronization, t );
					throw new LocalSynchronizationException(
							"Exception calling user Synchronization (beforeCompletion): " + synchronization.getClass().getName(),
							t
					);
				}
			}
		}
	}
