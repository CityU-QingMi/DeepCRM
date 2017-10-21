	@Test
	public void oneFullAndOneSplitMessageNoContentLength() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		String chunk1 = "SEND\na:alpha\n\nPayload1\0SEND\na:alpha\n";
		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk1));

		assertEquals(1, messages.size());
		assertEquals("Payload1", new String(messages.get(0).getPayload()));

		assertEquals(13, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());

		String chunk2 = "\nPayload2a";
		messages = stompDecoder.decode(toByteBuffer(chunk2));

		assertEquals(0, messages.size());
		assertEquals(23, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());

		String chunk3 = "-Payload2b\0";
		messages = stompDecoder.decode(toByteBuffer(chunk3));

		assertEquals(1, messages.size());
		assertEquals("Payload2a-Payload2b", new String(messages.get(0).getPayload()));
		assertEquals(0, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());
	}
