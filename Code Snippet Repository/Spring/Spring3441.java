	@Test
	public void testEventPublicationInterceptor() throws Throwable {
		MethodInvocation invocation = mock(MethodInvocation.class);
		ApplicationContext ctx = mock(ApplicationContext.class);

		EventPublicationInterceptor interceptor = new EventPublicationInterceptor();
		interceptor.setApplicationEventClass(MyEvent.class);
		interceptor.setApplicationEventPublisher(ctx);
		interceptor.afterPropertiesSet();

		given(invocation.proceed()).willReturn(new Object());
		given(invocation.getThis()).willReturn(new Object());
		interceptor.invoke(invocation);
		verify(ctx).publishEvent(isA(MyEvent.class));
	}
