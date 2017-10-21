	@Test
	public void invokeListenerWithSeveralTypes() {
		Method method = ReflectionUtils.findMethod(SampleEvents.class, "handleStringOrInteger");
		PayloadApplicationEvent<String> event = new PayloadApplicationEvent<>(this, "test");
		invokeListener(method, event);
		verify(this.sampleEvents, times(1)).handleStringOrInteger();
		PayloadApplicationEvent<Integer> event2 = new PayloadApplicationEvent<>(this, 123);
		invokeListener(method, event2);
		verify(this.sampleEvents, times(2)).handleStringOrInteger();
		PayloadApplicationEvent<Double> event3 = new PayloadApplicationEvent<>(this, 23.2);
		invokeListener(method, event3);
		verify(this.sampleEvents, times(2)).handleStringOrInteger();
	}
