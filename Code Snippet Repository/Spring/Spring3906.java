	@Test
	public void checkTarget() {
		List<IntervalTask> tasks = (List<IntervalTask>) new DirectFieldAccessor(
				this.registrar).getPropertyValue("fixedRateTasks");
		Runnable runnable = tasks.get(0).getRunnable();
		assertEquals(ScheduledMethodRunnable.class, runnable.getClass());
		Object targetObject = ((ScheduledMethodRunnable) runnable).getTarget();
		Method targetMethod = ((ScheduledMethodRunnable) runnable).getMethod();
		assertEquals(this.testBean, targetObject);
		assertEquals("test", targetMethod.getName());
	}
