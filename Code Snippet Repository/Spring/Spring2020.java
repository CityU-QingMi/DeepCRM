	@Test
	@SuppressWarnings("")
	public void schedulerAutoStartupFalse() throws Exception {
		StaticApplicationContext context = new StaticApplicationContext();
		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(
				SchedulerFactoryBean.class).addPropertyValue("autoStartup", false).getBeanDefinition();
		context.registerBeanDefinition("scheduler", beanDefinition);
		Scheduler bean = context.getBean("scheduler", Scheduler.class);
		assertFalse(bean.isStarted());
		context.refresh();
		assertFalse(bean.isStarted());
	}
