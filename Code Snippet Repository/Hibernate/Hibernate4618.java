	@Override
	public void reset() {
		super.reset();
		// NOTE : reset is typically called:
		// 		1) on initialization, and
		// 		2) after "after completion" handling is finished.
		//
		// Here we use that to "clear out" all 'delayed after-completion" state.  The registrationThreadId will
		// "lazily" be re-populated on the next synchronizationRegistered call to allow for the potential of the
		// next Session transaction occurring on a different thread (though that transaction would need to completely
		// operate on that thread).
		delayedCompletionHandling = false;
	}
