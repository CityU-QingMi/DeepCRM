	public void testMultiPut(CacheableService<?> service) {
		Object o = 1;

		Cache primary = this.cm.getCache("primary");
		Cache secondary = this.cm.getCache("secondary");

		assertNull(primary.get(o));
		assertNull(secondary.get(o));
		Object r1 = service.multiUpdate(o);
		assertSame(r1, primary.get(o).get());
		assertSame(r1, secondary.get(o).get());

		o = 2;
		assertNull(primary.get(o));
		assertNull(secondary.get(o));
		Object r2 = service.multiUpdate(o);
		assertSame(r2, primary.get(o).get());
		assertSame(r2, secondary.get(o).get());
	}
