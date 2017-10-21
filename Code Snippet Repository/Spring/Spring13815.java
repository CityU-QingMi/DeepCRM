	private RuntimeBeanReference getMessageChannel(
			String name, @Nullable Element element, ParserContext context, @Nullable Object source) {

		RootBeanDefinition executor;
		if (element == null) {
			executor = getDefaultExecutorBeanDefinition(name);
		}
		else {
			Element executorElem = DomUtils.getChildElementByTagName(element, "executor");
			if (executorElem == null) {
				executor = getDefaultExecutorBeanDefinition(name);
			}
			else {
				executor = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
				if (executorElem.hasAttribute("core-pool-size")) {
					executor.getPropertyValues().add("corePoolSize", executorElem.getAttribute("core-pool-size"));
				}
				if (executorElem.hasAttribute("max-pool-size")) {
					executor.getPropertyValues().add("maxPoolSize", executorElem.getAttribute("max-pool-size"));
				}
				if (executorElem.hasAttribute("keep-alive-seconds")) {
					executor.getPropertyValues().add("keepAliveSeconds", executorElem.getAttribute("keep-alive-seconds"));
				}
				if (executorElem.hasAttribute("queue-capacity")) {
					executor.getPropertyValues().add("queueCapacity", executorElem.getAttribute("queue-capacity"));
				}
			}
		}
		ConstructorArgumentValues argValues = new ConstructorArgumentValues();
		if (executor != null) {
			executor.getPropertyValues().add("threadNamePrefix", name + "-");
			String executorName = name + "Executor";
			registerBeanDefByName(executorName, executor, context, source);
			argValues.addIndexedArgumentValue(0, new RuntimeBeanReference(executorName));
		}
		RootBeanDefinition channelDef = new RootBeanDefinition(ExecutorSubscribableChannel.class, argValues, null);
		ManagedList<? super Object> interceptors = new ManagedList<>();
		if (element != null) {
			Element interceptorsElement = DomUtils.getChildElementByTagName(element, "interceptors");
			interceptors.addAll(WebSocketNamespaceUtils.parseBeanSubElements(interceptorsElement, context));
		}
		interceptors.add(new ImmutableMessageChannelInterceptor());
		channelDef.getPropertyValues().add("interceptors", interceptors);

		registerBeanDefByName(name, channelDef, context, source);
		return new RuntimeBeanReference(name);
	}
