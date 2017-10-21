	@Override
	public void registerSynchronization(Synchronization synchronization) {
		if ( synchronization == null ) {
			throw new NullSynchronizationException();
		}

		if ( synchronizations == null ) {
			synchronizations = new LinkedHashSet<Synchronization>();
		}

		final boolean added = synchronizations.add( synchronization );
		if ( !added ) {
			log.synchronizationAlreadyRegistered( synchronization );
		}
	}
