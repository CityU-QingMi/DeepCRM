	@Test
	public void testRefreshableFromTagProxyTargetClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("groovy-with-xsd-proxy-target-class.xml",
				getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(Messenger.class)).contains("refreshableMessenger"));

		Messenger messenger = (Messenger) ctx.getBean("refreshableMessenger");

		assertTrue(AopUtils.isAopProxy(messenger));
		assertTrue(messenger instanceof Refreshable);
		assertEquals("Hello World!", messenger.getMessage());

		assertTrue(ctx.getBeansOfType(ConcreteMessenger.class).values().contains(messenger));

		// Check that AnnotationUtils works with concrete proxied script classes
		assertNotNull(AnnotationUtils.findAnnotation(messenger.getClass(), Component.class));
	}
