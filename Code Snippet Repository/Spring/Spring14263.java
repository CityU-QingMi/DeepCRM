	@Test
	public void sendHeartbeat() throws Exception {
		this.session.setActive(true);
		this.session.sendHeartbeat();

		assertEquals(1, this.session.getSockJsFramesWritten().size());
		assertEquals(SockJsFrame.heartbeatFrame(), this.session.getSockJsFramesWritten().get(0));

		verify(this.taskScheduler).schedule(any(Runnable.class), any(Date.class));
		verifyNoMoreInteractions(this.taskScheduler);
	}
