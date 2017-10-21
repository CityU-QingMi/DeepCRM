	@Test
	public void invokeListenerRuntimeException() {
		Method method = ReflectionUtils.findMethod(
				SampleEvents.class, "generateRuntimeException", GenericTestEvent.class);
		GenericTestEvent<String> event = createGenericTestEvent("fail");

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Test exception");
		this.thrown.expectCause(is((Throwable) isNull()));
		invokeListener(method, event);
	}
