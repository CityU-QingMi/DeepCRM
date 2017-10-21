	@Override
	public InitialContextFactory createInitialContextFactory(Hashtable<?,?> environment) {
		if (activated == null && environment != null) {
			Object icf = environment.get(Context.INITIAL_CONTEXT_FACTORY);
			if (icf != null) {
				Class<?> icfClass;
				if (icf instanceof Class) {
					icfClass = (Class<?>) icf;
				}
				else if (icf instanceof String) {
					icfClass = ClassUtils.resolveClassName((String) icf, getClass().getClassLoader());
				}
				else {
					throw new IllegalArgumentException("Invalid value type for environment key [" +
							Context.INITIAL_CONTEXT_FACTORY + "]: " + icf.getClass().getName());
				}
				if (!InitialContextFactory.class.isAssignableFrom(icfClass)) {
					throw new IllegalArgumentException(
							"Specified class does not implement [" + InitialContextFactory.class.getName() + "]: " + icf);
				}
				try {
					return (InitialContextFactory) icfClass.newInstance();
				}
				catch (Throwable ex) {
					throw new IllegalStateException("Cannot instantiate specified InitialContextFactory: " + icf, ex);
				}
			}
		}

		// Default case...
		return new InitialContextFactory() {
			@Override
			@SuppressWarnings("unchecked")
			public Context getInitialContext(Hashtable<?,?> environment) {
				return new SimpleNamingContext("", boundObjects, (Hashtable<String, Object>) environment);
			}
		};
	}
