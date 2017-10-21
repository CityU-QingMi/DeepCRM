	@Test
	public void handleReturnValueWithMultipleHandlers() throws Exception {
		HandlerMethodReturnValueHandler anotherIntegerHandler = mock(HandlerMethodReturnValueHandler.class);
		when(anotherIntegerHandler.supportsReturnType(this.integerType)).thenReturn(true);

		this.handlers.handleReturnValue(55, this.integerType, this.mavContainer, null);

		verify(this.integerHandler).handleReturnValue(55, this.integerType, this.mavContainer, null);
		verifyNoMoreInteractions(anotherIntegerHandler);
	}
