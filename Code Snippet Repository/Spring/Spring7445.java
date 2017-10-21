	@Test
	public void resolveWithWrongMessageType() throws Exception {
		UnsupportedOperationException ex = new UnsupportedOperationException();
		Message<? extends Throwable> message = new GenericMessage<Throwable>(ex);
		MethodParameter parameter = new MethodParameter(this.method, 4);

		assertTrue(this.resolver.supportsParameter(parameter));
		thrown.expect(MethodArgumentTypeMismatchException.class);
		thrown.expectMessage(ErrorMessage.class.getName());
		thrown.expectMessage(GenericMessage.class.getName());
		assertSame(message, this.resolver.resolveArgument(parameter, message));
	}
