	public void assertNoLeaks() {
		if ( connectionCounter != null ) {
			int currentConnectionLeakCount = countConnectionLeaks();
			int diff = currentConnectionLeakCount - connectionLeakCount;
			if ( diff > 0 ) {
				throw new ConnectionLeakException( String.format(
						"%d connection(s) have been leaked! Previous leak count: %d, Current leak count: %d",
						diff,
						connectionLeakCount,
						currentConnectionLeakCount
				) );
			}
		}
	}
