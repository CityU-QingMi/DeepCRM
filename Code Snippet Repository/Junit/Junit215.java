	@Test
	void assertArrayEqualsDeltaDoubleArraysThrowsForIllegalDelta() {
		try {
			assertArrayEquals(new double[] {}, new double[] {}, -0.5F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <-0.5>");
		}
		try {
			assertArrayEquals(new double[] {}, new double[] {}, Float.NaN);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <NaN>");
		}

		try {
			assertArrayEquals(new double[] { 1.2, 1.3, 10 }, new double[] { 1.2, 1.3, 10 }, -0.5F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <-0.5>");
		}
		try {
			assertArrayEquals(new double[] { 0.1, 10 }, new double[] { 0.1, 10, 11 }, Float.NaN);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <NaN>");
		}
	}
