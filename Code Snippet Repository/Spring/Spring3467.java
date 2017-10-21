	@Test
	public void varArgSingle() {
		Method method = ReflectionUtils.findMethod(SampleMethods.class, "hello", Boolean.class, String[].class);
		MethodBasedEvaluationContext context = createEvaluationContext(method, null, "hello");

		assertNull(context.lookupVariable("a0"));
		assertNull(context.lookupVariable("p0"));
		assertNull(context.lookupVariable("flag"));

		assertEquals("hello", context.lookupVariable("a1"));
		assertEquals("hello", context.lookupVariable("p1"));
		assertEquals("hello", context.lookupVariable("vararg"));
	}
