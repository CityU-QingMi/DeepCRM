	@Test
	@SuppressWarnings({"", ""})
	public void cancelInactivityTasks() throws Exception {
		TcpConnection<byte[]> tcpConnection = getTcpConnection();

		ScheduledFuture future = mock(ScheduledFuture.class);
		when(this.taskScheduler.scheduleWithFixedDelay(any(), eq(1L))).thenReturn(future);

		tcpConnection.onReadInactivity(mock(Runnable.class), 2L);
		tcpConnection.onWriteInactivity(mock(Runnable.class), 2L);

		this.webSocketHandlerCaptor.getValue().afterConnectionClosed(this.webSocketSession, CloseStatus.NORMAL);

		verify(future, times(2)).cancel(true);
		verifyNoMoreInteractions(future);
	}
