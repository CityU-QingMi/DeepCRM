	@Override
	public void afterTransactionCompletion(boolean successful, boolean delayed) {
		log.debug( "#afterTransactionCompletion called" );
		if ( successful ) {
			successfulCompletionCount++;
		}
		else {
			failedCompletionCount++;
		}

	}
