	@Test
	public void testNewThreadHasOwnInstance() throws InterruptedException {
		SideEffectBean apartment = (SideEffectBean) beanFactory.getBean("apartment");
		assertEquals(INITIAL_COUNT, apartment.getCount() );
		apartment.doWork();
		apartment.doWork();
		apartment.doWork();
		assertEquals(INITIAL_COUNT + 3, apartment.getCount() );

		class Runner implements Runnable {
			public SideEffectBean mine;
			@Override
			public void run() {
				this.mine = (SideEffectBean) beanFactory.getBean("apartment");
				assertEquals(INITIAL_COUNT, mine.getCount() );
				mine.doWork();
				assertEquals(INITIAL_COUNT + 1, mine.getCount() );
			}
		}
		Runner r = new Runner();
		Thread t = new Thread(r);
		t.start();
		t.join();

		assertNotNull(r);

		// Check it didn't affect the other thread's copy
		assertEquals(INITIAL_COUNT + 3, apartment.getCount() );

		// When we use other thread's copy in this thread
		// it should behave like ours
		assertEquals(INITIAL_COUNT + 3, r.mine.getCount() );

		// Bound to two threads
		assertEquals(2, ((ThreadLocalTargetSourceStats) apartment).getObjectCount());
	}
