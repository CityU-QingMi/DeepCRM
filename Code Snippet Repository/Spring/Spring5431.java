	@Test
	public void getInputStreamReadBeyondEndOfStream() throws Exception {
		this.os.write(this.helloBytes);
		InputStream inputStream = os.getInputStream();
		byte[] actual = new byte[inputStream.available() + 1];
		int bytesRead = inputStream.read(actual);
		assertEquals(this.helloBytes.length, bytesRead);
		for (int i = 0; i < bytesRead; i++) {
			assertEquals(this.helloBytes[i], actual[i]);
		}
		assertEquals(0, actual[this.helloBytes.length]);
		assertEquals(0, inputStream.available());
	}
