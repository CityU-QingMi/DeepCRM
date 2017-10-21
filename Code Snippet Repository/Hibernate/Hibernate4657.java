	private static List<SessionFactoryServiceInitiator> buildStandardServiceInitiatorList() {
		final List<SessionFactoryServiceInitiator> serviceInitiators = new ArrayList<>();

		serviceInitiators.add( EventListenerServiceInitiator.INSTANCE );
		serviceInitiators.add( StatisticsInitiator.INSTANCE );
		serviceInitiators.add( CacheInitiator.INSTANCE );

		serviceInitiators.add( NativeQueryInterpreterInitiator.INSTANCE );

		return Collections.unmodifiableList( serviceInitiators );
	}
