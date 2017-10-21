	protected CountDownLatch expectPutWithValue(AdvancedCache cache, Predicate<Object> valuePredicate, int numUpdates) {
		if (!cacheMode.isInvalidation() && accessType != AccessType.NONSTRICT_READ_WRITE) {
			CountDownLatch latch = new CountDownLatch(numUpdates);
			ExpectingInterceptor.get(cache)
				.when((ctx, cmd) -> cmd instanceof PutKeyValueCommand && valuePredicate.test(((PutKeyValueCommand) cmd).getValue()))
				.countDown(latch);
			cleanup.add(() -> ExpectingInterceptor.cleanup(cache));
			return latch;
		} else {
			return new CountDownLatch(0);
		}
	}
