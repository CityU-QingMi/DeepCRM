	@Test
	public void testConcurrent() {
		for (int i = 0; i < 100; i++) {
			TestRun run = new TestRun();
			run.setDaemon(true);
			set.add(run);
		}
		for (Iterator<TestRun> it = new HashSet<>(set).iterator(); it.hasNext();) {
			TestRun run = it.next();
			run.start();
		}
		logger.info("Thread creation over, " + set.size() + " still active.");
		synchronized (set) {
			while (!set.isEmpty() && ex == null) {
				try {
					set.wait();
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
