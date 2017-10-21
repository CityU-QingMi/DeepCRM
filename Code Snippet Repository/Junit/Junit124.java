	@Test
	void assertArrayEqualsNestedObjectArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(//
				new Object[] { "a", new Object[] { 1, 2, 3, new double[] { 4.0, 5.1, 6.1 }, 7 } }, //
				new Object[] { "a", new Object[] { 1, 2, 3, new double[] { 4.0, 5.1, 6.1, 7.0 }, 8 } }, //
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ at index [1][3], expected: <3> but was: <4>");
		}
	}
