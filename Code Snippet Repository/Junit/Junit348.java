	@Test
	void assumeFalseWithBooleanFalseAndStringMessage() {
		String foo = null;
		try {
			assumeFalse(false, "false");
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
