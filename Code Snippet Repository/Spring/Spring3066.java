	public void testVarArgsKey(CacheableService<?> service) throws Exception {
		Object r1 = service.varArgsKey(1, 2, 3);
		Object r2 = service.varArgsKey(1, 2, 3);

		assertSame(r1, r2);

		Object r3 = service.varArgsKey(1, 2, 3);
		Object r4 = service.varArgsKey(1, 2);

		assertNotSame(r3, r4);
	}
