	@Test
	public void basic() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		String chunk = "SEND\na:alpha\n\nMessage body\0";

		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk));
		assertEquals(1, messages.size());
		assertEquals("Message body", new String(messages.get(0).getPayload()));

		assertEquals(0, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());
	}
