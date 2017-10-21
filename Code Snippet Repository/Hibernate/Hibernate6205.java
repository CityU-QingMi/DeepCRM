	@Test
	public void testIsLoadedWithReferenceProtectedMethod() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new MethodAccessBean(),
						"protectedAccessPropertyValue",
						cache
				)
		);
	}
