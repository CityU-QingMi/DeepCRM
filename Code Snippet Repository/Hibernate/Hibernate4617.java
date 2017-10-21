	protected void doAfterCompletion(boolean successful, boolean delayed) {
		log.tracef( "Synchronization coordinator: doAfterCompletion(successful=%s, delayed=%s)", successful, delayed );

		try {
			target.afterCompletion( successful, delayed );
		}
		finally {
			reset();
		}
	}
