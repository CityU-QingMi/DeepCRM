	@Test
	public void retainAndRelease() {
		PooledDataBuffer buffer = createDataBuffer(1);
		buffer.write((byte) 'a');

		buffer.retain();
		boolean result = buffer.release();
		assertFalse(result);
		result = buffer.release();
		assertTrue(result);
	}
