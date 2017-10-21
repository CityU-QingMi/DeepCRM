	@Test
	public void getInputStreamReadAll() throws Exception {
		this.os.write(this.helloBytes);
		InputStream inputStream = this.os.getInputStream();
		byte[] actual = new byte[inputStream.available()];
		int bytesRead = inputStream.read(actual);
		assertEquals(this.helloBytes.length, bytesRead);
		assertArrayEquals(this.helloBytes, actual);
		assertEquals(0, inputStream.available());
	}
