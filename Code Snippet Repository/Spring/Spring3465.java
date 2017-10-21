	@Test
	public void varArgEmpty() {
		Method method = ReflectionUtils.findMethod(SampleMethods.class, "hello", Boolean.class, String[].class);
		MethodBasedEvaluationContext context = createEvaluationContext(method, new Object[] {null});

		assertNull(context.lookupVariable("a0"));
		assertNull(context.lookupVariable("p0"));
		assertNull(context.lookupVariable("flag"));

		assertNull(context.lookupVariable("a1"));
		assertNull(context.lookupVariable("p1"));
		assertNull(context.lookupVariable("vararg"));
	}
