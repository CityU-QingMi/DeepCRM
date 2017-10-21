	@Test
	public void oneMessageInTwoChunks() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		String chunk1 = "SEND\na:alpha\n\nMessage";
		String chunk2 = " body\0";

		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk1));
		assertEquals(Collections.<Message<byte[]>>emptyList(), messages);

		messages = stompDecoder.decode(toByteBuffer(chunk2));
		assertEquals(1, messages.size());
		assertEquals("Message body", new String(messages.get(0).getPayload()));

		assertEquals(0, stompDecoder.getBufferSize());
		assertNull(stompDecoder.getExpectedContentLength());
	}
