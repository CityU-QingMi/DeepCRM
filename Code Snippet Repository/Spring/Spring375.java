	@Test
	public void testInvokeUnderTraceWithExceptionTracking() throws Throwable {
		given(mi.getMethod()).willReturn(String.class.getMethod("toString"));
		given(mi.proceed()).willThrow(new IllegalArgumentException());

		try {
			interceptor.invokeUnderTrace(mi, log);
			fail("Must have propagated the IllegalArgumentException");
		}
		catch (IllegalArgumentException expected) {
		}

		assertEquals("Monitors must exist for the method invocation and 2 exceptions",
				3, MonitorFactory.getNumRows());
		assertTrue("The jamon report must contain the toString method that was invoked",
				MonitorFactory.getReport().contains("toString"));
		assertTrue("The jamon report must contain the generic exception: " + MonitorFactory.EXCEPTIONS_LABEL,
				MonitorFactory.getReport().contains(MonitorFactory.EXCEPTIONS_LABEL));
		assertTrue("The jamon report must contain the specific exception: IllegalArgumentException'",
				MonitorFactory.getReport().contains("IllegalArgumentException"));
	}
