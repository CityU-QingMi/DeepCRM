	@Test
	public void testConcurrent() {
		for (int i = 0; i < 10; i++) {
			TestRun run = new TestRun(this);
			set.add(run);
			Thread t = new Thread(run);
			t.setDaemon(true);
			t.start();
		}
		logger.info("Thread creation over, " + set.size() + " still active.");
		synchronized (this) {
			while (!set.isEmpty() && ex == null) {
				try {
					wait();
				}
				catch (InterruptedException e) {
					logger.info(e.toString());
				}
				logger.info(set.size() + " threads still active.");
			}
		}
		if (ex != null) {
			fail(ex.getMessage());
		}
	}
