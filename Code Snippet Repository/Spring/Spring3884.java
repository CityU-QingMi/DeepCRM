	@Test
	public void cronTaskWithZone() throws InterruptedException {
		Assume.group(TestGroup.LONG_RUNNING);

		BeanDefinition processorDefinition = new RootBeanDefinition(ScheduledAnnotationBeanPostProcessor.class);
		BeanDefinition targetDefinition = new RootBeanDefinition(CronWithTimezoneTestBean.class);
		context.registerBeanDefinition("postProcessor", processorDefinition);
		context.registerBeanDefinition("target", targetDefinition);
		context.refresh();

		Object postProcessor = context.getBean("postProcessor");
		Object target = context.getBean("target");
		ScheduledTaskRegistrar registrar = (ScheduledTaskRegistrar)
				new DirectFieldAccessor(postProcessor).getPropertyValue("registrar");
		@SuppressWarnings("unchecked")
		List<CronTask> cronTasks = (List<CronTask>)
				new DirectFieldAccessor(registrar).getPropertyValue("cronTasks");
		assertEquals(1, cronTasks.size());
		CronTask task = cronTasks.get(0);
		ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task.getRunnable();
		Object targetObject = runnable.getTarget();
		Method targetMethod = runnable.getMethod();
		assertEquals(target, targetObject);
		assertEquals("cron", targetMethod.getName());
		assertEquals("0 0 0-4,6-23 * * ?", task.getExpression());
		Trigger trigger = task.getTrigger();
		assertNotNull(trigger);
		assertTrue(trigger instanceof CronTrigger);
		CronTrigger cronTrigger = (CronTrigger) trigger;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+10"));
		cal.clear();
		cal.set(2013, 3, 15, 4, 0); // 15-04-2013 4:00 GMT+10
		Date lastScheduledExecutionTime = cal.getTime();
		Date lastActualExecutionTime = cal.getTime();
		cal.add(Calendar.MINUTE, 30); // 4:30
		Date lastCompletionTime = cal.getTime();
		TriggerContext triggerContext = new SimpleTriggerContext(
				lastScheduledExecutionTime, lastActualExecutionTime, lastCompletionTime);
		cal.add(Calendar.MINUTE, 30);
		cal.add(Calendar.HOUR_OF_DAY, 1); // 6:00
		Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
		assertEquals(cal.getTime(), nextExecutionTime); // assert that 6:00 is next execution time
		Thread.sleep(10000);
	}
