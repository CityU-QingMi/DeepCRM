	@Test
	public void taskScheduler() {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);

		String name = "messageBrokerSockJsTaskScheduler";
		ThreadPoolTaskScheduler taskScheduler = config.getBean(name, ThreadPoolTaskScheduler.class);
		ScheduledThreadPoolExecutor executor = taskScheduler.getScheduledThreadPoolExecutor();
		assertEquals(Runtime.getRuntime().availableProcessors(), executor.getCorePoolSize());
		assertTrue(executor.getRemoveOnCancelPolicy());

		SimpleBrokerMessageHandler handler = config.getBean(SimpleBrokerMessageHandler.class);
		assertNotNull(handler.getTaskScheduler());
		assertArrayEquals(new long[] {15000, 15000}, handler.getHeartbeatValue());
	}
