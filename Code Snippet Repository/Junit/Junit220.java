	@Test
	void assertArrayEqualsDeltaDoubleArraysOfDifferentLength() {
		try {
			assertArrayEquals(new double[] { Double.MIN_VALUE, 2.0, 3.0, 4.0 },
				new double[] { Double.MAX_VALUE, 2.1, 3.1 }, 0.001);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <4> but was: <3>");
		}
	}
