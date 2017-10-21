	@Override
	public void initialize() throws SchedulerConfigException {
		// Absolutely needs thread-bound TaskExecutor to initialize.
		this.taskExecutor = SchedulerFactoryBean.getConfigTimeTaskExecutor();
		if (this.taskExecutor == null) {
			throw new SchedulerConfigException(
				"No local TaskExecutor found for configuration - " +
				"'taskExecutor' property must be set on SchedulerFactoryBean");
		}
	}
