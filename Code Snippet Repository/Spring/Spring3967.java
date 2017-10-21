	@Test
	public void testWithTwoClassesDefinedInTheOneGroovyFile_CorrectClassFirst() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("twoClassesCorrectOneFirst.xml", getClass());
		Messenger messenger = (Messenger) ctx.getBean("messenger");
		assertNotNull(messenger);
		assertEquals("Hello World!", messenger.getMessage());

		// Check can cast to GroovyObject
		GroovyObject goo = (GroovyObject) messenger;
		assertNotNull(goo);
	}
