	@Test
	public void indexOf() {
		DataBuffer buffer = createDataBuffer(3);
		buffer.write(new byte[]{'a', 'b', 'c'});

		int result = buffer.indexOf(b -> b == 'c', 0);
		assertEquals(2, result);

		result = buffer.indexOf(b -> b == 'c', Integer.MIN_VALUE);
		assertEquals(2, result);

		result = buffer.indexOf(b -> b == 'c', Integer.MAX_VALUE);
		assertEquals(-1, result);

		result = buffer.indexOf(b -> b == 'z', 0);
		assertEquals(-1, result);

		release(buffer);
	}
