	@Test
	public void clientInboundChannelCustomized() {
		AbstractSubscribableChannel channel = this.customContext.getBean(
				"clientInboundChannel", AbstractSubscribableChannel.class);

		assertEquals(3, channel.getInterceptors().size());

		CustomThreadPoolTaskExecutor taskExecutor = this.customContext.getBean(
				"clientInboundChannelExecutor", CustomThreadPoolTaskExecutor.class);

		assertEquals(11, taskExecutor.getCorePoolSize());
		assertEquals(12, taskExecutor.getMaxPoolSize());
		assertEquals(13, taskExecutor.getKeepAliveSeconds());
	}
