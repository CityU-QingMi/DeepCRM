	@Test
	public void testIsLoadedWithReferencePrivateField() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new FieldAccessBean(),
						"privateAccessProperty",
						cache
				)
		);
	}
