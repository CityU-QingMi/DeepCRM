	@Test
	void assertArrayEqualsNestedObjectArrayVsNull() {
		try {
			assertArrayEquals(//
				new Object[] { new Object[] {}, 1, "2", new Object[] { '3', new Object[] { null } } }, //
				new Object[] { new Object[] {}, 1, "2", new Object[] { '3', new Object[] { new Object[] { "4" } } } });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null> at index [3][1][0]");
		}

		try {
			assertArrayEquals(
				new Object[] { 1, 2, new Object[] { 3, new Object[] { "4", new Object[] { 5, new Object[] { 6 } } } },
						"7" },
				new Object[] { 1, 2, new Object[] { 3, new Object[] { "4", new Object[] { 5, null } } }, "7" });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null> at index [2][1][1][1]");
		}
	}
