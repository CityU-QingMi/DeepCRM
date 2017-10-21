	@Test
	public void oneFullAndOneSplitWithContentLengthExceedingBufferSize() throws InterruptedException {
		BufferingStompDecoder stompDecoder = new BufferingStompDecoder(STOMP_DECODER, 128);
		String chunk1 = "SEND\na:alpha\n\nPayload1\0SEND\ncontent-length:129\n";
		List<Message<byte[]>> messages = stompDecoder.decode(toByteBuffer(chunk1));

		assertEquals("We should have gotten the 1st message", 1, messages.size());
		assertEquals("Payload1", new String(messages.get(0).getPayload()));

		assertEquals(24, stompDecoder.getBufferSize());
		assertEquals(129, (int) stompDecoder.getExpectedContentLength());

		try {
			String chunk2 = "\nPayload2a";
			stompDecoder.decode(toByteBuffer(chunk2));
			fail("Expected exception");
		}
		catch (StompConversionException ex) {
			// expected
		}
	}
