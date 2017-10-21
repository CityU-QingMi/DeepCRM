	@Test
	public void testIsLoadedWithReferencePublicField() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new FieldAccessBean(),
						"publicAccessProperty",
						cache
				)
		);
	}
