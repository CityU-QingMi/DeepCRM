	@Nullable
	private RootBeanDefinition getDefaultExecutorBeanDefinition(String channelName) {
		if (channelName.equals("brokerChannel")) {
			return null;
		}
		RootBeanDefinition executorDef = new RootBeanDefinition(ThreadPoolTaskExecutor.class);
		executorDef.getPropertyValues().add("corePoolSize", Runtime.getRuntime().availableProcessors() * 2);
		executorDef.getPropertyValues().add("maxPoolSize", Integer.MAX_VALUE);
		executorDef.getPropertyValues().add("queueCapacity", Integer.MAX_VALUE);
		executorDef.getPropertyValues().add("allowCoreThreadTimeOut", true);
		return executorDef;
	}
