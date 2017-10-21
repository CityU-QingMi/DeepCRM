	private Runnable mockValidator(AdvancedCache cache, CountDownLatch latch) {
		PutFromLoadValidator originalValidator = PutFromLoadValidator.removeFromCache(cache);
		PutFromLoadValidator mockValidator = spy(originalValidator);
		doAnswer(invocation -> {
			try {
				return invocation.callRealMethod();
			} finally {
				latch.countDown();
			}
		}).when(mockValidator).endInvalidatingKey(any(), any());
		PutFromLoadValidator.addToCache(cache, mockValidator);
		return () -> {
			PutFromLoadValidator.removeFromCache(cache);
			PutFromLoadValidator.addToCache(cache, originalValidator);
		};
	}
