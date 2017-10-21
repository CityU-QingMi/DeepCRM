	@Override
	public void loggedEvent(Level level, String renderedMessage, Throwable thrown) {
		if ( renderedMessage != null ) {
			for ( String expectedPrefix : expectedPrefixes ) {
				if ( renderedMessage.startsWith( expectedPrefix ) ) {
					triggered.set( true );
					triggerMessage.set( renderedMessage );
				}
			}
		}
	}
