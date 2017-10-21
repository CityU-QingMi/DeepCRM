	@Test
	void assertArrayEqualsDifferentIntArrays() {
		try {
			assertArrayEquals(new int[] { Integer.MIN_VALUE, 1, 2, 10 }, new int[] { Integer.MIN_VALUE, 1, 10, 10 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [2], expected: <2> but was: <10>");
		}
	}
