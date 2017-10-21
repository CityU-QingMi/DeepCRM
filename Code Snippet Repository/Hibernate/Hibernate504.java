	@Deprecated
	@SuppressWarnings("")
	default T flushBeforeCompletion(boolean flushBeforeCompletion) {
		if ( flushBeforeCompletion ) {
			flushMode( FlushMode.ALWAYS );
		}
		else {
			flushMode( FlushMode.MANUAL );
		}
		return (T) this;
	}
