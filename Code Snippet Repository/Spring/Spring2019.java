	@Test
	@SuppressWarnings("")
	public void schedulerAutoStartsOnContextRefreshedEventByDefault() throws Exception {
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerBeanDefinition("scheduler", new RootBeanDefinition(SchedulerFactoryBean.class));
		Scheduler bean = context.getBean("scheduler", Scheduler.class);
		assertFalse(bean.isStarted());
		context.refresh();
		assertTrue(bean.isStarted());
	}
