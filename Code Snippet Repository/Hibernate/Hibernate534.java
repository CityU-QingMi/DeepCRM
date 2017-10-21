	public DelayedPostInsertIdentifier() {
		long value = SEQUENCE.incrementAndGet();
		if ( value < 0 ) {
			synchronized (SEQUENCE) {
				value = SEQUENCE.incrementAndGet();
				if ( value < 0 ) {
					SEQUENCE.set( 0 );
					value = 0;
				}
			}
		}
		this.identifier = value;
	}
