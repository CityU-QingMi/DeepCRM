	protected final void initialize(final boolean writing) {
		if ( initialized ) {
			return;
		}

		withTemporarySessionIfNeeded(
				new LazyInitializationWork<Object>() {
					@Override
					public Object doWork() {
						session.initializeCollection( AbstractPersistentCollection.this, writing );
						return null;
					}
				}
		);
	}
