	@SuppressWarnings("")
	@Test
	public void cacheExceptionRewriteCallStack() {
		final String keyItem = name.getMethodName();

		UnsupportedOperationException first = null;
		long ref = service.exceptionInvocations();
		try {
			service.cacheWithException(keyItem, true);
			fail("Should have thrown an exception");
		}
		catch (UnsupportedOperationException e) {
			first = e;
		}
		// Sanity check, this particular call has called the service
		assertEquals("First call should not have been cached", ref + 1, service.exceptionInvocations());

		UnsupportedOperationException second = methodInCallStack(keyItem);
		// Sanity check, this particular call has *not* called the service
		assertEquals("Second call should have been cached", ref + 1, service.exceptionInvocations());

		assertEquals(first.getCause(), second.getCause());
		assertEquals(first.getMessage(), second.getMessage());
		assertFalse("Original stack must not contain any reference to methodInCallStack",
				contain(first, AbstractJCacheAnnotationTests.class.getName(), "methodInCallStack"));
		assertTrue("Cached stack should have been rewritten with a reference to  methodInCallStack",
				contain(second, AbstractJCacheAnnotationTests.class.getName(), "methodInCallStack"));
	}
