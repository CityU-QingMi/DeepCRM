	@Test
	public void testCacheGetSynchronized() throws InterruptedException {
		T cache = getCache();
		final AtomicInteger counter = new AtomicInteger();
		final List<Object> results = new CopyOnWriteArrayList<>();
		final CountDownLatch latch = new CountDownLatch(10);

		String key = createRandomKey();
		Runnable run = () -> {
			try {
				Integer value = cache.get(key, () -> {
					Thread.sleep(50); // make sure the thread will overlap
					return counter.incrementAndGet();
				});
				results.add(value);
			}
			finally {
				latch.countDown();
			}
		};

		for (int i = 0; i < 10; i++) {
			new Thread(run).start();
		}
		latch.await();

		assertEquals(10, results.size());
		results.forEach(r -> assertThat(r, is(1))); // Only one method got invoked
	}
