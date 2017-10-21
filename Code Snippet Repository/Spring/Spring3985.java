	@Test
	public void testStaticScriptWithInlineDefinedInstanceUsingJsr223() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("groovyContextWithJsr223.xml", getClass());
		assertTrue(Arrays.asList(ctx.getBeanNamesForType(Messenger.class)).contains("messengerInstanceInline"));
		Messenger messenger = (Messenger) ctx.getBean("messengerInstanceInline");

		assertFalse("Shouldn't get proxy when refresh is disabled", AopUtils.isAopProxy(messenger));
		assertFalse("Scripted object should not be instance of Refreshable", messenger instanceof Refreshable);

		String desiredMessage = "Hello World!";
		assertEquals("Message is incorrect", desiredMessage, messenger.getMessage());
		assertTrue(ctx.getBeansOfType(Messenger.class).values().contains(messenger));
	}
