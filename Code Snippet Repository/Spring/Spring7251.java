	@Bean
	public UserDestinationResolver userDestinationResolver() {
		DefaultUserDestinationResolver resolver = new DefaultUserDestinationResolver(userRegistry());
		String prefix = getBrokerRegistry().getUserDestinationPrefix();
		if (prefix != null) {
			resolver.setUserDestinationPrefix(prefix);
		}
		resolver.setPathMatcher(getPathMatcher());
		return resolver;
	}
