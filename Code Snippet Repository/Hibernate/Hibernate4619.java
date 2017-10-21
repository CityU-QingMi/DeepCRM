	@Override
	public void afterCompletion(int status) {
		log.tracef( "Synchronization coordinator: afterCompletion(status=%s)", status );

		// The whole concept of "tracking" comes down to this code block..
		// Essentially we need to see if we can process the callback immediately.  So here we check whether the
		// current call is happening on the same thread as the thread under which we registered the Synchronization.
		// As far as we know, this can only ever happen in the rollback case where the transaction had been rolled
		// back on a separate "reaper" thread.  Since we know the transaction status and that check is not as heavy
		// as accessing the current thread, we check that first
		if ( JtaStatusHelper.isRollback( status ) ) {
			// we are processing a rollback, see if it is the same thread
			final long currentThreadId = Thread.currentThread().getId();
			final boolean isRegistrationThread = currentThreadId == registrationThreadId;
			if ( ! isRegistrationThread ) {
				// so we do have the condition of a rollback initiated from a separate thread.  Set the flag here and
				// check for it in SessionImpl. See HHH-7910.
				delayedCompletionHandling = true;

				log.rollbackFromBackgroundThread( status );
				return;
			}
		}

		// otherwise, do the callback immediately
		doAfterCompletion( JtaStatusHelper.isCommitted( status ), false );
	}
