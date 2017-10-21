	@Test
	public void slice() {
		DataBuffer buffer = createDataBuffer(3);
		buffer.write(new byte[]{'a', 'b'});

		DataBuffer slice = buffer.slice(1, 2);
		assertEquals(2, slice.readableByteCount());
		try {
			slice.write((byte) 0);
			fail("IndexOutOfBoundsException expected");
		}
		catch (Exception ignored) {
		}
		buffer.write((byte) 'c');

		assertEquals(3, buffer.readableByteCount());
		byte[] result = new byte[3];
		buffer.read(result);

		assertArrayEquals(new byte[]{'a', 'b', 'c'}, result);

		assertEquals(2, slice.readableByteCount());
		result = new byte[2];
		slice.read(result);

		assertArrayEquals(new byte[]{'b', 'c'}, result);


		release(buffer);
	}
