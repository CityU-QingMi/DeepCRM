	@Test
	public void setValueTriggersCallback() {
		String string = "hello";
		final String[] callbackHolder = new String[1];

		settableListenableFuture.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				callbackHolder[0] = result;
			}
			@Override
			public void onFailure(Throwable ex) {
				fail("Expected onSuccess() to be called");
			}
		});

		settableListenableFuture.set(string);
		assertThat(callbackHolder[0], equalTo(string));
		assertFalse(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
