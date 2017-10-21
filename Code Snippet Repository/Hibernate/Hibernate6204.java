	@Test
	public void testIsLoadedWithReferenceProtectedField() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new FieldAccessBean(),
						"protectedAccessProperty",
						cache
				)
		);
	}
