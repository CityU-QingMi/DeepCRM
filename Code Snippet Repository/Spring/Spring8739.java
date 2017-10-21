		@RequestMapping(params = "")
		public DeferredResult<Person> getDeferredResultWithDelayedError() {
			final DeferredResult<Person> deferredResult = new DeferredResult<>();
			new Thread() {
				public void run() {
					try {
						Thread.sleep(100);
						deferredResult.setErrorResult(new RuntimeException("Delayed Error"));
					}
					catch (InterruptedException e) {
						/* no-op */
					}
				}
			}.start();
			return deferredResult;
		}
