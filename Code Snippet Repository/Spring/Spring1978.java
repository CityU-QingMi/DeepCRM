	@Test
	public void getFlattenVararg() {
		this.keyGenerator.expect(1L, "foo", "bar");
		Object first = this.simpleService.get(1L, "foo", "bar");
		Object second = this.simpleService.get(1L, "foo", "bar");
		assertSame(first, second);

		Object key = new SimpleKey(1L, "foo", "bar");
		assertEquals(first, cache.get(key).get());
	}
