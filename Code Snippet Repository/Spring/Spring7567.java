	@Test
	public void twoMessagesInOneChunk() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		String chunk = "SEND\na:alpha\n\nPayload1\0" + "SEND\na:alpha\n\nPayload2\0";
		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk));

		assertEquals(2, messages.size());
		assertEquals("Payload1", new String(messages.get(0).getPayload()));
		assertEquals("Payload2", new String(messages.get(1).getPayload()));

		assertEquals(0, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());
	}
