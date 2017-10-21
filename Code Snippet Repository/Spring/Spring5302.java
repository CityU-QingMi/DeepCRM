	@Test
	public void writeAndRead() {

		DataBuffer buffer = createDataBuffer(5);
		buffer.write(new byte[]{'a', 'b', 'c'});

		int ch = buffer.read();
		assertEquals('a', ch);

		buffer.write((byte) 'd');
		buffer.write((byte) 'e');

		byte[] result = new byte[4];
		buffer.read(result);

		assertArrayEquals(new byte[]{'b', 'c', 'd', 'e'}, result);

		release(buffer);
	}
