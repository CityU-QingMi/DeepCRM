	@Test
	public void testGroovyBeanDynamic() {
		context = new GenericXmlApplicationContext(getClass(), getClass().getSimpleName()+"-groovy-dynamic-context.xml");
		TestService bean = context.getBean("groovyBean", TestService.class);
		LogUserAdvice logAdvice = context.getBean(LogUserAdvice.class);

		assertEquals(0, logAdvice.getCountThrows());
		try {
			bean.sayHello();
			fail("Expected exception");
		}
		catch (RuntimeException ex) {
			assertEquals("GroovyServiceImpl", ex.getMessage());
		}
		// No proxy here because the pointcut only applies to the concrete class, not the interface
		assertEquals(0, logAdvice.getCountThrows());
		assertEquals(0, logAdvice.getCountBefore());
	}
