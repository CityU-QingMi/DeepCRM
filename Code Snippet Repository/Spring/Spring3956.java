	@Test
	public void testJavaBean() {
		context = new GenericXmlApplicationContext(getClass(), getClass().getSimpleName()+"-java-context.xml");
		TestService bean = context.getBean("javaBean", TestService.class);
		LogUserAdvice logAdvice = context.getBean(LogUserAdvice.class);

		assertEquals(0, logAdvice.getCountThrows());
		try {
			bean.sayHello();
			fail("Expected exception");
		}
		catch (RuntimeException ex) {
			assertEquals("TestServiceImpl", ex.getMessage());
		}
		assertEquals(1, logAdvice.getCountThrows());

	}
