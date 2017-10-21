	@Test
	public void resourceScriptFromTag() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bsh-with-xsd.xml", getClass());
		TestBean testBean = (TestBean) ctx.getBean("testBean");

		Collection<String> beanNames = Arrays.asList(ctx.getBeanNamesForType(Messenger.class));
		assertTrue(beanNames.contains("messenger"));
		assertTrue(beanNames.contains("messengerImpl"));
		assertTrue(beanNames.contains("messengerInstance"));

		Messenger messenger = (Messenger) ctx.getBean("messenger");
		assertEquals("Hello World!", messenger.getMessage());
		assertFalse(messenger instanceof Refreshable);

		Messenger messengerImpl = (Messenger) ctx.getBean("messengerImpl");
		assertEquals("Hello World!", messengerImpl.getMessage());

		Messenger messengerInstance = (Messenger) ctx.getBean("messengerInstance");
		assertEquals("Hello World!", messengerInstance.getMessage());

		TestBeanAwareMessenger messengerByType = (TestBeanAwareMessenger) ctx.getBean("messengerByType");
		assertEquals(testBean, messengerByType.getTestBean());

		TestBeanAwareMessenger messengerByName = (TestBeanAwareMessenger) ctx.getBean("messengerByName");
		assertEquals(testBean, messengerByName.getTestBean());

		Collection<Messenger> beans = ctx.getBeansOfType(Messenger.class).values();
		assertTrue(beans.contains(messenger));
		assertTrue(beans.contains(messengerImpl));
		assertTrue(beans.contains(messengerInstance));
		assertTrue(beans.contains(messengerByType));
		assertTrue(beans.contains(messengerByName));

		ctx.close();
		assertNull(messenger.getMessage());
		assertNull(messengerImpl.getMessage());
		assertNull(messengerInstance.getMessage());
	}
