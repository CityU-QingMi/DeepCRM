	@Test
	void assertArrayEqualsShortArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new short[] { 150, 151 }, new short[] { 150, 151, 152 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}
