	@Test
	public void oneFullAndOneSplitMessageContentLength() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		int contentLength = "Payload2a-Payload2b".getBytes().length;
		String chunk1 = "SEND\na:alpha\n\nPayload1\0SEND\ncontent-length:" + contentLength + "\n";
		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk1));

		assertEquals(1, messages.size());
		assertEquals("Payload1", new String(messages.get(0).getPayload()));

		assertEquals(23, stompDecoder.getBufferSize());
		assertEquals(contentLength, (int) stompDecoder.getExpectedContentLength());

		String chunk2 = "\nPayload2a";
		messages = stompDecoder.decode(toByteBuffer(chunk2));

		assertEquals(0, messages.size());
		assertEquals(33, stompDecoder.getBufferSize());
		assertEquals(contentLength, (int) stompDecoder.getExpectedContentLength());

		String chunk3 = "-Payload2b\0";
		messages = stompDecoder.decode(toByteBuffer(chunk3));

		assertEquals(1, messages.size());
		assertEquals("Payload2a-Payload2b", new String(messages.get(0).getPayload()));
		assertEquals(0, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());
	}
