	@Test
	public void expand() {
		DataBuffer buffer = createDataBuffer(1);
		buffer.write((byte) 'a');
		buffer.write((byte) 'b');

		byte[] result = new byte[2];
		buffer.read(result);
		assertArrayEquals(new byte[]{'a', 'b'}, result);

		buffer.write(new byte[]{'c', 'd'});

		result = new byte[2];
		buffer.read(result);
		assertArrayEquals(new byte[]{'c', 'd'}, result);

		release(buffer);
	}
