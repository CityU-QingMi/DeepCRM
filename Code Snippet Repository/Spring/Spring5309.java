	@Test
	public void writeDataBuffer() {
		DataBuffer buffer1 = createDataBuffer(1);
		buffer1.write((byte) 'a');
		DataBuffer buffer2 = createDataBuffer(2);
		buffer2.write((byte) 'b');
		DataBuffer buffer3 = createDataBuffer(3);
		buffer3.write((byte) 'c');

		buffer1.write(buffer2, buffer3);
		buffer1.write((byte) 'd'); // make sure the write index is correctly set

		assertEquals(4, buffer1.readableByteCount());
		byte[] result = new byte[4];
		buffer1.read(result);

		assertArrayEquals(new byte[]{'a', 'b', 'c', 'd'}, result);

		release(buffer1);
	}
