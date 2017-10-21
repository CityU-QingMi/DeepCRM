		@Async
		public Future<String> returnSomething(int i) {
			assertTrue(!Thread.currentThread().getName().equals(originalThreadName));
			if (i == 0) {
				throw new IllegalArgumentException();
			}
			else if (i < 0) {
				return AsyncResult.forExecutionException(new IOException());
			}
			return AsyncResult.forValue(Integer.toString(i));
		}
