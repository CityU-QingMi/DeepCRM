	@Test
	public void closeFrameEmpty() {
		SockJsFrame frame = new SockJsFrame("c");

		assertEquals("c[]", frame.getContent());
		assertEquals(SockJsFrameType.CLOSE, frame.getType());
		assertEquals("[]", frame.getFrameData());

		frame = new SockJsFrame("c[]");

		assertEquals("c[]", frame.getContent());
		assertEquals(SockJsFrameType.CLOSE, frame.getType());
		assertEquals("[]", frame.getFrameData());
	}
