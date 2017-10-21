	private static org.apache.log4j.Level translate(final Level level) {
		if ( level == null ) {
			return org.apache.log4j.Level.ALL;
		}

		switch ( level ) {
			case FATAL:
				return org.apache.log4j.Level.FATAL;
			case ERROR:
				return org.apache.log4j.Level.ERROR;
			case WARN:
				return org.apache.log4j.Level.WARN;
			case INFO:
				return org.apache.log4j.Level.INFO;
			case DEBUG:
				return org.apache.log4j.Level.DEBUG;
			case TRACE:
				return org.apache.log4j.Level.TRACE;
		}

		return org.apache.log4j.Level.ALL;
	}
