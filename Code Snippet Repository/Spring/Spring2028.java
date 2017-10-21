	@Test
	public void multipleSchedulers() throws Exception {
		ClassPathXmlApplicationContext ctx = context("multipleSchedulers.xml");
		try {
			Scheduler scheduler1 = (Scheduler) ctx.getBean("scheduler1");
			Scheduler scheduler2 = (Scheduler) ctx.getBean("scheduler2");
			assertNotSame(scheduler1, scheduler2);
			assertEquals("quartz1", scheduler1.getSchedulerName());
			assertEquals("quartz2", scheduler2.getSchedulerName());
		}
		finally {
			ctx.close();
		}
	}
