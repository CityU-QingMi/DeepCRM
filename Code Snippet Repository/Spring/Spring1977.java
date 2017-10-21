	@Test
	public void getSimple() {
		this.keyGenerator.expect(1L);
		Object first = this.simpleService.get(1L);
		Object second = this.simpleService.get(1L);
		assertSame(first, second);

		Object key = new SimpleKey(1L);
		assertEquals(first, cache.get(key).get());
	}
