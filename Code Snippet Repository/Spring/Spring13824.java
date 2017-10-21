	public static RuntimeBeanReference registerScheduler(
			String schedulerName, ParserContext context, @Nullable Object source) {

		if (!context.getRegistry().containsBeanDefinition(schedulerName)) {
			RootBeanDefinition taskSchedulerDef = new RootBeanDefinition(ThreadPoolTaskScheduler.class);
			taskSchedulerDef.setSource(source);
			taskSchedulerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			taskSchedulerDef.getPropertyValues().add("poolSize", Runtime.getRuntime().availableProcessors());
			taskSchedulerDef.getPropertyValues().add("threadNamePrefix", schedulerName + "-");
			taskSchedulerDef.getPropertyValues().add("removeOnCancelPolicy", true);
			context.getRegistry().registerBeanDefinition(schedulerName, taskSchedulerDef);
			context.registerComponent(new BeanComponentDefinition(taskSchedulerDef, schedulerName));
		}
		return new RuntimeBeanReference(schedulerName);
	}
