	@Test
	public void completionBeforeFirstItem() throws Exception {
		Mono<Void> completion = Flux.<String>empty().as(this::sendOperator);
		Signal<Void> signal = completion.materialize().block();

		assertNotNull(signal);
		assertTrue("Unexpected signal: " + signal, signal.isOnComplete());

		assertEquals(0, this.writer.items.size());
		assertTrue(this.writer.completed);
	}
