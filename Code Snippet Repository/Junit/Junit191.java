	@Test
	void assertArrayEqualsDeltaFloatArrays() {
		assertArrayEquals(new float[] {}, new float[] {}, 0.001F);
		assertArrayEquals(new float[] { Float.MAX_VALUE }, new float[] { Float.MAX_VALUE }, 0.0001F);
		assertArrayEquals(new float[] { Float.MIN_VALUE, 2.111F, 2.521F, 1.01F },
			new float[] { Float.MIN_VALUE, 2.119F, 2.523F, 1.01001F }, 0.01F);

		assertArrayEquals(new float[] { Float.NaN }, new float[] { Float.NaN }, 0.1F);
		assertArrayEquals(new float[] { 10.18F, Float.NaN, 42.9F }, new float[] { 10.98F, Float.NaN, 43.9F }, 1F);
	}
