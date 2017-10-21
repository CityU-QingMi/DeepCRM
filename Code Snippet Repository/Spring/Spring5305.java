	@Test
	public void inputStream() throws IOException {
		byte[] data = new byte[]{'a', 'b', 'c', 'd', 'e'};

		DataBuffer buffer = createDataBuffer(4);
		buffer.write(data);

		buffer.read(); // readIndex++

		InputStream inputStream = buffer.asInputStream();

		int available = inputStream.available();
		assertEquals(4, available);

		int result = inputStream.read();
		assertEquals('b', result);

		available = inputStream.available();
		assertEquals(3, available);

		byte[] bytes = new byte[2];
		int len = inputStream.read(bytes);
		assertEquals(2, len);
		assertArrayEquals(new byte[]{'c', 'd'}, bytes);

		Arrays.fill(bytes, (byte) 0);
		len = inputStream.read(bytes);
		assertEquals(1, len);
		assertArrayEquals(new byte[]{'e', (byte) 0}, bytes);

		release(buffer);
	}
