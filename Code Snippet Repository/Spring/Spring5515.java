	@Test
	public void nonClosingInputStream() throws Exception {
		InputStream source = mock(InputStream.class);
		InputStream nonClosing = StreamUtils.nonClosing(source);
		nonClosing.read();
		nonClosing.read(bytes);
		nonClosing.read(bytes, 1, 2);
		nonClosing.close();
		InOrder ordered = inOrder(source);
		ordered.verify(source).read();
		ordered.verify(source).read(bytes, 0, bytes.length);
		ordered.verify(source).read(bytes, 1, 2);
		ordered.verify(source, never()).close();
	}
