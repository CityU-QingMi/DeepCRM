	@Test
	public void testInvokeUnderTraceWithNormalProcessing() throws Throwable {
		given(mi.getMethod()).willReturn(String.class.getMethod("toString"));

		interceptor.invokeUnderTrace(mi, log);

		assertTrue("jamon must track the method being invoked", MonitorFactory.getNumRows() > 0);
		assertTrue("The jamon report must contain the toString method that was invoked",
				MonitorFactory.getReport().contains("toString"));
	}
