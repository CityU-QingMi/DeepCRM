	@Test
	void assumeTrueWithBooleanTrue() {
		String foo = null;
		try {
			assumeTrue(true);
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
