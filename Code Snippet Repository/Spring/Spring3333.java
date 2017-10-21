	@Test
	public void testFooService() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(getConfigLocations(), getClass());

		FooService fooService = ctx.getBean("fooServiceImpl", FooService.class);
		ServiceInvocationCounter serviceInvocationCounter = ctx.getBean("serviceInvocationCounter", ServiceInvocationCounter.class);

		String value = fooService.foo(1);
		assertEquals("bar", value);

		Future<?> future = fooService.asyncFoo(1);
		assertTrue(future instanceof FutureTask);
		assertEquals("bar", future.get());

		assertEquals(2, serviceInvocationCounter.getCount());

		fooService.foo(1);
		assertEquals(3, serviceInvocationCounter.getCount());
	}
