	@Test
	void assertArrayEqualsObjectArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new Object[] { "a", "b", "c" }, new Object[] { "a", "b", "c", "d", "e", "f" },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <6>");
		}
	}
