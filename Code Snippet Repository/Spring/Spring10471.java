	@Test
	public void identicalExceptionThroughGetAndCallback() throws Exception {
		final HttpClientErrorException[] callbackException = new HttpClientErrorException[1];

		final CountDownLatch latch = new CountDownLatch(1);
		ListenableFuture<?> future = template.execute(baseUrl + "/status/notfound", HttpMethod.GET, null, null);
		future.addCallback(new ListenableFutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				fail("onSuccess not expected");
			}
			@Override
			public void onFailure(Throwable ex) {
				assertTrue(ex instanceof HttpClientErrorException);
				callbackException[0] = (HttpClientErrorException) ex;
				latch.countDown();
			}
		});

		try {
			future.get();
			fail("Exception expected");
		}
		catch (ExecutionException ex) {
			Throwable cause = ex.getCause();
			assertTrue(cause instanceof HttpClientErrorException);
			latch.await(5, TimeUnit.SECONDS);
			assertSame(callbackException[0], cause);
		}
	}
