	public ConnectionLeakUtil() {
		for ( IdleConnectionCounter connectionCounter : idleConnectionCounters ) {
			if ( connectionCounter.appliesTo( Dialect.getDialect().getClass() ) ) {
				this.connectionCounter = connectionCounter;
				break;
			}
		}
		if ( connectionCounter != null ) {
			connectionLeakCount = countConnectionLeaks();
		}
	}
