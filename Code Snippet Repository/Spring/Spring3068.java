	public void testUncheckedThrowable(CacheableService<?> service) throws Exception {
		try {
			service.throwUnchecked(1L);
			fail("Excepted exception");
		}
		catch (RuntimeException ex) {
			assertEquals("Wrong exception type", UnsupportedOperationException.class, ex.getClass());
			assertEquals("1", ex.getMessage());
		}
	}
