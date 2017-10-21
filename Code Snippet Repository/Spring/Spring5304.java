	@Test
	public void growDataBuffer() {
		DataBuffer buffer = stringBuffer("Hello World!");

		byte[] bytes = new byte[5];
		buffer.read(bytes);
		assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), bytes);

		buffer.write("!!".getBytes(StandardCharsets.UTF_8));

		bytes = new byte[9];
		buffer.read(bytes);
		assertArrayEquals(" World!!!".getBytes(StandardCharsets.UTF_8), bytes);

		release(buffer);
	}
