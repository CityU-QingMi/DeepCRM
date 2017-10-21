	@Test
	public void loadAndInitializeCustomConnectionProviderTest() throws SchedulerException, InterruptedException {
		StdSchedulerFactory factory = new StdSchedulerFactory("org/quartz/properties/quartzCustomConnectionProvider.properties");
		Scheduler scheduler = factory.getScheduler();
		try{
			scheduler.start();
		} catch(Exception e){
			//the mock connection provider throws a MockSQLException
			assertEquals("org.quartz.impl.MockSQLException",e.getCause().getCause().getClass().getName());
		}
		assertEquals("setCustomProperty(customValue)",MockConnectionProvider.methodsCalled.get(0));
		assertEquals("initialize",MockConnectionProvider.methodsCalled.get(1));
		assertEquals("getConnection",MockConnectionProvider.methodsCalled.get(2));
	}
