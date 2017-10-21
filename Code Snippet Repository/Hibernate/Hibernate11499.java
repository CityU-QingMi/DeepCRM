	private CountDownLatch expectRemotePutFromLoad(AdvancedCache localCache, AdvancedCache remoteCache) {
		CountDownLatch putFromLoadLatch;
		if (!isUsingInvalidation()) {
			putFromLoadLatch = new CountDownLatch(1);
			// The command may fail to replicate if it can't acquire lock locally
			ExpectingInterceptor.Condition remoteCondition = ExpectingInterceptor.get(remoteCache)
				.when((ctx, cmd) -> !ctx.isOriginLocal() && cmd instanceof PutKeyValueCommand);
			ExpectingInterceptor.Condition localCondition = ExpectingInterceptor.get(localCache)
				.whenFails((ctx, cmd) -> ctx.isOriginLocal() && cmd instanceof PutKeyValueCommand);
			remoteCondition.run(() -> {
				localCondition.cancel();
				putFromLoadLatch.countDown();
			});
			localCondition.run(() -> {
				remoteCondition.cancel();
				putFromLoadLatch.countDown();
			});
			// just for case the test fails and does not remove the interceptor itself
			cleanup.add(() -> ExpectingInterceptor.cleanup(localCache, remoteCache));
		} else {
			putFromLoadLatch = new CountDownLatch(0);
		}
		return putFromLoadLatch;
	}
