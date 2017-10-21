	public void testConditionalExpressionSync(CacheableService<?> service) throws Exception {
		Object r1 = service.conditionalSync(4);
		Object r2 = service.conditionalSync(4);

		assertNotSame(r1, r2);

		Object r3 = service.conditionalSync(3);
		Object r4 = service.conditionalSync(3);

		assertSame(r3, r4);
	}
