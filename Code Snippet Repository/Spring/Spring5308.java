	@Test
	public void writeByteBuffer() {
		DataBuffer buffer1 = createDataBuffer(1);
		buffer1.write((byte) 'a');
		ByteBuffer buffer2 = createByteBuffer(2);
		buffer2.put((byte) 'b');
		buffer2.flip();
		ByteBuffer buffer3 = createByteBuffer(3);
		buffer3.put((byte) 'c');
		buffer3.flip();

		buffer1.write(buffer2, buffer3);
		buffer1.write((byte) 'd'); // make sure the write index is correctly set

		assertEquals(4, buffer1.readableByteCount());
		byte[] result = new byte[4];
		buffer1.read(result);

		assertArrayEquals(new byte[]{'a', 'b', 'c', 'd'}, result);

		release(buffer1);
	}
