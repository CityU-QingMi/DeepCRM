	@Test
	public void testResourceScriptFromTag() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("groovy-with-xsd.xml", getClass());
		Messenger messenger = (Messenger) ctx.getBean("messenger");
		CallCounter countingAspect = (CallCounter) ctx.getBean("getMessageAspect");

		assertTrue(AopUtils.isAopProxy(messenger));
		assertFalse(messenger instanceof Refreshable);
		assertEquals(0, countingAspect.getCalls());
		assertEquals("Hello World!", messenger.getMessage());
		assertEquals(1, countingAspect.getCalls());

		ctx.close();
		assertEquals(-200, countingAspect.getCalls());
	}
