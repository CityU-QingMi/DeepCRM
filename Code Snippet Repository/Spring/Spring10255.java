	@Test
	public void writeMultipleItems() throws Exception {
		List<String> items = Arrays.asList("one", "two", "three");
		Mono<Void> completion = Flux.fromIterable(items).as(this::sendOperator);
		Signal<Void> signal = completion.materialize().block();

		assertNotNull(signal);
		assertTrue("Unexpected signal: " + signal, signal.isOnComplete());

		assertEquals(3, this.writer.items.size());
		assertEquals("one", this.writer.items.get(0));
		assertEquals("two", this.writer.items.get(1));
		assertEquals("three", this.writer.items.get(2));
		assertTrue(this.writer.completed);
	}
