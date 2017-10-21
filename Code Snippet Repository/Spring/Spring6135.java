	public static Log getLog(String name) {
		switch (logApi) {
			case LOG4J:
				return Log4jDelegate.createLog(name);
			case SLF4J_LAL:
				return Slf4jDelegate.createLocationAwareLog(name);
			case SLF4J:
				return Slf4jDelegate.createLog(name);
			default:
				// Defensively use lazy-initializing delegate class here as well since the
				// java.logging module is not present by default on JDK 9. We are requiring
				// its presence if neither Log4j nor SLF4J is available; however, in the
				// case of Log4j or SLF4J, we are trying to prevent early initialization
				// of the JavaUtilLog adapter - e.g. by a JVM in debug mode - when eagerly
				// trying to parse the bytecode for all the cases of this switch clause.
				return JavaUtilDelegate.createLog(name);
		}
	}
