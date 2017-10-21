	public static SimpleNamingContextBuilder emptyActivatedContextBuilder() throws NamingException {
		SimpleNamingContextBuilder builder = activated;
		if (builder != null) {
			// Clear already activated context builder.
			builder.clear();
		}
		else {
			// Create and activate new context builder.
			builder = new SimpleNamingContextBuilder();
			// The activate() call will cause an assignment to the activated variable.
			builder.activate();
		}
		return builder;
	}
