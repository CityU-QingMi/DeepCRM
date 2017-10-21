	private <V> StopWatch timeMultiThreaded(String id, final Map<Integer, V> map,
			ValueFactory<V> factory) throws InterruptedException {

		StopWatch stopWatch = new StopWatch(id);
		for (int i = 0; i < 500; i++) {
			map.put(i, factory.newValue(i));
		}
		Thread[] threads = new Thread[30];
		stopWatch.start("Running threads");
		for (int threadIndex = 0; threadIndex < threads.length; threadIndex++) {
			threads[threadIndex] = new Thread("Cache access thread " + threadIndex) {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						for (int i = 0; i < 1000; i++) {
							map.get(i);
						}
					}
				}
			};
		}
		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			if (thread.isAlive()) {
				thread.join(2000);
			}
		}
		stopWatch.stop();
		return stopWatch;
	}
