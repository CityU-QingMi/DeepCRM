	@Test
	public void invocationTargetException() throws Exception {
		Throwable expected = new RuntimeException("error");
		try {
			invokeExceptionRaisingHandler(expected);
		}
		catch (RuntimeException actual) {
			assertSame(expected, actual);
		}

		expected = new Error("error");
		try {
			invokeExceptionRaisingHandler(expected);
		}
		catch (Error actual) {
			assertSame(expected, actual);
		}

		expected = new Exception("error");
		try {
			invokeExceptionRaisingHandler(expected);
		}
		catch (Exception actual) {
			assertSame(expected, actual);
		}

		expected = new Throwable("error");
		try {
			invokeExceptionRaisingHandler(expected);
		}
		catch (IllegalStateException actual) {
			assertNotNull(actual.getCause());
			assertSame(expected, actual.getCause());
			assertTrue(actual.getMessage().contains("Failed to invoke handler method"));
		}
	}
