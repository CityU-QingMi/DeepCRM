	@Test
	void assertArrayEqualsNestedObjectArrayVsNullAndMessage() {
		try {
			assertArrayEquals(new Object[] { 1, new Object[] { 2, 3, new Object[] { 4, 5, new Object[] { null } } } },
				new Object[] { 1, new Object[] { 2, 3, new Object[] { 4, 5, new Object[] { new Object[] { 6 } } } } },
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null> at index [1][2][2][0]");
		}

		try {
			assertArrayEquals(
				new Object[] { 1, new Object[] { 2, new Object[] { 3, new Object[] { new Object[] { 4 } } } } },
				new Object[] { 1, new Object[] { 2, new Object[] { 3, new Object[] { null } } } }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null> at index [1][1][1][0]");
		}
	}
