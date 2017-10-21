	@Test
	public void asByteBuffer() {
		DataBuffer buffer = createDataBuffer(4);
		buffer.write(new byte[]{'a', 'b', 'c'});
		buffer.read(); // skip a

		ByteBuffer result = buffer.asByteBuffer();

		buffer.write((byte) 'd');
		assertEquals(2, result.remaining());
		byte[] resultBytes = new byte[2];
		buffer.read(resultBytes);
		assertArrayEquals(new byte[]{'b', 'c'}, resultBytes);

		release(buffer);
	}
