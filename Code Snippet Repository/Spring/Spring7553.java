	@Test
	public void customPathMatcher() {
		SimpleBrokerMessageHandler broker = this.customContext.getBean(SimpleBrokerMessageHandler.class);
		DefaultSubscriptionRegistry registry = (DefaultSubscriptionRegistry) broker.getSubscriptionRegistry();
		assertEquals("a.a", registry.getPathMatcher().combine("a", "a"));

		SimpAnnotationMethodMessageHandler handler = this.customContext.getBean(SimpAnnotationMethodMessageHandler.class);
		assertEquals("a.a", handler.getPathMatcher().combine("a", "a"));

		DefaultUserDestinationResolver resolver = this.customContext.getBean(DefaultUserDestinationResolver.class);
		assertNotNull(resolver);
		assertEquals(false, new DirectFieldAccessor(resolver).getPropertyValue("keepLeadingSlash"));
	}
