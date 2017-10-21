	@Test
	public void canWrite() {

		assertTrue(this.messageWriter.canWrite(forClass(Object.class), null));
		assertFalse(this.messageWriter.canWrite(forClass(Object.class), new MediaType("foo", "bar")));

		assertTrue(this.messageWriter.canWrite(null, MediaType.TEXT_EVENT_STREAM));
		assertTrue(this.messageWriter.canWrite(forClass(ServerSentEvent.class), new MediaType("foo", "bar")));

		// SPR-15464
		assertTrue(this.messageWriter.canWrite(ResolvableType.NONE, MediaType.TEXT_EVENT_STREAM));
		assertFalse(this.messageWriter.canWrite(ResolvableType.NONE, new MediaType("foo", "bar")));
	}
