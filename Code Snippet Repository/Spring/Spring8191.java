	@SuppressWarnings("")
	protected Set<Class<? extends TestExecutionListener>> getDefaultTestExecutionListenerClasses() {
		Set<Class<? extends TestExecutionListener>> defaultListenerClasses = new LinkedHashSet<>();
		ClassLoader cl = getClass().getClassLoader();
		for (String className : getDefaultTestExecutionListenerClassNames()) {
			try {
				defaultListenerClasses.add((Class<? extends TestExecutionListener>) ClassUtils.forName(className, cl));
			}
			catch (Throwable ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Could not load default TestExecutionListener class [" + className +
							"]. Specify custom listener classes or make the default listener classes available.", ex);
				}
			}
		}
		return defaultListenerClasses;
	}
