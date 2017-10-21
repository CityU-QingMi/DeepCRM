	static <T> T assertTimeoutPreemptively(Duration timeout, ThrowingSupplier<T> supplier,
			Supplier<String> messageSupplier) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		try {
			Future<T> future = executorService.submit(() -> {
				try {
					return supplier.get();
				}
				catch (Throwable throwable) {
					throw ExceptionUtils.throwAsUncheckedException(throwable);
				}
			});

			long timeoutInMillis = timeout.toMillis();
			try {
				return future.get(timeoutInMillis, TimeUnit.MILLISECONDS);
			}
			catch (TimeoutException ex) {
				throw new AssertionFailedError(
					buildPrefix(nullSafeGet(messageSupplier)) + "execution timed out after " + timeoutInMillis + " ms");
			}
			catch (ExecutionException ex) {
				throw ExceptionUtils.throwAsUncheckedException(ex.getCause());
			}
			catch (Throwable ex) {
				throw ExceptionUtils.throwAsUncheckedException(ex);
			}
		}
		finally {
			executorService.shutdownNow();
		}
	}
