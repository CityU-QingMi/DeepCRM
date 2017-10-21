	@Test
	public void exceptionHandlerFromControllerAdvice() throws Exception {

		InvocableHandlerMethod invocable =
				this.methodResolver.getExceptionHandlerMethod(
						new IllegalStateException("reason"), this.handlerMethod);

		assertNotNull(invocable);
		assertEquals(TestControllerAdvice.class, invocable.getBeanType());
	}
