	@Test
	public void exceptionHandlerNeverThrowsUnexpectedException() {
		Method m = ReflectionUtils.findMethod(ClassWithException.class, "failWithVoid");
		TestableAsyncUncaughtExceptionHandler exceptionHandler = new TestableAsyncUncaughtExceptionHandler(true);
		AnnotationAsyncExecutionAspect.aspectOf().setExceptionHandler(exceptionHandler);
		try {
			assertFalse("Handler should not have been called", exceptionHandler.isCalled());
			ClassWithException obj = new ClassWithException();
			try {
				obj.failWithVoid();
				exceptionHandler.await(3000);
				exceptionHandler.assertCalledWith(m, UnsupportedOperationException.class);
			}
			catch (Exception ex) {
				fail("No unexpected exception should have been received but got " + ex.getMessage());
			}
		}
		finally {
			AnnotationAsyncExecutionAspect.aspectOf().setExceptionHandler(defaultExceptionHandler);

		}
	}
