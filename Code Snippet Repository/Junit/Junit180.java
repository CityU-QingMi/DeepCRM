	@Test
	void assertArrayEqualsFloatArrays() {
		assertArrayEquals(new float[] {}, new float[] {});
		assertArrayEquals(new float[] { Float.MAX_VALUE }, new float[] { Float.MAX_VALUE });
		assertArrayEquals(new float[] { Float.MIN_VALUE, 5F, 5.5F, 1.00F },
			new float[] { Float.MIN_VALUE, 5F, 5.5F, 1.00F });

		assertArrayEquals(new float[] { Float.NaN }, new float[] { Float.NaN });
		assertArrayEquals(new float[] { 10.18F, Float.NaN, 42.9F }, new float[] { 10.18F, Float.NaN, 42.9F });
	}
