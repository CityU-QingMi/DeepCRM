		@Async
		public ListenableFuture<String> returnSomethingListenable(int i) {
			assertTrue(!Thread.currentThread().getName().equals(originalThreadName));
			if (i == 0) {
				throw new IllegalArgumentException();
			}
			else if (i < 0) {
				return AsyncResult.forExecutionException(new IOException());
			}
			return new AsyncResult<>(Integer.toString(i));
		}
