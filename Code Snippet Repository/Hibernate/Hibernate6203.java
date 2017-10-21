	@Test
	public void testIsLoadedWithReferencePublicMethod() {
		assertEquals(
				LoadState.UNKNOWN,
				PersistenceUtilHelper.isLoadedWithReference(
						new MethodAccessBean(),
						"publicAccessPropertyValue",
						cache
				)
		);
	}
