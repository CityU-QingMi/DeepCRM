	private static void awaitEvent(BooleanSupplier condition, long timeToWait, String description) {
		long timeToSleep = 200;
		for (int i = 0 ; i < Math.floor(timeToWait / timeToSleep); i++) {
			if (condition.getAsBoolean()) {
				return;
			}
			try {
				Thread.sleep(timeToSleep);
			}
			catch (InterruptedException e) {
				throw new IllegalStateException("Interrupted while waiting for " + description, e);
			}
		}
		throw new IllegalStateException("Timed out waiting for " + description);
	}
