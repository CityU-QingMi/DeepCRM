	protected CountDownLatch expectPutWithValue(Predicate<Object> valuePredicate) {
		if (!isUsingInvalidation() && accessType != AccessType.NONSTRICT_READ_WRITE) {
			CountDownLatch latch = new CountDownLatch(1);
			ExpectingInterceptor.get(remoteRegion.getCache())
				.when((ctx, cmd) -> cmd instanceof PutKeyValueCommand && valuePredicate.test(((PutKeyValueCommand) cmd).getValue()))
				.countDown(latch);
			cleanup.add(() -> ExpectingInterceptor.cleanup(remoteRegion.getCache()));
			return latch;
		} else {
			return new CountDownLatch(0);
		}
	}
