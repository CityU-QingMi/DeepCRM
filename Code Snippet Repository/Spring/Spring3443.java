	@Test
	public void invokeListenerCheckedException() {
		Method method = ReflectionUtils.findMethod(
				SampleEvents.class, "generateCheckedException", GenericTestEvent.class);
		GenericTestEvent<String> event = createGenericTestEvent("fail");

		this.thrown.expect(UndeclaredThrowableException.class);
		this.thrown.expectCause(is(instanceOf(IOException.class)));
		invokeListener(method, event);
	}
