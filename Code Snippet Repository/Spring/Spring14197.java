	@Test(timeout = 5000)
	public void fallbackAfterConnectTimeout() throws Exception {
		TestClientHandler clientHandler = new TestClientHandler();
		this.testFilter.sleepDelayMap.put("/xhr_streaming", 10000L);
		this.testFilter.sendErrorMap.put("/xhr_streaming", 503);
		initSockJsClient(createXhrTransport());
		this.sockJsClient.setConnectTimeoutScheduler(this.wac.getBean(ThreadPoolTaskScheduler.class));
		WebSocketSession clientSession = sockJsClient.doHandshake(clientHandler, this.baseUrl + "/echo").get();
		assertEquals("Fallback didn't occur", XhrClientSockJsSession.class, clientSession.getClass());
		TextMessage message = new TextMessage("message1");
		clientSession.sendMessage(message);
		clientHandler.awaitMessage(message, 5000);
		clientSession.close();
	}
