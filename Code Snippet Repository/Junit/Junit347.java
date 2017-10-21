	@Test
	void assumeFalseWithBooleanSupplierFalse() {
		String foo = null;
		try {
			assumeFalse(() -> false);
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
