	@Test
	void assumeTrueWithBooleanTrueAndStringMessage() {
		String foo = null;
		try {
			assumeTrue(true, "true");
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
