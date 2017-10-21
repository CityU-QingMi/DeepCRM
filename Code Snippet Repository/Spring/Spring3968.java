	@Test
	public void testWithTwoClassesDefinedInTheOneGroovyFile_WrongClassFirst() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("twoClassesWrongOneFirst.xml", getClass());
			ctx.getBean("messenger", Messenger.class);
			fail("Must have failed: two classes defined in GroovyScriptFactory source, non-Messenger class defined first.");
		}
		// just testing for failure here, hence catching Exception...
		catch (Exception expected) {
		}
	}
