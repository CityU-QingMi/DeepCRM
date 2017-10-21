	private RuntimeBeanReference registerUserRegistryMessageHandler(
			RuntimeBeanReference userRegistry, RuntimeBeanReference brokerTemplate,
			String destination, ParserContext context, @Nullable Object source) {

		Object scheduler = WebSocketNamespaceUtils.registerScheduler(SCHEDULER_BEAN_NAME, context, source);

		RootBeanDefinition beanDef = new RootBeanDefinition(UserRegistryMessageHandler.class);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(0, userRegistry);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(1, brokerTemplate);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(2, destination);
		beanDef.getConstructorArgumentValues().addIndexedArgumentValue(3, scheduler);

		String beanName = registerBeanDef(beanDef, context, source);
		return new RuntimeBeanReference(beanName);
	}
