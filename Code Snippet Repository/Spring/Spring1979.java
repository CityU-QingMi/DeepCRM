	@Test
	public void getFiltered() {
		this.keyGenerator.expect(1L);
		Object first = this.simpleService.getFiltered(1L, "foo", "bar");
		Object second = this.simpleService.getFiltered(1L, "foo", "bar");
		assertSame(first, second);

		Object key = new SimpleKey(1L);
		assertEquals(first, cache.get(key).get());
	}
