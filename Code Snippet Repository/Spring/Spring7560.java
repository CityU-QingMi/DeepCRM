	@Test
	public void clientOutboundChannelCustomized() {
		AbstractSubscribableChannel channel = this.customContext.getBean(
				"clientOutboundChannel", AbstractSubscribableChannel.class);

		assertEquals(3, channel.getInterceptors().size());

		ThreadPoolTaskExecutor taskExecutor = this.customContext.getBean(
				"clientOutboundChannelExecutor", ThreadPoolTaskExecutor.class);

		assertEquals(21, taskExecutor.getCorePoolSize());
		assertEquals(22, taskExecutor.getMaxPoolSize());
		assertEquals(23, taskExecutor.getKeepAliveSeconds());
	}
