	@Test
	public void beanInstanceRetrievedAtEveryInvocation() {
		Method method = ReflectionUtils.findMethod(
				SampleEvents.class, "handleGenericString", GenericTestEvent.class);
		when(this.context.getBean("testBean")).thenReturn(this.sampleEvents);
		ApplicationListenerMethodAdapter listener = new ApplicationListenerMethodAdapter(
				"testBean", GenericTestEvent.class, method);
		listener.init(this.context, new EventExpressionEvaluator());
		GenericTestEvent<String> event = createGenericTestEvent("test");


		listener.onApplicationEvent(event);
		verify(this.sampleEvents, times(1)).handleGenericString(event);
		verify(this.context, times(1)).getBean("testBean");

		listener.onApplicationEvent(event);
		verify(this.sampleEvents, times(2)).handleGenericString(event);
		verify(this.context, times(2)).getBean("testBean");
	}
