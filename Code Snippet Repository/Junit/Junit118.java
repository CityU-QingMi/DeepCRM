	@Test
	void assertArrayEqualsNestedObjectArrayVsNullAndMessageSupplier() {
		try {
			assertArrayEquals(new Object[] { "1", "2", "3", new Object[] { "4", new Object[] { null } } },
				new Object[] { "1", "2", "3", new Object[] { "4", new Object[] { new int[] { 5 } } } },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null> at index [3][1][0]");
		}

		try {
			assertArrayEquals(
				new Object[] { 1, 2, new Object[] { "3", new Object[] { '4', new Object[] { 5, 6, new long[] {} } } } },
				new Object[] { 1, 2, new Object[] { "3", new Object[] { '4', new Object[] { 5, 6, null } } } },
				() -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null> at index [2][1][1][2]");
		}
	}
