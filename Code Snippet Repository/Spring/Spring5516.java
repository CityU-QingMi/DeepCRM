	@Test
	public void nonClosingOutputStream() throws Exception {
		OutputStream source = mock(OutputStream.class);
		OutputStream nonClosing = StreamUtils.nonClosing(source);
		nonClosing.write(1);
		nonClosing.write(bytes);
		nonClosing.write(bytes, 1, 2);
		nonClosing.close();
		InOrder ordered = inOrder(source);
		ordered.verify(source).write(1);
		ordered.verify(source).write(bytes, 0, bytes.length);
		ordered.verify(source).write(bytes, 1, 2);
		ordered.verify(source, never()).close();
	}
