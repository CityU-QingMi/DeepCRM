	public static SimpleNamingContextBuilder emptyActivatedContextBuilder() throws NamingException {
		if (activated != null) {
			// Clear already activated context builder.
			activated.clear();
		}
		else {
			// Create and activate new context builder.
			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
			// The activate() call will cause an assignment to the activated variable.
			builder.activate();
		}
		return activated;
	}
