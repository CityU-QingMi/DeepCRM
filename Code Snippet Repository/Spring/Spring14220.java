	@Test
	public void messageArrayFrameEmpty() {
		SockJsFrame frame = new SockJsFrame("a");

		assertEquals("a[]", frame.getContent());
		assertEquals(SockJsFrameType.MESSAGE, frame.getType());
		assertEquals("[]", frame.getFrameData());

		frame = new SockJsFrame("a[]");

		assertEquals("a[]", frame.getContent());
		assertEquals(SockJsFrameType.MESSAGE, frame.getType());
		assertEquals("[]", frame.getFrameData());
	}
