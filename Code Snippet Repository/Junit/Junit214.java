	@Test
	void assertArrayEqualsDeltaDoubleArrays() {
		assertArrayEquals(new double[] {}, new double[] {}, 0.5);
		assertArrayEquals(new double[] { Double.MAX_VALUE, 0.1 }, new double[] { Double.MAX_VALUE, 0.2 }, 0.2);
		assertArrayEquals(new double[] { Double.MIN_VALUE, 3.1, 1.3, 2.7 },
			new double[] { Double.MIN_VALUE, 3.4, 1.7, 2.4 }, 0.5);

		assertArrayEquals(new double[] { Double.NaN }, new double[] { Double.NaN }, 0.01);
		assertArrayEquals(new double[] { 1.2, 1.8, Double.NaN, 4.9 }, new double[] { 1.25, 1.7, Double.NaN, 4.8 }, 0.2);
	}
