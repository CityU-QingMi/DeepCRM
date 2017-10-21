	@Override
	public void processAnyDelayedAfterCompletion() {
		if ( delayedCompletionHandling ) {
			delayedCompletionHandling = false;

			// false here (rather than how we used to keep and check the status) because as discussed above
			// the delayed logic should only ever occur during rollback
			doAfterCompletion( false, true );

			// NOTE : doAfterCompletion calls reset
			throw new HibernateException( "Transaction was rolled back in a different thread!" );
		}
	}
