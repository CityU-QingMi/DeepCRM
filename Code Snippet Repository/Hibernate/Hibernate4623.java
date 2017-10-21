	@Override
	public void notifySynchronizationsAfterTransactionCompletion(int status) {
		log.tracef(
				"SynchronizationRegistryStandardImpl.notifySynchronizationsAfterTransactionCompletion(%s)",
				status
		);

		if ( synchronizations != null ) {
			try {
				for ( Synchronization synchronization : synchronizations ) {
					try {
						synchronization.afterCompletion( status );
					}
					catch (Throwable t) {
						log.synchronizationFailed( synchronization, t );
						throw new LocalSynchronizationException(
								"Exception calling user Synchronization (afterCompletion): " + synchronization.getClass().getName(),
								t
						);
					}
				}
			}
			finally {
				clearSynchronizations();
			}
		}
	}
