	@Test
	public void testAddingConstructorResolvers() {
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		// reflective constructor accessor is the only one by default
		List<ConstructorResolver> constructorResolvers = ctx.getConstructorResolvers();
		assertEquals(1, constructorResolvers.size());

		ConstructorResolver dummy = new DummyConstructorResolver();
		ctx.addConstructorResolver(dummy);
		assertEquals(2, ctx.getConstructorResolvers().size());

		List<ConstructorResolver> copy = new ArrayList<>();
		copy.addAll(ctx.getConstructorResolvers());
		assertTrue(ctx.removeConstructorResolver(dummy));
		assertFalse(ctx.removeConstructorResolver(dummy));
		assertEquals(1, ctx.getConstructorResolvers().size());

		ctx.setConstructorResolvers(copy);
		assertEquals(2, ctx.getConstructorResolvers().size());
	}
