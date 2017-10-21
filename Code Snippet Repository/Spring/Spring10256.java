	@Test
	public void errorAfterMultipleItems() throws Exception {
		IllegalStateException error = new IllegalStateException("boo");
		Flux<String> publisher = Flux.generate(() -> 0, (idx , subscriber) -> {
			int i = ++idx;
			subscriber.next(String.valueOf(i));
			if (i == 3) {
				subscriber.error(error);
			}
			return i;
		});
		Mono<Void> completion = publisher.as(this::sendOperator);
		Signal<Void> signal = completion.materialize().block();

		assertNotNull(signal);
		assertSame("Unexpected signal: " + signal, error, signal.getThrowable());

		assertEquals(3, this.writer.items.size());
		assertEquals("1", this.writer.items.get(0));
		assertEquals("2", this.writer.items.get(1));
		assertEquals("3", this.writer.items.get(2));
		assertSame(error, this.writer.error);
	}
