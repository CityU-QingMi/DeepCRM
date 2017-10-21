	@Test
	public void outputStream() throws IOException {
		DataBuffer buffer = createDataBuffer(4);
		buffer.write((byte) 'a');

		OutputStream outputStream = buffer.asOutputStream();
		outputStream.write(new byte[]{'b', 'c', 'd'});

		buffer.write((byte) 'e');

		byte[] bytes = new byte[5];
		buffer.read(bytes);
		assertArrayEquals(new byte[]{'a', 'b', 'c', 'd', 'e'}, bytes);

		release(buffer);
	}
