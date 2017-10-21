	@Test
	public void heartbeatValueWithAndWithoutTaskScheduler() throws Exception {

		assertNull(this.messageHandler.getHeartbeatValue());

		this.messageHandler.setTaskScheduler(this.taskScheduler);

		assertNotNull(this.messageHandler.getHeartbeatValue());
		assertArrayEquals(new long[] {10000, 10000}, this.messageHandler.getHeartbeatValue());
	}
