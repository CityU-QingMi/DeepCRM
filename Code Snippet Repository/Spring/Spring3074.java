	public void testMultiCache(CacheableService<?> service) {
		Object o1 = new Object();
		Object o2 = new Object();

		Cache primary = this.cm.getCache("primary");
		Cache secondary = this.cm.getCache("secondary");

		assertNull(primary.get(o1));
		assertNull(secondary.get(o1));
		Object r1 = service.multiCache(o1);
		assertSame(r1, primary.get(o1).get());
		assertSame(r1, secondary.get(o1).get());

		Object r2 = service.multiCache(o1);
		Object r3 = service.multiCache(o1);

		assertSame(r1, r2);
		assertSame(r1, r3);

		assertNull(primary.get(o2));
		assertNull(secondary.get(o2));
		Object r4 = service.multiCache(o2);
		assertSame(r4, primary.get(o2).get());
		assertSame(r4, secondary.get(o2).get());
	}
