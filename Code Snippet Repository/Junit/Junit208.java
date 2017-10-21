	@Test
	void assertArrayEqualsDoubleArraysOfDifferentLength() {
		try {
			assertArrayEquals(new double[] { Double.MIN_VALUE, 1.0, 2.0, 3.0 },
				new double[] { Double.MAX_VALUE, 1.1, 1.0 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <4> but was: <3>");
		}
	}
