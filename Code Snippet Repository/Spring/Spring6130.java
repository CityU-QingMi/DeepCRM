	@Test
	public void testStandardEvaluationContext() {
		StandardEvaluationContext context = new StandardEvaluationContext();
		assertNotNull(context.getTypeComparator());

		TypeComparator tc = new StandardTypeComparator();
		context.setTypeComparator(tc);
		assertEquals(tc, context.getTypeComparator());

		TypeLocator tl = new StandardTypeLocator();
		context.setTypeLocator(tl);
		assertEquals(tl, context.getTypeLocator());
	}
