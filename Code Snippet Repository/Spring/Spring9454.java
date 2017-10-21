	private static void runIfAsyncNotComplete(AsyncContext asyncContext, Runnable task) {
		try {
			if (asyncContext.getRequest().isAsyncStarted()) {
				task.run();
			}
		}
		catch (IllegalStateException ex) {
			// Ignore: AsyncContext recycled and should not be used
			// e.g. TIMEOUT_LISTENER (above) may have completed the AsyncContext
		}
	}
