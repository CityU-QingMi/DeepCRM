	@Test
	void assertArrayEqualsDifferentObjectArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new Object[] { "one", 1L, Double.MIN_VALUE, "abc" },
				new Object[] { "one", 1L, 42.42, "abc" }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [2], expected: <4.9E-324> but was: <42.42>");
		}
	}
