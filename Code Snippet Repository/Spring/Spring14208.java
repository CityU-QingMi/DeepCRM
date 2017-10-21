	@Test
	public void connectFailure() throws Exception {
		final HttpServerErrorException expected = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		RestOperations restTemplate = mock(RestOperations.class);
		given(restTemplate.execute((URI) any(), eq(HttpMethod.POST), any(), any())).willThrow(expected);

		final CountDownLatch latch = new CountDownLatch(1);
		connect(restTemplate).addCallback(
				new ListenableFutureCallback<WebSocketSession>() {
					@Override
					public void onSuccess(WebSocketSession result) {
					}
					@Override
					public void onFailure(Throwable ex) {
						if (ex == expected) {
							latch.countDown();
						}
					}
				}
		);
		verifyNoMoreInteractions(this.webSocketHandler);
	}
