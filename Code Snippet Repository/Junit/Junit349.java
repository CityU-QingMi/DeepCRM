	@Test
	void assumeFalseWithBooleanSupplierFalseAndMessageSupplier() {
		String foo = null;
		try {
			assumeFalse(() -> false, () -> "false");
			foo = "foo";
		}
		finally {
			assertNotNull(foo);
		}
	}
