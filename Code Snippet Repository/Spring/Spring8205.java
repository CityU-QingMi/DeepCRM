	public ApplicationContext getApplicationContext() {
		ApplicationContext context = this.cacheAwareContextLoaderDelegate.loadContext(this.mergedContextConfiguration);
		if (context instanceof ConfigurableApplicationContext) {
			@SuppressWarnings("resource")
			ConfigurableApplicationContext cac = (ConfigurableApplicationContext) context;
			Assert.state(cac.isActive(), () ->
					"The ApplicationContext loaded for [" + mergedContextConfiguration +
					"] is not active. This may be due to one of the following reasons: " +
					"1) the context was closed programmatically by user code; " +
					"2) the context was closed during parallel test execution either " +
					"according to @DirtiesContext semantics or due to automatic eviction " +
					"from the ContextCache due to a maximum cache size policy.");
		}
		return context;
	}
