	@Test
	public void varArgMultiple() {
		Method method = ReflectionUtils.findMethod(SampleMethods.class, "hello", Boolean.class, String[].class);
		MethodBasedEvaluationContext context = createEvaluationContext(method, null, "hello", "hi");

		assertNull(context.lookupVariable("a0"));
		assertNull(context.lookupVariable("p0"));
		assertNull(context.lookupVariable("flag"));

		assertArrayEquals(new Object[] {"hello", "hi"}, (Object[]) context.lookupVariable("a1"));
		assertArrayEquals(new Object[] {"hello", "hi"}, (Object[]) context.lookupVariable("p1"));
		assertArrayEquals(new Object[] {"hello", "hi"}, (Object[]) context.lookupVariable("vararg"));
	}
