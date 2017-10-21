	private Session buildEntityManager(SynchronizationType synchronizationType, Map map) {
		validateNotClosed();
		SessionBuilderImplementor builder = withOptions();
		if ( synchronizationType == SynchronizationType.SYNCHRONIZED ) {
			builder.autoJoinTransactions( true );
		}
		else {
			builder.autoJoinTransactions( false );
		}

		final Session session = builder.openSession();
		if ( map != null ) {
			map.keySet().forEach( key -> {
				if ( key instanceof String ) {
					session.setProperty( (String) key, map.get( key ) );
				}
			} );
		}
		return session;
	}
