	@Test
	void assertArrayEqualsDoubleArrays() {
		assertArrayEquals(new double[] {}, new double[] {});
		assertArrayEquals(new double[] { Double.MAX_VALUE }, new double[] { Double.MAX_VALUE });
		assertArrayEquals(new double[] { Double.MIN_VALUE, 2.1, 5.5, 1.0 },
			new double[] { Double.MIN_VALUE, 2.1, 5.5, 1.0 });

		assertArrayEquals(new double[] { Double.NaN }, new double[] { Double.NaN });
		assertArrayEquals(new double[] { 1.2, 10.8, Double.NaN, 42.9 }, new double[] { 1.2, 10.8, Double.NaN, 42.9 });
	}
