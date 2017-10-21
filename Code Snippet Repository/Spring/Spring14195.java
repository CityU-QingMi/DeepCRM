	@Test
	public void infoRequestFailure() throws Exception {
		TestClientHandler handler = new TestClientHandler();
		this.testFilter.sendErrorMap.put("/info", 500);
		CountDownLatch latch = new CountDownLatch(1);
		initSockJsClient(createWebSocketTransport());
		this.sockJsClient.doHandshake(handler, this.baseUrl + "/echo").addCallback(
				new ListenableFutureCallback<WebSocketSession>() {
					@Override
					public void onSuccess(WebSocketSession result) {
					}

					@Override
					public void onFailure(Throwable ex) {
						latch.countDown();
					}
				}
		);
		assertTrue(latch.await(5000, TimeUnit.MILLISECONDS));
	}
