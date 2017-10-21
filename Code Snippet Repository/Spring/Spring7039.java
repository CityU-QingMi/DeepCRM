	protected MessagingMessageListenerAdapter getPayloadInstance(final Object payload,
			String methodName, Class... parameterTypes) {

		Method method = ReflectionUtils.findMethod(SampleBean.class, methodName, parameterTypes);
		MessagingMessageListenerAdapter adapter = new MessagingMessageListenerAdapter() {
			@Override
			protected Object extractMessage(javax.jms.Message message) {
				return payload;
			}
		};
		adapter.setHandlerMethod(factory.createInvocableHandlerMethod(sample, method));
		return adapter;
	}
