	@Test
	public void testAddingRemovingAccessors() {
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		// reflective property accessor is the only one by default
		List<PropertyAccessor> propertyAccessors = ctx.getPropertyAccessors();
		assertEquals(1,propertyAccessors.size());

		StringyPropertyAccessor spa = new StringyPropertyAccessor();
		ctx.addPropertyAccessor(spa);
		assertEquals(2,ctx.getPropertyAccessors().size());

		List<PropertyAccessor> copy = new ArrayList<>();
		copy.addAll(ctx.getPropertyAccessors());
		assertTrue(ctx.removePropertyAccessor(spa));
		assertFalse(ctx.removePropertyAccessor(spa));
		assertEquals(1,ctx.getPropertyAccessors().size());

		ctx.setPropertyAccessors(copy);
		assertEquals(2,ctx.getPropertyAccessors().size());
	}
