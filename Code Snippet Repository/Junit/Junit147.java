	@Test
	void assertArrayEqualsDifferentByteArrays() {
		try {
			assertArrayEquals(new byte[] { 12, 13, 12, 13 }, new byte[] { 12, 13, 12, 14 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3], expected: <13> but was: <14>");
		}
	}
