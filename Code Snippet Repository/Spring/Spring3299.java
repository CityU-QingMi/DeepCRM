	private void aspectIsApplied(ApplicationContext ctx) {
		FooService fooService = ctx.getBean(FooService.class);
		ServiceInvocationCounter counter = ctx.getBean(ServiceInvocationCounter.class);

		assertEquals(0, counter.getCount());

		assertTrue(fooService.isInitCalled());
		assertEquals(1, counter.getCount());

		String value = fooService.foo(1);
		assertEquals("bar", value);
		assertEquals(2, counter.getCount());

		fooService.foo(1);
		assertEquals(3, counter.getCount());
	}
