	@Test
	public void testMessageCodesResolver() {
		loadBeanDefinitions("mvc-config-message-codes-resolver.xml");
		RequestMappingHandlerAdapter adapter = this.appContext.getBean(RequestMappingHandlerAdapter.class);
		assertNotNull(adapter);
		Object initializer = adapter.getWebBindingInitializer();
		assertNotNull(initializer);
		MessageCodesResolver resolver =
				((ConfigurableWebBindingInitializer) initializer).getMessageCodesResolver();
		assertNotNull(resolver);
		assertEquals(TestMessageCodesResolver.class, resolver.getClass());
		assertEquals(false, new DirectFieldAccessor(adapter).getPropertyValue("ignoreDefaultModelOnRedirect"));
	}
