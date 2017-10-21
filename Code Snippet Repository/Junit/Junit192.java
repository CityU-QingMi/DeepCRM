	@Test
	void assertArrayEqualsDeltaFloatArraysThrowsForIllegalDelta() {
		try {
			assertArrayEquals(new float[] {}, new float[] {}, -0.5F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <-0.5>");
		}
		try {
			assertArrayEquals(new float[] {}, new float[] {}, Float.NaN);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <NaN>");
		}

		try {
			assertArrayEquals(new float[] { 12.9F, 7F, 13F }, new float[] { 12.9F, 7F, 13F }, -0.5F);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <-0.5>");
		}
		try {
			assertArrayEquals(new float[] { 1.11F, 1.11F, 9F }, new float[] { 1.11F, 1.11F, 9F, 10F }, Float.NaN);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "positive delta expected but was: <NaN>");
		}
	}
