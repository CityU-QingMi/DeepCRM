	@Test
	void assertArrayEqualsWithNulls() {
		assertArrayEquals((boolean[]) null, (boolean[]) null);
		assertArrayEquals((char[]) null, (char[]) null);
		assertArrayEquals((byte[]) null, (byte[]) null);
		assertArrayEquals((int[]) null, (int[]) null);
		assertArrayEquals((long[]) null, (long[]) null);
		assertArrayEquals((float[]) null, (float[]) null);
		assertArrayEquals((double[]) null, (double[]) null);
		assertArrayEquals((Object[]) null, (Object[]) null);
	}
