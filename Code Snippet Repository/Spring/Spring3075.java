	public void testMultiEvict(CacheableService<?> service) {
		Object o1 = new Object();
		Object o2 = o1.toString() + "A";


		Object r1 = service.multiCache(o1);
		Object r2 = service.multiCache(o1);

		Cache primary = this.cm.getCache("primary");
		Cache secondary = this.cm.getCache("secondary");

		primary.put(o2, o2);
		assertSame(r1, r2);
		assertSame(r1, primary.get(o1).get());
		assertSame(r1, secondary.get(o1).get());

		service.multiEvict(o1);
		assertNull(primary.get(o1));
		assertNull(secondary.get(o1));
		assertNull(primary.get(o2));

		Object r3 = service.multiCache(o1);
		Object r4 = service.multiCache(o1);
		assertNotSame(r1, r3);
		assertSame(r3, r4);

		assertSame(r3, primary.get(o1).get());
		assertSame(r4, secondary.get(o1).get());
	}
