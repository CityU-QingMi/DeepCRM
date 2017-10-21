	@Test
	public void testIsLoadedWithReferencePrivateMethod() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new MethodAccessBean(),
						"privateAccessPropertyValue",
						cache
				)
		);
	}
