	private void thinkRandomTime() {
		try {
			Thread.sleep( random.nextInt( THINK_TIME_MILLIS ) );
		}
		catch (InterruptedException ex) {
			throw new RuntimeException( "sleep interrupted", ex );
		}

		if ( TERMINATE_ALL_USERS ) {
			throw new RuntimeException( "told to terminate (because a UserRunner had failed)" );
		}
	}
