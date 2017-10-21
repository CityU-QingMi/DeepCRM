	@Test
	public void testRefreshableFromTag() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("groovy-with-xsd.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(Messenger.class)).contains("refreshableMessenger"));

		Messenger messenger = (Messenger) ctx.getBean("refreshableMessenger");
		CallCounter countingAspect = (CallCounter) ctx.getBean("getMessageAspect");

		assertTrue(AopUtils.isAopProxy(messenger));
		assertTrue(messenger instanceof Refreshable);
		assertEquals(0, countingAspect.getCalls());
		assertEquals("Hello World!", messenger.getMessage());
		assertEquals(1, countingAspect.getCalls());

		assertTrue(ctx.getBeansOfType(Messenger.class).values().contains(messenger));
	}
