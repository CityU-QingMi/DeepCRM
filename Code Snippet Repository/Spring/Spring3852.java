	@Test
	public void asyncResultWithSeparateCallbacksAndValue() throws Exception {
		String value = "val";
		final Set<String> values = new HashSet<>(1);
		ListenableFuture<String> future = AsyncResult.forValue(value);
		future.addCallback(values::add, (ex) -> fail("Failure callback not expected: " + ex));
		assertSame(value, values.iterator().next());
		assertSame(value, future.get());
		assertSame(value, future.completable().get());
		future.completable().thenAccept(v -> assertSame(value, v));
	}
