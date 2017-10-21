	@Test
	public void writeOneItem() throws Exception {
		Mono<Void> completion = Flux.just("one").as(this::sendOperator);
		Signal<Void> signal = completion.materialize().block();

		assertNotNull(signal);
		assertTrue("Unexpected signal: " + signal, signal.isOnComplete());

		assertEquals(1, this.writer.items.size());
		assertEquals("one", this.writer.items.get(0));
		assertTrue(this.writer.completed);
	}
