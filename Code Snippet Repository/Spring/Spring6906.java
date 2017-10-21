	@Test
	public void testErrorHandlers() {
		ErrorHandler expected = this.context.getBean("testErrorHandler", ErrorHandler.class);
		ErrorHandler errorHandler1 = getErrorHandler("listener1");
		ErrorHandler errorHandler2 = getErrorHandler("listener2");
		ErrorHandler defaultErrorHandler = getErrorHandler(DefaultMessageListenerContainer.class.getName() + "#0");
		assertSame(expected, errorHandler1);
		assertSame(expected, errorHandler2);
		assertNull(defaultErrorHandler);
	}
