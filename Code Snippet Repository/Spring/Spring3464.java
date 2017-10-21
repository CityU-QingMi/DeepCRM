	@Test
	public void nullArgument() {
		Method method = ReflectionUtils.findMethod(SampleMethods.class, "hello", String.class, Boolean.class);
		MethodBasedEvaluationContext context = createEvaluationContext(method, null, null);

		assertNull(context.lookupVariable("a0"));
		assertNull(context.lookupVariable("p0"));
		assertNull(context.lookupVariable("foo"));

		assertNull(context.lookupVariable("a1"));
		assertNull(context.lookupVariable("p1"));
		assertNull(context.lookupVariable("flag"));
	}
