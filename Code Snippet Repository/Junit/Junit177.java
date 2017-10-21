	@Test
	void assertArrayEqualsDifferentLongArrays() {
		try {
			assertArrayEquals(new long[] { Long.MIN_VALUE, 17, 18L, 19 }, new long[] { Long.MIN_VALUE, 17, 18, 20 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3], expected: <19> but was: <20>");
		}
	}
