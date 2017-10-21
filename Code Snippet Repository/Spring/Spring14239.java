	@Test
	public void defaultTransportHandlersWithOverride() {
		XhrReceivingTransportHandler xhrHandler = new XhrReceivingTransportHandler();

		DefaultSockJsService service = new DefaultSockJsService(mock(TaskScheduler.class), xhrHandler);
		Map<TransportType, TransportHandler> handlers = service.getTransportHandlers();

		assertEquals(8, handlers.size());
		assertSame(xhrHandler, handlers.get(xhrHandler.getTransportType()));
	}
