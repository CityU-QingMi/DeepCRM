	private static Log4DelegatingLogger convertType(BasicLogger log) {
		if ( log instanceof DelegatingBasicLogger) {
			//Most loggers generated via the annotation processor are of this type
			DelegatingBasicLogger wrapper = (DelegatingBasicLogger) log;
			try {
				return extractFromWrapper( wrapper );
			}
			catch (Exception cause) {
				throw new RuntimeException( cause );
			}
		}
		if ( ! ( log instanceof Log4DelegatingLogger ) ) {
			throw new AssertionFailure( "Unexpected log type: JBoss Logger didn't register the custom TestableLoggerProvider as logger provider" );
		}
		return (Log4DelegatingLogger) log;
	}
