	@Test
	public void brokerChannelCustomized() {
		AbstractSubscribableChannel channel = this.customContext.getBean(
				"brokerChannel", AbstractSubscribableChannel.class);

		assertEquals(4, channel.getInterceptors().size());

		ThreadPoolTaskExecutor taskExecutor = this.customContext.getBean(
				"brokerChannelExecutor", ThreadPoolTaskExecutor.class);

		assertEquals(31, taskExecutor.getCorePoolSize());
		assertEquals(32, taskExecutor.getMaxPoolSize());
		assertEquals(33, taskExecutor.getKeepAliveSeconds());
	}
