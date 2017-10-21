	@Test
	public void testFooService() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(getConfigLocations(), getClass());

		FooService fooService = (FooService) ctx.getBean("fooServiceImpl");
		ServiceInvocationCounter serviceInvocationCounter = (ServiceInvocationCounter) ctx.getBean("serviceInvocationCounter");

		assertEquals(0, serviceInvocationCounter.getCount());

		assertTrue(fooService.isInitCalled());
		assertEquals(1, serviceInvocationCounter.getCount());

		String value = fooService.foo(1);
		assertEquals("bar", value);
		assertEquals(2, serviceInvocationCounter.getCount());

		fooService.foo(1);
		assertEquals(3, serviceInvocationCounter.getCount());
	}
