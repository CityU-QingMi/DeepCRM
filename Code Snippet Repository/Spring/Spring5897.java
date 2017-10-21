	@Test
	public void testAddingMethodResolvers() {
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		// reflective method accessor is the only one by default
		List<MethodResolver> methodResolvers = ctx.getMethodResolvers();
		assertEquals(1, methodResolvers.size());

		MethodResolver dummy = new DummyMethodResolver();
		ctx.addMethodResolver(dummy);
		assertEquals(2, ctx.getMethodResolvers().size());

		List<MethodResolver> copy = new ArrayList<>();
		copy.addAll(ctx.getMethodResolvers());
		assertTrue(ctx.removeMethodResolver(dummy));
		assertFalse(ctx.removeMethodResolver(dummy));
		assertEquals(1, ctx.getMethodResolvers().size());

		ctx.setMethodResolvers(copy);
		assertEquals(2, ctx.getMethodResolvers().size());
	}
