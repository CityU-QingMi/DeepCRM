	@Test
	void assumeTrueWithBooleanSupplierTrue() {
		String foo = null;
		try {
			assumeTrue(() -> true);
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
