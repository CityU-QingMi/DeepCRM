	@Test
	public void exceptionHandlerCalled() {
		Method m = ReflectionUtils.findMethod(ClassWithException.class, "failWithVoid");
		TestableAsyncUncaughtExceptionHandler exceptionHandler = new TestableAsyncUncaughtExceptionHandler();
		AnnotationAsyncExecutionAspect.aspectOf().setExceptionHandler(exceptionHandler);
		try {
			assertFalse("Handler should not have been called", exceptionHandler.isCalled());
			ClassWithException obj = new ClassWithException();
			obj.failWithVoid();
			exceptionHandler.await(3000);
			exceptionHandler.assertCalledWith(m, UnsupportedOperationException.class);
		}
		finally {
			AnnotationAsyncExecutionAspect.aspectOf().setExceptionHandler(defaultExceptionHandler);
		}
	}
