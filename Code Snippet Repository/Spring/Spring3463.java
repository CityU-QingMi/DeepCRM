	@Test
	public void simpleArguments() {
		Method method = ReflectionUtils.findMethod(SampleMethods.class, "hello", String.class, Boolean.class);
		MethodBasedEvaluationContext context = createEvaluationContext(method, "test", true);

		assertEquals("test", context.lookupVariable("a0"));
		assertEquals("test", context.lookupVariable("p0"));
		assertEquals("test", context.lookupVariable("foo"));

		assertEquals(true, context.lookupVariable("a1"));
		assertEquals(true, context.lookupVariable("p1"));
		assertEquals(true, context.lookupVariable("flag"));

		assertNull(context.lookupVariable("a2"));
		assertNull(context.lookupVariable("p2"));
	}
