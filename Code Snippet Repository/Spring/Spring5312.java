	@Test
	public void lastIndexOf() {
		DataBuffer buffer = createDataBuffer(3);
		buffer.write(new byte[]{'a', 'b', 'c'});

		int result = buffer.lastIndexOf(b -> b == 'b', 2);
		assertEquals(1, result);

		result = buffer.lastIndexOf(b -> b == 'c', 2);
		assertEquals(2, result);

		result = buffer.lastIndexOf(b -> b == 'b', Integer.MAX_VALUE);
		assertEquals(1, result);

		result = buffer.lastIndexOf(b -> b == 'c', Integer.MAX_VALUE);
		assertEquals(2, result);

		result = buffer.lastIndexOf(b -> b == 'b', Integer.MIN_VALUE);
		assertEquals(-1, result);

		result = buffer.lastIndexOf(b -> b == 'c', Integer.MIN_VALUE);
		assertEquals(-1, result);

		result = buffer.lastIndexOf(b -> b == 'z', 0);
		assertEquals(-1, result);

		release(buffer);
	}
