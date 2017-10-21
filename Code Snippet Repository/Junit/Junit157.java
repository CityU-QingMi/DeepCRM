	@Test
	void assertArrayEqualsDifferentShortArrays() {
		try {
			assertArrayEquals(new short[] { 10, 100, 1000, 10000 }, new short[] { 1, 10, 100, 1000 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [0], expected: <10> but was: <1>");
		}
	}
