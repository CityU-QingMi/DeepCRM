	@Test
	void assertArrayEqualsDifferentByteArraysAndMessage() {
		try {
			assertArrayEquals(new byte[] { 1, 2, 3, 4, 5 }, new byte[] { 1, 2, 3, 5, 5 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3], expected: <4> but was: <5>");
		}
	}
