	@Test
	public void threadPoolSizeDefault() {
		String name = "clientInboundChannelExecutor";
		ThreadPoolTaskExecutor executor = this.defaultContext.getBean(name, ThreadPoolTaskExecutor.class);
		assertEquals(Runtime.getRuntime().availableProcessors() * 2, executor.getCorePoolSize());
		// No way to verify queue capacity

		name = "clientOutboundChannelExecutor";
		executor = this.defaultContext.getBean(name, ThreadPoolTaskExecutor.class);
		assertEquals(Runtime.getRuntime().availableProcessors() * 2, executor.getCorePoolSize());

		name = "brokerChannelExecutor";
		executor = this.defaultContext.getBean(name, ThreadPoolTaskExecutor.class);
		assertEquals(0, executor.getCorePoolSize());
		assertEquals(1, executor.getMaxPoolSize());
	}
