	@Test
	void failUsableAsAnExpression() {
		// @formatter:off
		long count = Stream.empty()
				.peek(element -> fail("peek should never be called"))
				.filter(element -> fail("filter should never be called", new Throwable("cause")))
				.map(element -> fail(new Throwable("map should never be called")))
				.sorted((e1, e2) -> fail(() -> "sorted should never be called"))
				.count();
		// @formatter:on
		assertEquals(0L, count);
	}
