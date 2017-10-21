	@Test
	public void registerUnregisterServeralContexts() throws MalformedObjectNameException {
		this.environment.setProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME, this.name.getMethodName());
		ConfigurableApplicationContext context = createApplicationContext("app");
		ConfigurableApplicationContext childContext = createApplicationContext("child");
		assertEquals(0, searchLiveBeansViewMeans().size());
		LiveBeansView.registerApplicationContext(context);
		assertSingleLiveBeansViewMbean("app");
		LiveBeansView.registerApplicationContext(childContext);
		assertEquals(1, searchLiveBeansViewMeans().size()); // Only one MBean
		LiveBeansView.unregisterApplicationContext(childContext);
		assertSingleLiveBeansViewMbean("app"); // Root context removes it
		LiveBeansView.unregisterApplicationContext(context);
		assertEquals(0, searchLiveBeansViewMeans().size());
	}
