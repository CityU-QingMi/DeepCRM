	private void registerWebSocketMessageBrokerStats(RootBeanDefinition broker, RuntimeBeanReference inChannel,
			RuntimeBeanReference outChannel, ParserContext context, @Nullable Object source) {

		RootBeanDefinition beanDef = new RootBeanDefinition(WebSocketMessageBrokerStats.class);

		RuntimeBeanReference webSocketHandler = new RuntimeBeanReference(WEB_SOCKET_HANDLER_BEAN_NAME);
		beanDef.getPropertyValues().add("subProtocolWebSocketHandler", webSocketHandler);

		if (StompBrokerRelayMessageHandler.class == broker.getBeanClass()) {
			beanDef.getPropertyValues().add("stompBrokerRelay", broker);
		}
		String name = inChannel.getBeanName() + "Executor";
		if (context.getRegistry().containsBeanDefinition(name)) {
			beanDef.getPropertyValues().add("inboundChannelExecutor", context.getRegistry().getBeanDefinition(name));
		}
		name = outChannel.getBeanName() + "Executor";
		if (context.getRegistry().containsBeanDefinition(name)) {
			beanDef.getPropertyValues().add("outboundChannelExecutor", context.getRegistry().getBeanDefinition(name));
		}
		Object scheduler = WebSocketNamespaceUtils.registerScheduler(SCHEDULER_BEAN_NAME, context, source);
		beanDef.getPropertyValues().add("sockJsTaskScheduler", scheduler);

		registerBeanDefByName("webSocketMessageBrokerStats", beanDef, context, source);
	}
