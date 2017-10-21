	public void handleNonstopCacheException(NonStopCacheException nonStopCacheException) {
		if ( Boolean.getBoolean( HIBERNATE_THROW_EXCEPTION_ON_TIMEOUT_PROPERTY ) ) {
			throw nonStopCacheException;
		}
		else {
			if ( Boolean.getBoolean( HIBERNATE_LOG_EXCEPTION_STACK_TRACE_PROPERTY ) ) {
				LOG.debug(
						"Ignoring NonstopCacheException - " + nonStopCacheException.getMessage(),
						nonStopCacheException
				);
			}
			else {
				LOG.debug( "Ignoring NonstopCacheException - " + nonStopCacheException.getMessage() );
			}
		}
	}
