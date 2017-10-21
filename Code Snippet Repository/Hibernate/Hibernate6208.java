	@Test
	public void testIsLoadedWithNullInterceptor() {
		assertEquals(
				LoadState.LOADED,
				PersistenceUtilHelper.isLoaded(
						new PersistentAttributeInterceptable() {

							@Override
							public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
								return null;
							}

							@Override
							public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {

							}
						}
				)
		);
	}
