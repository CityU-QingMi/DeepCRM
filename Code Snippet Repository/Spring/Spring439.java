	@Override
	public void testMultiEvict(CacheableService<?> service) {
		Object o1 = new Object();

		Object r1 = service.multiCache(o1);
		Object r2 = service.multiCache(o1);

		Cache primary = cm.getCache("primary");
		Cache secondary = cm.getCache("secondary");

		assertSame(r1, r2);
		assertSame(r1, primary.get(o1).get());
		assertSame(r1, secondary.get(o1).get());

		service.multiEvict(o1);
		assertNull(primary.get(o1));
		assertNull(secondary.get(o1));

		Object r3 = service.multiCache(o1);
		Object r4 = service.multiCache(o1);
		assertNotSame(r1, r3);
		assertSame(r3, r4);

		assertSame(r3, primary.get(o1).get());
		assertSame(r4, secondary.get(o1).get());
	}
